package shop.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.dao.OrderItemPOMapper;
import shop.mapper.OrderMapper;
import shop.model.OrderModel;
import shop.service.OrderService;
import shop.utils.GenerateNum;
import shop.utils.RedisUtils;

import java.util.Map;

/**
 * Created by songningning1 on 2017/10/17.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private OrderItemPOMapper orderItemPOMapper;


    @Transactional(rollbackFor=Exception.class)
    public void createOrder(){
        OrderModel orderModel = new OrderModel();
            orderModel.setBuyerId("122");
            orderModel.setTotalPrice(20);
            orderModel.setOpendId("first");
            orderModel.setPayStatus(0);
            orderModel.setOrderStatus(1);
            orderModel.setShippingStatus(0);
            orderModel.setOrderId(GenerateNum.getInstance().GenerateOrder());
            orderModel.setReceiverId("se");
            //orderItemPOMapper.insert();
    }


    public Map create(String str) {
        return null;
    }

    public Map getOrderByToken(int token) {
        return null;
    }
}
