package shop.service;

public interface UnifyOrderService {

    /**
     * 微信统一下单
     * @param str
     * @return
     */
    String createOrderWX(String str);
}
