package com.imocc.miaosha.Redis;

/**
 * @author Dyson
 * @date 2019/8/4 15:39
 */
public class OrderKey extends BasePrefix {

    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
