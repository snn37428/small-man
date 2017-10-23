package tyshop.utils;

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

    @Override
    public void set(String key, String value) {
        Jedis jedis = this.getJedis();
        jedis.set(key, value);
        this.releaseJedis(jedis);
    }

    @Override
    public void set(String key, String value, Integer seconds) {
        Jedis jedis = this.getJedis();
        jedis.set(key, value);
        jedis.expire(key, seconds);
        this.releaseJedis(jedis);
    }

    @Override
    public String get(String key) {
        Jedis jedis = this.getJedis();
        String result = jedis.get(key);
        this.releaseJedis(jedis);
        return result;
    }

    @Override
    public void del(String key) {
        Jedis jedis = this.getJedis();
        jedis.del(key);
        this.releaseJedis(jedis);
    }

    @Override
    public void expire(String key, Integer seconds) {
        Jedis jedis = this.getJedis();
        jedis.expire(key, seconds);
        this.releaseJedis(jedis);
    }

    @Override
    public Long incr(String key) {
        Jedis jedis = this.getJedis();
        Long count = jedis.incr(key);
        this.releaseJedis(jedis);
        return count;
    }

    @Override
    public void setObject(String key, Object object) {
        Jedis jedis = this.getJedis();
        byte[] serStr = SerializationUtil.serialize(object);
        jedis.set(key, serStr.toString());
        this.releaseJedis(jedis);
    }

    @Override
    public Object getObject(String key) {
        Jedis jedis = this.getJedis();
        String strRs = jedis.get(key);
        if (StringUtils.isEmpty(strRs)) {
            return null;
        }
        Object obj = SerializationUtil.deserialize(strRs.getBytes());
        this.releaseJedis(jedis);
        return obj;
    }

    public void incrOrder(String key) {

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
