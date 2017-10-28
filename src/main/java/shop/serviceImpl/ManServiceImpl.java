package shop.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.service.ManService;
import shop.utils.RedisUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by songningning1 on 2017/10/13.
 */
@Service
public class ManServiceImpl implements ManService {

    @Autowired
    private RedisUtils redisUtils;


    public Map cleanRedis(String key) {
        Map map = new HashMap();
        try {
            redisUtils.del(key);
        } catch (Exception e) {
            map.put("Meg", "删除缓存失败，key:" + key);
        }
        map.put("Meg", "删除缓存成功，Key：" + key);
        return map;
    }

}
