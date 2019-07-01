package com.ruichen.restful.config.shiro;

import com.ruichen.restful.config.shiro.cache.ShiroRedisCacheManager;
import com.ruichen.restful.config.shiro.jwt.JwtFilter;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.redis.core.RedisTemplate;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName  ShiroConfig
 * @Description  shiro配置类
 * @Date  2019/4/19 22:23
 * @author  lixueyun
 * @version  V1.0
 */
@Configuration
public class ShiroConfig {

	/**
	 * @methodName  securityManager
	 * @description	配置使用自定义Realm，关闭Shiro自带的session
	 * 详情见文档 http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
	 * @param userRealm
	 * @param shiroRedisCacheManager
	 * @author  lixueyun
	 * @Date  2019/4/19 22:25
	 * @return  org.apache.shiro.mgt.SecurityManager
	 */
	@Bean(name = "securityManager")
	@DependsOn("userRealm")
	public SecurityManager securityManager(UserRealm userRealm, ShiroRedisCacheManager shiroRedisCacheManager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		//注入自定义的Realm
		securityManager.setRealm(userRealm);
		// 关闭Shiro自带的session
		DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
		DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
		defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
		subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
		securityManager.setSubjectDAO(subjectDAO);
		//设置自定义redis缓存
		securityManager.setCacheManager(shiroRedisCacheManager);
		return securityManager;
	}

	/**
	 * @methodName  shiroFilterFactoryBean
	 * @description  添加自己的过滤器，自定义url规则
	 * Shiro自带拦截器配置规则
	 * rest：比如/admins/user/**=rest[user],根据请求的方法，相当于/admins/user/**=perms[user：method] ,其中method为post，get，delete等
	 * port：比如/admins/user/**=port[8081],当请求的url的端口不是8081是跳转到schemal：//serverName：8081?queryString,其中schmal是协议http或https等，serverName是你访问的host,8081是url配置里port的端口，queryString是你访问的url里的？后面的参数
	 * perms：比如/admins/user/**=perms[user：add：*],perms参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，比如/admins/user/**=perms["user：add：*,user：modify：*"]，当有多个参数时必须每个参数都通过才通过，想当于isPermitedAll()方法
	 * roles：比如/admins/user/**=roles[admin],参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，当有多个参数时，比如/admins/user/**=roles["admin,guest"],每个参数通过才算通过，相当于hasAllRoles()方法。//要实现or的效果看http://zgzty.blog.163.com/blog/static/83831226201302983358670/
	 * anon：比如/admins/**=anon 没有参数，表示可以匿名使用
	 * authc：比如/admins/user/**=authc表示需要认证才能使用，没有参数
	 * authcBasic：比如/admins/user/**=authcBasic没有参数表示httpBasic认证
	 * ssl：比如/admins/user/**=ssl没有参数，表示安全的url请求，协议为https
	 * user：比如/admins/user/**=user没有参数表示必须存在用户，当登入操作时不做检查
	 * 详情见文档 http://shiro.apache.org/web.html#urls-
	 * @param securityManager
	 * @author  lixueyun
	 * @Date  2019/4/19 22:24
	 * @return  org.apache.shiro.spring.web.ShiroFilterFactoryBean
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager, RedisTemplate redisTemplate) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		//Shiro的核心安全接口,这个属性是必须的
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		// 添加自己的过滤器取名为jwt
		Map<String, Filter> filterMap = new LinkedHashMap<>();
		filterMap.put("jwt", new JwtFilter(redisTemplate));
		shiroFilterFactoryBean.setFilters(filterMap);

		/*定义shiro过滤链  Map结构
		 * Map中key(xml中是指value值)的第一个'/'代表的路径是相对于HttpServletRequest.getContextPath()的值来的
		 * anon：它对应的过滤器里面是空的,什么都没做,这里.do和.jsp后面的*表示参数,比方说login.jsp?main这种
		 * authc：该过滤器下的页面必须验证后才能访问,它是Shiro内置的一个拦截器org.apache.shiro.web.filter.authc.FormAuthenticationFilter
		 */
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
         /* 过滤链定义，从上向下顺序执行，一般将 / ** 放在最为下边:这是一个坑呢，一不小心代码就不好使了;
          authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问 */
	 	filterChainDefinitionMap.put("/", "anon");
	 	//Swagger接口文档
	 	filterChainDefinitionMap.put("/v2/api-docs", "anon");
	 	filterChainDefinitionMap.put("/webjars/**", "anon");
	 	filterChainDefinitionMap.put("/swagger-resources/**", "anon");
	 	filterChainDefinitionMap.put("/swagger-ui.html", "anon");
	 	filterChainDefinitionMap.put("/doc.html", "anon");
		//数据库监控
		filterChainDefinitionMap.put("/druid/**", "anon");
		filterChainDefinitionMap.put("/**", "jwt");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	/**
	 * @methodName  lifecycleBeanPostProcessor
	 * @description  Shiro生命周期处理器
	 * 用于在实现了Initializable接口的Shiro bean初始化时调用Initializable接口回调(例如:UserRealm)
	 * 在实现了Destroyable接口的Shiro bean销毁时调用 Destroyable接口回调(例如:DefaultSecurityManager)
	 * @param
	 * @author  lixueyun
	 * @Date  2019/4/19 22:27
	 * @return  org.apache.shiro.spring.LifecycleBeanPostProcessor
	 */
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * @methodName  advisorAutoProxyCreator
	 * @description  开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,
	 * 并在必要时进行安全逻辑验证
	 * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
	 * @param
	 * @author  lixueyun
	 * @Date  2019/4/19 22:27
	 * @return  org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
	 */
	@Bean
	@DependsOn({"lifecycleBeanPostProcessor"})
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}

	/**
	 * @methodName  authorizationAttributeSourceAdvisor
	 * @description 启用shrio授权注解拦截方式，AOP式方法级权限检查
	 * @param securityManager
	 * @author  lixueyun
	 * @Date  2019/5/31 9:20
	 * @return  org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
	 */
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
}
