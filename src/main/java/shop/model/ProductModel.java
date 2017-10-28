package shop.model;

import java.util.Date;

/**
 * Created by songningning1 on 2017/9/22.
 */
public class ProductModel {

    private int id;
    private int sellerId;
    private String sn;//编码
    private String name;//商品名称
    private String nameDesc;//商品描述
    private String price;//销售价格
    private String marketPrice;//市场价格
    private String image;//展示图片
    private int stock;//库存
    private String deliveryWeight;//统一规格物流
    private int isMarketbable;//是否上架
    private int isUnifiedSpec;//是否统一规格
    private int isList;//是否列出
    private String introduction;//商品详情
    private String sales;//销量
    private String productCategoryId;//商品品类
    private int deliveryType;//是否统一邮费
    private String delvieryFees;//邮费价格
    private String deliveryTemplateId;//邮费模板
    private Date created;
    private Date modified;
    private int active;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameDesc() {
        return nameDesc;
    }

    public void setNameDesc(String nameDesc) {
        this.nameDesc = nameDesc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getMarketPrice() {
        return marketPrice;
    }

    public void setMarketPrice(String marketPrice) {
        this.marketPrice = marketPrice;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDeliveryWeight() {
        return deliveryWeight;
    }

    public void setDeliveryWeight(String deliveryWeight) {
        this.deliveryWeight = deliveryWeight;
    }

    public int getIsMarketbable() {
        return isMarketbable;
    }

    public void setIsMarketbable(int isMarketbable) {
        this.isMarketbable = isMarketbable;
    }

    public int getIsUnifiedSpec() {
        return isUnifiedSpec;
    }

    public void setIsUnifiedSpec(int isUnifiedSpec) {
        this.isUnifiedSpec = isUnifiedSpec;
    }

    public int getIsList() {
        return isList;
    }

    public void setIsList(int isList) {
        this.isList = isList;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getSales() {
        return sales;
    }

    public void setSales(String sales) {
        this.sales = sales;
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public int getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(int deliveryType) {
        this.deliveryType = deliveryType;
    }

    public String getDelvieryFees() {
        return delvieryFees;
    }

    public void setDelvieryFees(String delvieryFees) {
        this.delvieryFees = delvieryFees;
    }

    public String getDeliveryTemplateId() {
        return deliveryTemplateId;
    }

    public void setDeliveryTemplateId(String deliveryTemplateId) {
        this.deliveryTemplateId = deliveryTemplateId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
