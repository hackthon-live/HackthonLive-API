package com.iydsj.sw.common.redis;

import redis.clients.jedis.JedisPubSub;

import java.util.List;
import java.util.Set;

/**
 * @author yanyan.wang
 * @date 2016-01-03 23:30
 */
public interface RedisService {

    /**
     * get the value by key
     *
     * @param key the key
     * @return the value
     */
    String get(String key);

    /**
     * set the key,value
     *
     * @param key   the key
     * @param value the value
     * @return the value
     */
    String set(String key, String value);

    /**
     * inc by value
     *
     * @param key   the value
     * @param value the value
     * @return
     */
    long incrBy(String key, long value);

    /**
     * inc by value
     *
     * @param key     the key
     * @param value   the value
     * @param seconds the seconds
     * @return
     */
    long incrBy(String key, long value, int seconds);

    /**
     * get the value by key
     *
     * @param key
     * @return
     */
    byte[] get(byte[] key);

    /**
     * set the key-value
     *
     * @param key
     * @param value
     * @return
     */
    byte[] set(byte[] key, byte[] value);

    /**
     * set the key-value
     *
     * @param key
     * @param value
     * @param expire
     * @return
     */
    byte[] set(byte[] key, byte[] value, int expire);

    /**
     * del the key
     *
     * @param key
     */
    void del(byte[] key);

    /**
     * get the keys
     *
     * @param pattern
     * @return
     */
    Set<byte[]> keys(String pattern);

    /**
     * del the keys
     *
     * @param keypattern
     */
    void del(String keypattern);

    /**
     * push message
     *
     * @param channel the channel
     * @param message the message
     * @return
     */
    Long publish(byte[] channel, byte[] message);

    /**
     * the subcribe
     *
     * @param jedisPubSub
     * @param channels
     */
    void subscribe(JedisPubSub jedisPubSub, String... channels);

    /**
     * 把值插入到链表头部
     * 如果key不存在将创建一个新的list
     * @param key
     * @param values
     * @return
     */
    Long lpush(String  key, String... values);

    /**
     * 把值插入到链表尾部
     * @param key
     * @param values
     * @return
     */
    Long rpush(String key, String... values);


    /**
     * 返回并删除链表头部的元素
     * @param key
     * @return
     */
    String lpop(String key);

    /**
     * 返回并删除链表尾部的元素
     * @param key
     * @return
     */
    String rpop(String key);

    /**
     * 返回链表中[start,end]中的元素
     * @param key
     * @param start
     * @param end
     * @return
     */
    List<String> lrange(String key , Long start,Long end);

    /**
     * 从链表删除value值，删除count绝对值个value后结束
     * count > 0从表头开始 count < 0 从表尾开始 count = 0 全部删除
     * @param key
     * @param count
     * @param value
     * @return
     */
    Long lrem(String key,Long count,String value);

    /**
     * 剪切list中[start,end]中的元素并将其重新赋值给list
     * @param key
     * @param start
     * @param end
     * @return
     */
    String ltrim(String key,Long start,Long end);

    /**
     * 返回链表中index元素
     * @param key
     * @param index
     * @return
     */
    String lindex(String key , Long index);


    /**
     * 返回链表长度
     * @param key
     * @return
     */
    Long llen(String key);

}
