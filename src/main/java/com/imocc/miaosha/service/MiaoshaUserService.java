package com.imocc.miaosha.service;

import com.imocc.miaosha.dao.MiaoshaUserDao;
import com.imocc.miaosha.domain.MiaoshaUser;
import com.imocc.miaosha.exception.GlobalException;
import com.imocc.miaosha.result.CodeMsg;
import com.imocc.miaosha.util.MD5Util;
import com.imocc.miaosha.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dyson
 * @date 2019/8/11 10:05
 */
@Service
public class MiaoshaUserService {

    @Autowired
    MiaoshaUserDao miaoshaUserDao;


    public MiaoshaUser getById(long id){
        return miaoshaUserDao.getById(id);
    }

    public boolean login(LoginVo loginVo) {
        //出现异常直接抛出即可
        if(loginVo == null){
            throw new GlobalException( CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        //判断手机号是否存在
        MiaoshaUser user = getById(Long.parseLong(mobile));
        if(user == null){
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        }
        //验证密码
        String dbPass = user.getPassword();
        String saltDb = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass,saltDb);
        if(calcPass.equals(dbPass)){
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        }
        return true;
    }
}
