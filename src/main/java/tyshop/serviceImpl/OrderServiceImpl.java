package tyshop.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tyshop.mapper.OrderMapper;
import tyshop.model.OrderModel;
import tyshop.service.OrderService;
import tyshop.utils.GenerateNum;
import tyshop.utils.RedisUtils;

import java.util.Map;

/**
 * Created by songningning1 on 2017/10/17.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private OrderMapper orderMapper;


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
            orderMapper.insert(orderModel);
    }


    public Map create(String str) {
        return null;
    }

    public Map getOrderByToken(int token) {
        return null;
    }
}
