package shop.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.OrderItemPOMapper;
import shop.dao.OrderPOMapper;
import shop.pojo.OrderItemPO;
import shop.pojo.OrderPO;
import shop.service.OrderService;
import shop.utils.GenerateNum;
import shop.utils.RedisUtils;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by songningning1 on 2017/10/17.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private OrderPOMapper orderPOMapper;
    @Autowired
    private OrderItemPOMapper orderItemPOMapper;

    public Map create(String str) {
        OrderPO orderPO = new OrderPO();
        orderPO.setSellerId(1L);
        orderPO.setBuyerId("4545");
        orderPO.setTotalPrice(new BigDecimal(20));
        orderPO.setPaymentStatus(2);
        orderPO.setOrderStatus(1);
        orderPO.setShippingStatus(0);
        orderPO.setOrderSn(GenerateNum.getInstance().GenerateOrder());
        orderPO.setReceiverId("se");
        orderPO.setActive(true);
        this.insertOrderTransactional(orderPO, new OrderItemPO());
        return null;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void insertOrderTransactional(OrderPO op, OrderItemPO itemPO){
        orderPOMapper.insert(op);
    }

    public Map getOrderByToken(int token) {
        return null;
    }
}
