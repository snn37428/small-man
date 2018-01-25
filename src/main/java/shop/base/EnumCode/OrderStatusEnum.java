package shop.base.EnumCode;

/**
 * 订单状态
 *
 * @author: songningning
 * @date: created in 2018/1/25 23:17
 * @modified: by
 */
public enum OrderStatusEnum {
    NO_CONFIRM(0, "未确认"),
    CONFIRM(1, "已确认"),
    FINISH(2, "已完成"),
    NO_FINISH(3, "已取消");

    private int key;

    private String value;

    OrderStatusEnum(int key, String value) {
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
