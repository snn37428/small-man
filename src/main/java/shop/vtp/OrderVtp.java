package shop.vtp;

/**
 * 下单接收参数
 *
 * @author: songningning
 * @date: created in 2018/1/21 0:05
 * @modified: by
 */
public class OrderVtp {

    private String token;
    private String reMark;
    private String goodsJsonStr;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getReMark() {
        return reMark;
    }

    public void setReMark(String reMark) {
        this.reMark = reMark;
    }

    public String getGoodsJsonStr() {
        return goodsJsonStr;
    }

    public void setGoodsJsonStr(String goodsJsonStr) {
        this.goodsJsonStr = goodsJsonStr;
    }
}
