package shop.utils;

/**
 * Created by songningning1 on 2017/9/9.
 */
public class RedisCacheTest {

//    @Autowired
//    private RedisUtils redisUtils;
//
//
//    public String queryByRedis() {
//        String key = "redis";
//        // 1.从缓存中命中
//        try {
//            String redisJson = redisUtils.get(key);
//            if (StringUtils.isEmpty(redisJson)) {
//                return redisJson;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // 2.如果没用命中，执行原有逻辑，从数据库中获取数据
//        // 未实现，从数据库中获取数据
//      //  String json = getFromDB();
//        String json ="";
//        // 3.将查询出来的结果加入缓存
//        try {
//            this.redisUtils.set(key, json);
//            this.redisUtils.expire(key, 60 * 60 * 24);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return json;
//    }
}
