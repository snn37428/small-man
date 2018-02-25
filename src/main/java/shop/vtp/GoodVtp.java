package shop.vtp;

/**
 * 下单携带商品信息
 *
 * @author: songningning
 * @date: created in 2018/1/21 1:16
 * @modified: by 2018、2、24
 */
public class GoodVtp {

    private String goodsId; //商品Id
    private String number; //商品数量
    private String propertyChildIds;
    private String logisticsType;
    private String categoryId;//商品组别

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPropertyChildIds() {
        return propertyChildIds;
    }

    public void setPropertyChildIds(String propertyChildIds) {
        this.propertyChildIds = propertyChildIds;
    }

    public String getLogisticsType() {
        return logisticsType;
    }

    public void setLogisticsType(String logisticsType) {
        this.logisticsType = logisticsType;
    }

    public GoodVtp() {
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public GoodVtp(String goodsId, String number, String propertyChildIds, String logisticsType) {
        this.goodsId = goodsId;
        this.number = number;
        this.propertyChildIds = propertyChildIds;
        this.logisticsType = logisticsType;
    }
}
