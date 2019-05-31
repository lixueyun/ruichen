package com.ruichen.restful.config.shiro;

import com.ruichen.restful.config.shiro.cache.ShiroRedisCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.ShiroHttpSession;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

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
	private ShiroRedisCacheManager shiroRedisCacheManager;


	@Bean(name = "rememberMeCookie")
	public SimpleCookie rememberMeCookie() {
		// 这里的Cookie的默认名称是 CookieRememberMeManager.DEFAULT_REMEMBER_ME_COOKIE_NAME
		SimpleCookie cookie = new SimpleCookie(CookieRememberMeManager.DEFAULT_REMEMBER_ME_COOKIE_NAME);
		cookie.setHttpOnly(true);
		// 设置 cookie 的过期时间，单位为秒，这里为7天
		cookie.setMaxAge(7 * 24 * 60 * 60);
		return cookie;
	}

	/**
	 * @methodName  rememberMeManager
	 * @description  rememberMe管理器	cipherKey生成见{@code Base64Test.java}
	 * @param rememberMeCookie
	 * @author  lixueyun
	 * @Date  2019/5/30 20:13
	 * @return  org.apache.shiro.web.mgt.CookieRememberMeManager
	 */
	@Bean(name = "rememberMeManager")
	public CookieRememberMeManager rememberMeManager(SimpleCookie rememberMeCookie) {
		CookieRememberMeManager manager = new CookieRememberMeManager();
		manager.setCipherKey(Base64.decode("cnVpY2hlbgAAAAAAAAAAAA=="));
		manager.setCookie(rememberMeCookie);
		return manager;
	}

	/**
	 * @methodName  sessionManager
	 * @description  session 管理对象
	 * @param
	 * @author  lixueyun
	 * @Date  2019/5/30 22:48
	 * @return  org.apache.shiro.web.session.mgt.DefaultWebSessionManager
	 */
	@Bean(name = "sessionManager")
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setCacheManager(shiroRedisCacheManager);
		// session 验证失效时间（默认为15分钟 单位：秒）
		sessionManager.setSessionValidationInterval(15 * 60 * 1000);
     	// session 失效时间（默认为30分钟 单位：秒）
		sessionManager.setGlobalSessionTimeout(30 * 60 * 1000);

		Cookie cookie = new SimpleCookie(ShiroHttpSession.DEFAULT_SESSION_ID_NAME);
		cookie.setName("shiroCookie");
		cookie.setHttpOnly(true);
		sessionManager.setSessionIdCookie(cookie);
		return sessionManager;
	}

	/**
	 * @methodName  securityManager
	 * @description	配置各种manager,跟xml的配置很像，但是，这里有一个细节，就是各个set的次序不能乱
	 * @param
	 * @author  lixueyun
	 * @Date  2019/4/19 22:25
	 * @return  org.apache.shiro.mgt.SecurityManager
	 */
	@Bean(name = "securityManager")
	@DependsOn("userRealm")
	public SecurityManager securityManager(CookieRememberMeManager rememberMeManager, SessionManager sessionManager, UserRealm userRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		// 记住密码 查看源码可以知道，这里的rememberMeManager就仅仅是一个赋值，所以先执行
		securityManager.setRememberMeManager(rememberMeManager);
		//配置 缓存管理类 cacheManager，这个cacheManager必须要在前面执行，
		// 因为setRealm 和 setSessionManage都有方法初始化了cachemanager,看下源码就知道了
		securityManager.setCacheManager(shiroRedisCacheManager);
		// 配置 SecurityManager，并注入 shiroRealm 这个跟springmvc集成很像
		securityManager.setRealm(userRealm);
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
//		Map<String, Filter> filterMap = new LinkedHashMap<>();
		// shiro有一些默认的拦截器 比如authc，它就是FormAuthenticationFilter表单拦截器 可以自定义拦截器放在这
		// filterMap.put("authc", new AjaxPermissionsAuthorizationFilter());
		//覆盖默认的user拦截器(默认拦截器解决不了ajax请求 session超时的问题)
		//filterMap.put("user", new GunsUserFilter());
//		shiroFilterFactoryBean.setFilters(filterMap);

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
		filterChainDefinitionMap.put("/swagger-ui.html", "anon");
		filterChainDefinitionMap.put("/swagger-resources/**", "anon");
		filterChainDefinitionMap.put("/v2/api-docs/**", "anon");
		filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**", "anon");
		filterChainDefinitionMap.put("/configuration/security", "anon");
		filterChainDefinitionMap.put("/configuration/ui", "anon");
		filterChainDefinitionMap.put("/webjars/**", "anon");
		filterChainDefinitionMap.put("/doc.html", "anon");
		filterChainDefinitionMap.put("/druid/**", "anon");
		filterChainDefinitionMap.put("/login", "anon");
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/**", "authc");
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
