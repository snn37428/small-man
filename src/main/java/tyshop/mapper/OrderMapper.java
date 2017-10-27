package tyshop.mapper;

import org.springframework.stereotype.Repository;
import tyshop.model.OrderModel;

import java.util.List;
import java.util.Map;

/**
 * Created by songningning1 on 2017/10/25.
 */
@Repository("orderMapper")
public interface OrderMapper {
    /**
     * 创建订单
     *
     * @param orderModel
     * @return
     */
    int insert(OrderModel orderModel);

    /**
     * 根据主键获取订单
     * @param orderId
     * @return
     */
    OrderMapper getOrderById(int orderId);
}
