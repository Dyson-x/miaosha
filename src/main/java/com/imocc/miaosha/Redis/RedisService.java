package com.imocc.miaosha.Redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.security.Key;

/**
 * @author Dyson
 * @date 2019/8/2 17:07
 */
//通过RedisService提供Redis服务
@Service
public class RedisService {

    @Autowired
    JedisPool jedisPool;



    //通过JedisPool获取出Jedis客户端
    public <T> T get(KeyPrefix prefix, String key, Class<T> clazz) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //生成真正的key
            String realKey = prefix.getPrefix()+key;
            String str = jedis.get(realKey);
            T t =stringToBean(str,clazz);
            return t;
        } finally {
            //关闭连接池
            returnToPool(jedis);
        }
    }

    public <T> boolean set(KeyPrefix prefix,String key, T value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String str = beanToString(value);
            if(str == null || str.length()<=0){
                return false;
            }
            String realKey = prefix.getPrefix()+key;
            int seconds=prefix.expireSeconds();
            if(seconds<=0){
                //永不过期
                jedis.set(realKey,str);
            }else {
                //设置有效期
                jedis.setex(realKey,seconds,str);
            }
            jedis.set(realKey,str);
            return true;
        } finally {
            //关闭连接池
            returnToPool(jedis);
        }
    }
    //判断key是否存在
    public <T> boolean exists(KeyPrefix prefix,String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            return jedis.exists(realKey);
        } finally {
            //关闭连接池
            returnToPool(jedis);
        }
    }
    //增加值（原子操作）
    public <T> Long incr(KeyPrefix prefix,String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            return jedis.incr(realKey);
        } finally {
            //关闭连接池
            returnToPool(jedis);
        }
    }
    //删除值（操作）
    public <T> Long decr(KeyPrefix prefix,String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix()+key;
            return jedis.decr(realKey);
        } finally {
            //关闭连接池
            returnToPool(jedis);
        }
    }
    private <T> String beanToString(T value) {
        if(value==null){
            return null;
        }
        Class<?> clazz = value.getClass();
        if(clazz == int.class || clazz == Integer.class){
            return ""+value;
        }else if(clazz == String.class){
            return (String)value;
        }else if(clazz == long.class || clazz == Long.class){
            return ""+value;
        }else {
            return JSON.toJSONString(value);
        }
    }
    //将一个字符串转化为一个Bean对象
    private <T> T stringToBean(String str,Class<T> clazz) {
        if(str == null||str.length()<=0){
            return null;
        }
        if(clazz == int.class || clazz == Integer.class){
            return (T)Integer.valueOf(str);
        }else if(clazz == String.class){
            return (T)str;
        }else if(clazz == long.class || clazz == Long.class){
            return (T)Long.valueOf(str);
        }else {
            return JSON.toJavaObject(JSON.parseObject(str),clazz);
        }
    }

    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            //如果使用的是连接池，并不会直接关闭而是返回到连接池中
            jedis.close();
        }
    }



}
