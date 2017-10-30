package shop.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.OrderPOMapper;
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


    @Transactional(rollbackFor=Exception.class)
    public void createOrder(){
        OrderPO orderPO = new OrderPO();
        orderPO.setSellerId(1L);
        orderPO.setBuyerId("122");
        orderPO.setTotalPrice(new BigDecimal(20));
        orderPO.setPaymentStatus(2);
        orderPO.setOrderStatus(1);
        orderPO.setShippingStatus(0);
        orderPO.setOrderSn(GenerateNum.getInstance().GenerateOrder());
        orderPO.setReceiverId("se");
        orderPO.setActive(true);
        orderPOMapper.insert(orderPO);

    }


    public Map create(String str) {

        this.createOrder();

        return null;
    }

    public Map getOrderByToken(int token) {
        return null;
    }
}
