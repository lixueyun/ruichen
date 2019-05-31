package com.ruichen.restful.common.constants;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * @ClassName  ShiroConstants
 * @Description  shiro常量类
 * @Date  2019/4/20 14:23
 * @author  lixueyun
 * @version  V1.0
 */
public class ShiroConstants {

    /**
     * 加盐参数
     */
    public final static String HASH_ALGORITHM_NAME = "MD5";

    /**
     * 循环次数
     */
    public final static int HASH_ITERATIONS = 1024;

    /**
     * @methodName  getSubject
     * @description 获取当前 subject
     * @param
     * @author  lixueyun
     * @Date  2019/5/31 10:40
     * @return  org.apache.shiro.subject.Subject
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }


    /**
     * @methodName  getSession
     * @description 从shiro获取session
     * @param
     * @author  lixueyun
     * @Date  2019/5/31 10:41
     * @return  org.apache.shiro.session.Session
     */
    public static Session getSession() {
        return getSubject().getSession();
    }

}
