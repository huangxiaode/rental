package edu.dongnao.rental.web.controller;

import org.apache.dubbo.config.annotation.Reference;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.dongnao.rental.lang.ApiResponse;
import edu.dongnao.rental.lang.ServiceResult;
import edu.dongnao.rental.uc.api.ISmsService;
import edu.dongnao.rental.uc.api.IUserService;
import edu.dongnao.rental.web.base.LoginUserUtil;
/**
 * 用户访问控制层
 */
@Controller
public class UserController {
    @Reference
    private IUserService userService;
    
    @Reference
    private ISmsService smsService;

    @GetMapping("/user/login")
    public String loginPage() {
        return "user/login";
    }

    @GetMapping("/user/center")
    public String centerPage() {
        return "user/center";
    }

    @PostMapping(value = "api/user/info")
    @ResponseBody
    public ApiResponse updateUserInfo(@RequestParam(value = "profile") String profile,
                                      @RequestParam(value = "value") String value) {
        if (value.isEmpty()) {
            return ApiResponse.ofStatus(ApiResponse.Status.BAD_REQUEST);
        }

        if ("email".equals(profile) && !LoginUserUtil.checkEmail(value)) {
            return ApiResponse.ofMessage(HttpStatus.SC_BAD_REQUEST, "不支持的邮箱格式");
        }

        ServiceResult<Boolean> result = userService.modifyUserProfile(profile, value, LoginUserUtil.getLoginUserId());
        if (result.isSuccess()) {
            return ApiResponse.ofSuccess("");
        } else {
            return ApiResponse.ofMessage(HttpStatus.SC_BAD_REQUEST, result.getMessage());
        }

    }
    
    @GetMapping(value = "sms/code")
    @ResponseBody
    public ApiResponse smsCode(@RequestParam("telephone") String telephone) {
        if (!LoginUserUtil.checkTelephone(telephone)) {
            return ApiResponse.ofMessage(org.springframework.http.HttpStatus.BAD_REQUEST.value(), "请输入正确的手机号");
        }
        ServiceResult<String> result = smsService.sendSms(telephone);
        if (result.isSuccess()) {
            return ApiResponse.ofSuccess("");
        } else {
            return ApiResponse.ofMessage(org.springframework.http.HttpStatus.BAD_REQUEST.value(), result.getMessage());
        }

    }
}
