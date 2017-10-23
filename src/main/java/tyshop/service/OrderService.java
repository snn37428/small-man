package tyshop.service;

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
     * 根据用户名id，查询订单
     * @param id
     * @return
     */
    Map selectOrderById(int id);
}
