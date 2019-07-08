package com.ruichen.restful.config.shiro;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @ClassName  ShiroProperties
 * @Description  shiro配置实体类
 * @Date  2019/7/1 22:23
 * @author  lixueyun
 * @version  V1.0
 */
@Slf4j
@Component
public class ShiroProperties {

    /**
     * AES密码加密私钥(Base64加密)
     */
    public static String ENCRYPT_AES_KEY;

    /**
     * JWT认证加密私钥(Base64加密)
     */
    public static String ENCRYPT_JWT_KEY;

    /**
     * 过期时间
     */
    public static String ACCESS_TOKENEXPIRE_TIME;

    /**
     * RefreshToken过期时间-30分钟-30*60(秒为单位)
     */
    public static String REFRESH_TOKEN_EXPIRE_TIME;

    /**
     * Shiro缓存过期时间-5分钟-5*60(秒为单位)(一般设置与AccessToken过期时间一致)
     */
    public static String SHIRO_CACHE_EXPIRE_TIME;

    @Value("${shiro.encryptAesKey}")
    public void setEncryptAesKey(String encryptAesKey) {
        ENCRYPT_AES_KEY = encryptAesKey;
    }

    @Value("${shiro.encryptJwtKey}")
    public void setEncryptJwtKey(String encryptJwtKey) {
        ENCRYPT_JWT_KEY = encryptJwtKey;
    }

    @Value("${shiro.accessTokenExpireTime}")
    public void setAccessTokenexpireTime(String accessTokenExpireTime) {
        ACCESS_TOKENEXPIRE_TIME = accessTokenExpireTime;
    }

    @Value("${shiro.refreshTokenExpireTime}")
    public void setRefreshTokenExpireTime(String refreshTokenExpireTime) {
        REFRESH_TOKEN_EXPIRE_TIME = refreshTokenExpireTime;
    }

    @Value("${shiro.shiroCacheExpireTime}")
    public void setShiroCacheExpireTime(String shiroCacheExpireTime) {
        SHIRO_CACHE_EXPIRE_TIME = shiroCacheExpireTime;
    }
}
