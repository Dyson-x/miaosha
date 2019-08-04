package com.imocc.miaosha.Redis;

/**
 * @author Dyson
 * @date 2019/8/4 15:32
 */
public interface KeyPrefix {
    //有效期
    public int expireSeconds();

    public String getPrefix();
}
