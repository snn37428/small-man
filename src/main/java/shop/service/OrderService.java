package shop.service;

import shop.pojo.Auc;
import shop.vtp.OrderVtp;
import shop.vtp.PayOrderVtp;

import java.util.Map;

/**
 * Created by songningning1 on 2017/10/17.
 */
public interface OrderService {
    /**
     * 创建订单
     *
     * @param orderVtp
     * @return
     */
    Map create(OrderVtp orderVtp);

    /**
     * 查询订单
     *
     * @param auc
     * @return
     */
    Map list(Auc auc);
}
