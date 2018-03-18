package shop.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 类说明
 *
 * @author: songningning
 * @date: created in 2018/3/18 19:25
 * @modified: by
 */
public class Test {
@Autowired
    private OrderPOMapper orderPOMapper;

    public void test1 () {
        OrderPO se = new OrderPO();
        se.setPlcId(11);
        orderPOMapper.insert(se);
        System.out.println("8");
    }

    public OrderPOMapper getOrderPOMapper() {
        return orderPOMapper;
    }

    public void setOrderPOMapper(OrderPOMapper orderPOMapper) {
        this.orderPOMapper = orderPOMapper;
    }
}
