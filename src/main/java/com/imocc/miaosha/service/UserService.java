package com.imocc.miaosha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imocc.miaosha.dao.UserDao;
import com.imocc.miaosha.domain.User;

/**
 * @author Dyson
 * @date 2019/7/30 18:21
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public User getById(int id) {
        return userDao.getById(id);
    }

}
