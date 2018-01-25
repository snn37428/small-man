package shop.base.EnumCode;

/**
 * 类说明
 *
 * @author: songningning
 * @date: created in 2018/1/25 23:18
 * @modified: by
 */
public enum PaymentStatusEnum {
    NO_PAY(0, "未支付"),
    PART_PAY(1, "部分支付"),
    Pay(2, "已支付"),
    PART_REFUND(3, "部分退款"),

    REFUND(4,"已退款");

    private int key;

    private String value;

    PaymentStatusEnum(int key, String value) {
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
