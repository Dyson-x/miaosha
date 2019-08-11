package com.imocc.miaosha.controller;


import com.alibaba.druid.util.StringUtils;
import com.imocc.miaosha.result.CodeMsg;
import com.imocc.miaosha.result.Result;
import com.imocc.miaosha.service.MiaoshaUserService;
import com.imocc.miaosha.util.VaildatorUtil;
import com.imocc.miaosha.vo.LoginVo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;


/**
 * @author Dyson
 * @date 2019/8/11 8:13
 */
@Controller
@RequestMapping("/login")
public class LoginController {


    @Autowired
    MiaoshaUserService userService;

    private static org.slf4j.Logger log = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<Boolean> doLogin(@Valid LoginVo loginVo){
        log.info(loginVo.toString());
        //参数校验
//        String passInput = loginVo.getPassword();
//        String mobile = loginVo.getMobile();
//        if(StringUtils.isEmpty(passInput)){
//            return Result.error(CodeMsg.PASSWORD_EMPTY);
//        }
//        if(StringUtils.isEmpty(mobile)){
//            return Result.error(CodeMsg.MOBILE_EMPTY);
//        }
//        if(!VaildatorUtil.isMobile(mobile)){
//            return Result.error(CodeMsg.MOBILE_ERROR);
//        }
        //登录
        Boolean ret = userService.login(loginVo);
        return Result.success(true);
    }


}
