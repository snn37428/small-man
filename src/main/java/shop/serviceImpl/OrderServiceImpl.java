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
import shop.base.EnumCode.ActiveEnum;
import shop.base.EnumCode.OrderStatusEnum;
import shop.base.EnumCode.PaymentStatusEnum;
import shop.base.EnumCode.ShippingStatusEnum;
import shop.dao.OrderPOMapper;
import shop.dao.ProductPOMapper;
import shop.dao.TOrderItemMapper;
import shop.pojo.OrderPO;
import shop.pojo.ProductPO;
import shop.pojo.TOrderItem;
import shop.service.OrderService;
import shop.utils.GenerateNum;
import shop.utils.RedisUtils;
import shop.vtp.GoodVtp;
import shop.vtp.OrderVtp;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
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
    //验证token
    @Resource(name = "LoginServiceImpl")
    private LoginServiceImpl loginService;
    //读商品库
    @Resource(name = "productPOMapper")
    private ProductPOMapper productPOMapper;
    @Resource(name = "tOrderItemMapper")
    private TOrderItemMapper tOrderItemMapper;

    public Map create(OrderVtp orderVtp) {

        if (orderVtp == null || StringUtils.isBlank(orderVtp.getToken()) || StringUtils.isBlank(orderVtp.getGoodsJsonStr())) {
            logger.info("创建订单，参数为空，param:" + JSON.toJSONString(orderVtp));
            return ResMap.getNullParamMap();
        }
        String openId = loginService.getOpenIdByToken(orderVtp.getToken());
        if (openId == null) {
            logger.info("创建订单，token验证未通过");
            return ResMap.rightCodeMap("Token验证错误");
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
            TOrderItem tOrderItem = new TOrderItem();
            OrderPO orderPO = new OrderPO();
            if (this.buildOrderItem(tOrderItem, goodVtp, orderPO)) {
                if (this.buildOrder(orderPO, tOrderItem, goodVtp, openId)) {
                    if (this.insertOrderItemTransactional(orderPO, tOrderItem)) {
                        logger.info("创建订单，成功，orderId：" + orderPO.getOrderSn());
                    }
                }
            }
        }
        return null;
    }

    /**
     * 组装订单参数 t_order
     * @param orderPO
     * @param tOrderItem
     * @param goodVtp
     * @param openId
     * @return
     */
    private boolean buildOrder(OrderPO orderPO, TOrderItem tOrderItem, GoodVtp goodVtp, String openId) {
        boolean flag = false;
        try {
            orderPO.setBuyerId(openId);
            orderPO.setSellerId(1L);
            orderPO.setActive(true);
            orderPO.setOrderSn(GenerateNum.getInstance().GenerateOrder());
            orderPO.setOrderStatus(OrderStatusEnum.NO_CONFIRM.getKey());
            orderPO.setPaymentStatus(PaymentStatusEnum.NO_PAY.getKey());
            orderPO.setShippingStatus(ShippingStatusEnum.NO_SEND.getKey());
            orderPO.setReceiverId("");
            orderPO.setUpdated(new Date());
            flag = true;
        } catch (Exception e) {
            logger.info("创建订单，组装order入库参数，异常");
        }
        return flag;
    }

    /**
     * 通过商品id查询商品详情
     * @param tOrderItem
     * @param goodVtp
     * @return
     */
    private boolean buildOrderItem(TOrderItem tOrderItem, GoodVtp goodVtp, OrderPO orderPO) {
        boolean flag = false;
        ProductPO product;
        try {
           // Long.parseLong(goodVtp.getGoodsId())=11l;
            product = productPOMapper.selectByPrimaryKey(11L);
            if (product == null) {
                logger.info("创建订单，通过商品Id查询商品，为空,goodId:" + goodVtp.getGoodsId());
                return flag;
            }
            tOrderItem.setActive(true);
            tOrderItem.setSn(product.getSn());
            tOrderItem.setProductId(Long.parseLong(goodVtp.getGoodsId()));
            tOrderItem.setName(product.getName());
            tOrderItem.setProductImg(product.getImage());
            tOrderItem.setPrice(new BigDecimal(product.getPrice()));
            tOrderItem.setQuantity(Integer.valueOf(goodVtp.getNumber()));
            tOrderItem.setUpdated(new Date());
            orderPO.setTotalPrice(new BigDecimal(product.getPrice()));
            flag = true;
        } catch (NumberFormatException e) {
            logger.info("创建订单，通过商品Id,查询商品，异常");
        }
        return flag;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public boolean insertOrderItemTransactional(OrderPO orderPO, TOrderItem itemPO) {
        int orderId = orderPOMapper.insert(orderPO);
        itemPO.setOrderId((long)orderId);
        tOrderItemMapper.insert(itemPO);
        return true;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void insertOrderTransactional(OrderPO op, TOrderItem tOrderItem) {
//        orderPOMapper.insert(op);
//        tPOMapper.selectByPrimaryKey(Long.valueOf(goodVtp.getGoodsId()));
//        productPO = produc(NumberFormatException e) {
//            logger.info("创建订单，读数据商品信息异常，productPO:" + goodVtp.getGoodsId());
//        }
//        if (productPO == null) {
//            logger.info("创建订单，读数据商品信息为空，productPO:" + goodVtp.getGoodsId());
//        }
//        OrderPO orderPO = new OrderPO();
//        orderPO.setBuyerId();
//        orderPO.set
//        orderPO.setTotalPrice(new BigDecimal(20));
//        orderPO.setPaymentStatus(2);
//        orderPO.setOrderStatus(1);
//        orderPO.setShippingStatus(0);
//        orderPO.setOrderSn(GenerateNum.getInstance().GenerateOrder());
//        orderPO.setReceiverId("se");
//        orderPO.setActive(true);
//        this.insertOrderTransactional(orderPO, new OrderItemPO());
//        return orderPO;

    }
}