package shop.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by songningning1 on 2017/9/9.
 */
@Service
public class RedisUtilsImpl implements RedisUtils {

    @Autowired
    private JedisPool jedisPool;


    public void set(String key, String value) {
        Jedis jedis = this.getJedis();
        jedis.set(key, value);
        this.releaseJedis(jedis);
    }

    public void set(String key, String value, Integer seconds) {
        Jedis jedis = this.getJedis();
        jedis.set(key, value);
        jedis.expire(key, seconds);
        this.releaseJedis(jedis);
    }

    public String get(String key) {
        Jedis jedis = this.getJedis();
        String result = jedis.get(key);
        this.releaseJedis(jedis);
        return result;
    }

    public void del(String key) {
        Jedis jedis = this.getJedis();
        jedis.del(key);
        this.releaseJedis(jedis);
    }

    public void expire(String key, Integer seconds) {
        Jedis jedis = this.getJedis();
        jedis.expire(key, seconds);
        this.releaseJedis(jedis);
    }

    public Long incr(String key) {
        Jedis jedis = this.getJedis();
        Long count = jedis.incr(key);
        this.releaseJedis(jedis);
        return count;
    }

    public void setObject(String key, Object object) {
        Jedis jedis = this.getJedis();
        byte[] serStr = SerializationUtil.serialize(object);
        jedis.set(key, serStr.toString());
        this.releaseJedis(jedis);
    }

    public Object getObject(String key) {
        return null;
    }

    /**
     * 获取Jedis连接
     */
    public Jedis getJedis() {
        return this.jedisPool.getResource();
    }

    /**
     * 释放redis连接
     */
    public void releaseJedis(Jedis jedis) {
        jedis.disconnect();
    }
}
