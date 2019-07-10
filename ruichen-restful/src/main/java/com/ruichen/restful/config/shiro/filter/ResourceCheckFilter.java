package com.ruichen.restful.config.shiro.filter;

import com.ruichen.restful.common.enums.ErrorCodeEnum;
import com.ruichen.restful.common.exception.ShiroSpecialException;
import com.ruichen.restful.common.utils.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @ClassName  ResourceCheckFilter
 * @Description 鉴权请求URL访问权限拦截器
 * @author  lixueyun
 * @Date  2019/7/8 14:51
 */
@Slf4j
public class ResourceCheckFilter extends AccessControlFilter {

   /**
    * @methodName  isAccessAllowed
    * @description  表示是否允许访问 ，如果允许访问返回true，否则false
    * @param request
    * @param response
    * @param o  表示写在拦截器中括号里面的字符串 mappedValue 就是 [urls] 配置中拦截器参数部分
    * @author  lixueyun
    * @Date  2019/7/8 14:52
    * @return  boolean
    */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object o) throws Exception {
        Subject subject = getSubject(request,response);
        String url = getPathWithinApplication(request);
        log.info("当前用户正在访问的url:{}", url);
        try {
            //委托realm类授权认证
            boolean permitted = subject.isPermitted(url);
            if (!permitted) {
                log.error(ErrorCodeEnum.E101005.getText() + "\n" + ErrorCodeEnum.E101005.getText());
                JwtUtil.responseMessage(response, ErrorCodeEnum.E101005.getValue(), ErrorCodeEnum.E101005.getText());
                return false;
            }
            return permitted;
        }catch (Exception e){
            e.printStackTrace();
            Throwable throwable = e.getCause();
            if (throwable instanceof ShiroSpecialException) {
                ShiroSpecialException shiroSpecialException = (ShiroSpecialException) throwable;
                JwtUtil.responseMessage(response, shiroSpecialException.getBaseEnum().getValue(), shiroSpecialException.getMessage());
                return false;
            }
            log.error(ErrorCodeEnum.E101005.getText() + "\n" + e.getMessage());
            JwtUtil.responseMessage(response, ErrorCodeEnum.E101005.getValue(), ErrorCodeEnum.E101005.getText());
            return false;
        }
    }


    /**
     * @methodName  onAccessDenied
     * @description 什么也不用做
     * @param request
     * @param response
     * @author  lixueyun
     * @Date  2019/7/8 14:53
     * @return  boolean
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        return false;
    }

}