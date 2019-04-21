package com.ruichen.restful.config.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

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

	@Autowired
	private UserRealm userRealm;

	/**
	 * @methodName  securityManager
	 * @description  securityManager
	 * @param
	 * @author  lixueyun
	 * @Date  2019/4/19 22:25
	 * @return  org.apache.shiro.mgt.SecurityManager
	 */
	@Bean
	public SecurityManager securityManager(CookieRememberMeManager rememberMeManager, CacheManager cacheShiroManager, SessionManager sessionManager) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(userRealm);
		//缓存
		securityManager.setCacheManager(cacheShiroManager);
		//记住密码
		securityManager.setRememberMeManager(rememberMeManager);
		//session管理
		securityManager.setSessionManager(sessionManager);
		return securityManager;
	}

	/**
	 * @methodName  shiroFilterFactoryBean
	 * @description  Shiro的Web过滤器Factory 命名:shiroFilter
	 * @param securityManager
	 * @author  lixueyun
	 * @Date  2019/4/19 22:24
	 * @return  org.apache.shiro.spring.web.ShiroFilterFactoryBean
	 */
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		//Shiro的核心安全接口,这个属性是必须的
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//默认的登陆访问url
		shiroFilterFactoryBean.setLoginUrl("/login");
		//登陆成功后跳转的url
		shiroFilterFactoryBean.setSuccessUrl("/index");
		//没有权限跳转的url
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		Map<String, Filter> filterMap = new LinkedHashMap<>();
		//shiro有一些默认的拦截器 比如authc，它就是FormAuthenticationFilter表单拦截器 可以自定义拦截器放在这
		//filterMap.put("authc", new AjaxPermissionsAuthorizationFilter());
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
		filterChainDefinitionMap.put("/static/**", "anon");
		filterChainDefinitionMap.put("/login/logout", "anon");
		filterChainDefinitionMap.put("/error", "anon");
		filterChainDefinitionMap.put("/**", "authc");
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	/**
	 * @methodName  hashedCredentialsMatcher
	 * @description 凭证匹配器
	 * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了所以我们需要修改下doGetAuthenticationInfo中的代码;）
	 * 可以扩展凭证匹配器，实现 输入密码错误次数后锁定等功能，下一次
	 * @param
	 * @author  lixueyun
	 * @Date  2019/4/19 22:26
	 * @return  org.apache.shiro.authc.credential.HashedCredentialsMatcher
	 */
	@Bean(name = "credentialsMatcher")
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		//散列算法:这里使用MD5算法;
		hashedCredentialsMatcher.setHashAlgorithmName("md5");
		//散列的次数，比如散列两次，相当于 md5(md5(""));
		hashedCredentialsMatcher.setHashIterations(2);
		//storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
		hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);
		return hashedCredentialsMatcher;
	}

	/**
	 * @methodName  lifecycleBeanPostProcessor
	 * @description  Shiro生命周期处理器
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

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
}
