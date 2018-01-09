package shop.serviceImpl;

import shop.base.wxfinal.WXLoginFinal;
import shop.pojo.PaymentPo;
import shop.service.UnifyOrderService;
import shop.utils.UnityOrderUtils;

import java.util.HashMap;
import java.util.Map;

public class UnityOrderServiceImpl implements UnifyOrderService {
    @Override
    public String createOrderWX(String str) {

        PaymentPo paymentPo = new PaymentPo();
        paymentPo.setAppid("1234");
        paymentPo.setGoods_tag("34");
        paymentPo.setMch_id("sfe");
















        return null;
    }


    public static void main(String[] args) {
        System.out.println(UnityOrderUtils.getRandomString(32));
    }


    private String buildSignMap () {

        Map<String, String> map = new HashMap<String, String>();
        map.put("appid", WXLoginFinal.getWxAppid());
        map.put("mch_id", WXLoginFinal.getMchId());
        map.put("nonce_str", UnityOrderUtils.getRandomString(16));
        map.put("body",  body);
        map.put("out_trade_no", );//单号
        map.put("total_fee",)
        map.put("spbill_create_ip", );
        map.put("notify_url", );
        map.put("trade_type", "JSAPI");
        map.put("fee_type", "CNY");
        map.put("openid", );
        map.put("sign", )
    }
}
