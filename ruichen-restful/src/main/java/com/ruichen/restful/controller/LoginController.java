package com.ruichen.restful.controller;

import com.ruichen.restful.common.constant.ShiroConstant;
import com.ruichen.restful.common.enums.ErrorCodeEnum;
import com.ruichen.restful.common.exception.ShiroSpecialException;
import com.ruichen.restful.common.response.BaseResponse;
import com.ruichen.restful.common.response.BaseResponseBuilder;
import com.ruichen.restful.common.utils.AesCipherUtil;
import com.ruichen.restful.common.utils.JwtUtil;
import com.ruichen.restful.config.shiro.ShiroProperties;
import com.ruichen.restful.controller.request.LoginRequest;
import com.ruichen.restful.repository.mybatis.entity.UserEntity;
import com.ruichen.restful.service.IUserService;
import com.ruichen.restful.url.LoginUrl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName  LoginController
 * @Description
 * @Date  2019/4/4 16:06
 * @author  lixueyun
 */
@RestController
public class LoginController {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @ApiOperation(value = "用户登录", notes = "用户登录", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = LoginUrl.LOGIN, method = RequestMethod.POST)
    public BaseResponse<Object> login(@RequestBody @Validated LoginRequest request) {

        // 查询数据库中的帐号信息
        UserEntity userEntity = userService.getUserByAccount(request.getAccount());
        if (userEntity == null) {
            throw new ShiroSpecialException(ErrorCodeEnum.E101001, ErrorCodeEnum.E101001.getText());
        }

        // 密码进行AES解密
        String key = AesCipherUtil.deCrypto(userEntity.getPassword());
        // 因为密码加密是以帐号+密码的形式进行加密的，所以解密后的对比是帐号+密码
        if (key.equals(request.getAccount() + request.getPassword())) {
            // 清除可能存在的Shiro权限信息缓存
            Boolean isExists = redisTemplate.hasKey(ShiroConstant.PREFIX_SHIRO_CACHE + request.getAccount());
            if (isExists) {
                redisTemplate.delete(ShiroConstant.PREFIX_SHIRO_CACHE + request.getAccount());
            }
            // 设置RefreshToken，时间戳为当前时间戳，直接设置即可(不用先删后设，会覆盖已有的RefreshToken)
            String currentTimeMillis = String.valueOf(System.currentTimeMillis());
            redisTemplate.opsForValue().set(ShiroConstant.PREFIX_SHIRO_REFRESH_TOKEN + request.getAccount(), currentTimeMillis, ShiroProperties.REFRESH_TOKEN_EXPIRE_TIME, TimeUnit.SECONDS);
            // 从Header中Authorization返回AccessToken，时间戳为当前时间戳
            String token = JwtUtil.sign(request.getAccount(), currentTimeMillis);
            return new BaseResponseBuilder<String>().success().data(token).build();
        } else {
            throw new ShiroSpecialException(ErrorCodeEnum.E101001, ErrorCodeEnum.E101001.getText());
        }
    }
}
