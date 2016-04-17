package com.iydsj.sw.common.test;

import org.junit.Test;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;

/**
 * @author yanyan.wang
 * @date 2016-01-03 11:47
 */
@ContextConfiguration(locations = "classpath:config/spring/application-*.xml")
public class RedisTest extends AbstractJUnit4SpringContextTests {

    @Resource
    private JedisPool jedisPool;

    @Resource
    private PropertyPlaceholderConfigurer redisPropertyConfigurer;

    @Test
    public void test(){
        Jedis jedis=jedisPool.getResource();
        jedis.set("test","test");
    }
}
