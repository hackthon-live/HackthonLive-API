package com.iydsj.sw.common.redis.impl;

import com.iydsj.sw.common.redis.RedisService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPubSub;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author yanyan.wang
 * @date 2016-01-03 23:32
 */
@Service("redisService")
public class RedisServiceImpl implements RedisService {

    @Resource
    private JedisPool jedisPool;


    @Override
    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.get(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            return value;
        } finally {
            jedis.close();
        }
    }

    @Override
    public long incrBy(String key, long value) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.incrBy(key, value);
        } finally {
            jedis.close();
        }
    }

    @Override
    public long incrBy(String key, long value, int seconds) {
        Jedis jedis = jedisPool.getResource();
        try {
            long val = jedis.incrBy(key, value);
            if (val == value) {
                jedis.expire(key, seconds);
            }
            return val;
        } finally {
            jedis.close();
        }
    }

    @Override
    public byte[] get(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.get(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public byte[] set(byte[] key, byte[] value) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            return value;
        } finally {
            jedis.close();
        }
    }

    @Override
    public byte[] set(byte[] key, byte[] value, int expire) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.set(key, value);
            if (expire != 0) {
                jedis.expire(key, expire);
            }
            return value;
        } finally {
            jedis.close();
        }
    }

    @Override
    public void del(byte[] key) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.del(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Set<byte[]> keys(String pattern) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.keys(pattern.getBytes());
        } finally {
            jedis.close();
        }
    }

    @Override
    public void del(String keypattern) {
        Jedis jedis = jedisPool.getResource();
        try {
            Set<String> keys = jedis.keys(keypattern);
            jedis.del(keys.toArray(new String[keys.size()]));
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long publish(byte[] channel, byte[] message) {
        Jedis jedis = jedisPool.getResource();
        try {
            Long id = jedis.publish(channel, message);
            return id;
        } finally {
            jedis.close();
        }
    }

    @Override
    public void subscribe(JedisPubSub jedisPubSub, String... channels) {
        Jedis jedis = jedisPool.getResource();
        try {
            jedis.subscribe(jedisPubSub, channels);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long lpush(String key, String... values) {
        Jedis jedis = jedisPool.getResource();
        try {
           return jedis.lpush(key,values);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long rpush(String key, String... values) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.rpush(key,values);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String lpop(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.lpop(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String rpop(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.rpop(key);
        } finally {
            jedis.close();
        }
    }

    @Override
    public List<String> lrange(String key, Long start, Long end) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.lrange(key,start,end);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long lrem(String key, Long count, String value) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.lrem(key,count,value);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String ltrim(String key, Long start, Long end) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.ltrim(key,start,end);
        } finally {
            jedis.close();
        }
    }

    @Override
    public String lindex(String key, Long index) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.lindex(key,index);
        } finally {
            jedis.close();
        }
    }

    @Override
    public Long llen(String key) {
        Jedis jedis = jedisPool.getResource();
        try {
            return jedis.llen(key);
        } finally {
            jedis.close();
        }
    }
}
