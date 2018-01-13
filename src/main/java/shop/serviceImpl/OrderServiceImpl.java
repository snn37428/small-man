package shop.serviceImpl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import shop.base.BaseMap.ResMap;
import shop.dao.OrderItemPOMapper;
import shop.dao.OrderPOMapper;
import shop.pojo.OrderItemPO;
import shop.pojo.OrderPO;
import shop.service.OrderService;
import shop.utils.GenerateNum;
import shop.utils.RedisUtils;
import shop.vtp.PayOrderVtp;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by songningning1 on 2017/10/17.
 */
@Service("OrderServiceImpl")
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = Logger.getLogger(OrderServiceImpl.class);

    @Autowired
    private RedisUtils redisUtils;
    @Resource(name = "orderPOMapper")
    private OrderPOMapper orderPOMapper;

    @Resource(name = "OrderItemPOMapper")
    private OrderItemPOMapper orderItemPOMapper;

    public Map create(PayOrderVtp payOrderVtp) {

        if (payOrderVtp == null || StringUtils.isBlank(payOrderVtp.getOrderId()) || StringUtils.isBlank(payOrderVtp.getTotalFee()) || StringUtils.isBlank(payOrderVtp.getToken())) {
            logger.info("增加地址，参数为空，param:" + JSON.toJSONString(payOrderVtp));
            return ResMap.getNullParamMap();
        }




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
    public void insertOrderTransactional(OrderPO op, OrderItemPO itemPO) {
        orderPOMapper.insert(op);
    }

    public Map getOrderByToken(int token) {
        return null;
    }
}
