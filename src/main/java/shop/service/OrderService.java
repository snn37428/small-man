package shop.service;

import java.util.Map;

/**
 * Created by songningning1 on 2017/10/17.
 */
public interface OrderService {
    /**
     * 创建订单
     * @param str
     * @return
     */
    Map create(String str);

    /**
     * 根据用户名token，查询订单
     * @param token
     * @return
     */
    Map getOrderByToken(int token);
}
