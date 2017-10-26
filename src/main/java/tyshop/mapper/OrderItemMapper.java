package tyshop.mapper;

import tyshop.model.OrderItemModel;

import java.util.Map;

/**
 * Created by songningning1 on 2017/10/25.
 */
public interface OrderItemMapper {
    /**
     * 创建订单
     *
     * @param orderItemModel
     * @return
     */
    Map insert(OrderItemModel orderItemModel);

    /**
     * 根据用户名token，查询订单
     * @param sellerId
     * @return
     */
    Map listOrderByOderId(String sellerId);
}
