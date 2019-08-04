package com.imocc.miaosha.Redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author Dyson
 * @date 2019/8/4 10:58
 */
@Service
public class RedisPoolFactory {
    @Autowired
    RedisConfig redisConfig;

    //将JedisPool加载到Spring容器中
    @Bean
    public JedisPool JedisPoolFactoty() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(redisConfig.getPoolMaxIdle());
        poolConfig.setMaxTotal(redisConfig.getPoolMaxTotal());
        //将毫秒单位转化为秒级单位
        poolConfig.setMaxIdle(redisConfig.getPoolMaxWait() * 1000);
        //配置JedisPool的信息
        JedisPool jp = new JedisPool(poolConfig, redisConfig.getHost(), redisConfig.getPort(),
                redisConfig.getTimeout() * 1000, redisConfig.getPassword(), 0);
        return jp;
    }
}
