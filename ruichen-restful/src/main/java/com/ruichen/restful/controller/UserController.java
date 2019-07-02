package com.ruichen.restful.controller;

import com.ruichen.restful.common.constant.ShiroConstant;
import com.ruichen.restful.common.enums.ErrorCodeEnum;
import com.ruichen.restful.common.exception.ControllerException;
import com.ruichen.restful.common.response.BaseResponse;
import com.ruichen.restful.common.response.BaseResponseBuilder;
import com.ruichen.restful.common.utils.AesCipherUtil;
import com.ruichen.restful.controller.request.UserSaveRequest;
import com.ruichen.restful.repository.mybatis.entity.UserEntity;
import com.ruichen.restful.service.IUserService;
import com.ruichen.restful.url.UserUrl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName  UserController
 * @Description
 * @Date  2019/7/2 15:29
 * @author  lixueyun
 */
@RestController
@Api(value = "UserController", tags = "用户管理")
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "新增用户", notes = "新增用户", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParam(name = ShiroConstant.AUTHORIZATION_HEADER, value = ShiroConstant.AUTHORIZATION_VALUE, paramType = "header", dataType = "String", required = true)
    @RequestMapping(value = UserUrl.USER_SAVE, method = RequestMethod.POST)
    public BaseResponse<Object> userSave(@RequestBody @Validated UserSaveRequest request) {

        UserEntity userEntity = userService.getUserByAccount(request.getAccount());

        if (userEntity != null) {
            throw new ControllerException(ErrorCodeEnum.E101010, ErrorCodeEnum.E101010.getText());
        }
        //获取加密password
        String password = AesCipherUtil.enCrypto(request.getAccount() + request.getPassword());
        request.setPassword(password);

        userEntity = new UserEntity();
        BeanUtils.copyProperties(request, userEntity);

        userService.save(userEntity);
        return new BaseResponseBuilder<String>().success().build();
    }
}
