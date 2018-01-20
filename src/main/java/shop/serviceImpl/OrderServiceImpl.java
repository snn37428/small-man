package shop.serviceImpl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import shop.base.BaseMap.ResMap;
import shop.dao.OrderItemPOMapper;
import shop.dao.OrderPOMapper;
import shop.dao.ProductPOMapper;
import shop.pojo.OrderItemPO;
import shop.pojo.OrderPO;
import shop.pojo.ProductPO;
import shop.service.OrderService;
import shop.utils.GenerateNum;
import shop.utils.RedisUtils;
import shop.vtp.GoodVtp;
import shop.vtp.OrderVtp;
import shop.vtp.PayOrderVtp;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
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
    //写订单数据库
    @Resource(name = "OrderItemPOMapper")
    private OrderItemPOMapper orderItemPOMapper;
    //验证token
    @Resource(name = "LoginServiceImpl")
    private LoginServiceImpl loginServiceImpl;
    //读商品库

    @Resource(name = "productPOMapper")
    private ProductPOMapper productPOMapper;

    public Map create(OrderVtp orderVtp) {

        if (orderVtp == null || StringUtils.isBlank(orderVtp.getToken()) || StringUtils.isBlank(orderVtp.getGoodsJsonStr())) {
            logger.info("创建订单，参数为空，param:" + JSON.toJSONString(orderVtp));
            return ResMap.getNullParamMap();
        }
        Boolean isToken = loginServiceImpl.checkToken(orderVtp.getToken());
        if (!isToken) {
            logger.info("创建订单，token验证未通过");
        }
        List<GoodVtp> goodVtps = JSON.parseArray(orderVtp.getGoodsJsonStr(), GoodVtp.class);
        if (CollectionUtils.isEmpty(goodVtps)) {
            logger.info("创建订单，商品信息为空");
            return ResMap.errCodeMap("商品信息为空");
        }
        for (GoodVtp goodVtp : goodVtps) {
            if (StringUtils.isBlank(goodVtp.getGoodsId())) {
                logger.info("创建订单，商品Id为空");
            }
            this.buildOrder(goodVtp);
        }

//
        return null;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void insertOrderTransactional(OrderPO op, OrderItemPO itemPO) {
        orderPOMapper.insert(op);
    }


    private OrderPO buildOrder(GoodVtp goodVtp) {

        ProductPO productPO = null;
        try {
            productPO = productPOMapper.selectByPrimaryKey(Long.valueOf(goodVtp.getGoodsId()));
        } catch (NumberFormatException e) {
            logger.info("创建订单，读数据商品信息异常，productPO:" + goodVtp.getGoodsId());
        }
        if (productPO == null) {
            logger.info("创建订单，读数据商品信息为空，productPO:" + goodVtp.getGoodsId());
        }
        OrderPO orderPO = new OrderPO();
        orderPO.setBuyerId();
        orderPO.set
        orderPO.setTotalPrice(new BigDecimal(20));
        orderPO.setPaymentStatus(2);
        orderPO.setOrderStatus(1);
        orderPO.setShippingStatus(0);
        orderPO.setOrderSn(GenerateNum.getInstance().GenerateOrder());
        orderPO.setReceiverId("se");
        orderPO.setActive(true);
        this.insertOrderTransactional(orderPO, new OrderItemPO());
        return orderPO;
    }
}
