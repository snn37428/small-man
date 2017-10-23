package tyshop.service;

import java.util.Map;

/**
 * Created by songningning1 on 2017/10/13.
 */
public interface ManService {

    /**
     * 通过key清除缓存
     *
     * @param key
     * @return
     */
    Map cleanRedis(String key);

}
