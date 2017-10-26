package tyshop.mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by songningning1 on 2017/10/25.
 */
public interface OrderMapper {
    /**
     * 创建订单
     *
     * @param orderMapper
     * @return
     */
    int insert(OrderMapper orderMapper);

    /**
     * 根据主键获取订单
     * @param orderId
     * @return
     */
    OrderMapper getOrderById(int orderId);
}
