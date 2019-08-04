package com.imocc.miaosha.controller;


import com.imocc.miaosha.Redis.KeyPrefix;
import com.imocc.miaosha.Redis.RedisService;
import com.imocc.miaosha.Redis.UserKey;
import com.imocc.miaosha.domain.User;
import com.imocc.miaosha.result.Result;
import com.imocc.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Dyson
 * @date 2019/7/30 15:38
 */
@Controller
@RequestMapping("/demo")
public class SampleController {
    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","Joshua");
        return "hello";
    }

    //测试数据访问
    @Autowired
    UserService userService;
    @ResponseBody
    @RequestMapping("/db/get")
    public Result<User> dbGet(){
        User user = userService.getById(1);
        return Result.success(user);
    }

    @Autowired
    RedisService  redisService;
    @ResponseBody
    @RequestMapping("/redis/get")
    public Result<User> redisGet(){
        User user = redisService.get(UserKey.getById,""+1,User.class);
        return Result.success(user);
    }
    @ResponseBody
    @RequestMapping("/redis/set")
    public Result<Boolean> redisSet(){
        User user = new User();
        user.setId(1);
        user.setName("dyson");
        redisService.set(UserKey.getById,"key2",user);
        return Result.success(true);
    }


}
