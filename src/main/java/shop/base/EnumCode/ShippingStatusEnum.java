package shop.base.EnumCode;

/**
 * 类说明
 *
 * @author: songningning
 * @date: created in 2018/1/25 23:19
 * @modified: by
 */
public enum ShippingStatusEnum {
    NO_SEND(0, "未发货"),
    PART_SEND(1, "部分发货"),
    SEND(2, "已发货"),
    PART_REFUND(3, "部分退货"),

    REFUND(4, "已退货");

    private int key;

    private String value;

    ShippingStatusEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
