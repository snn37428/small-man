package shop.base.EnumCode;

/**
 * 类说明
 *
 * @author: songningning
 * @date: created in 2018/2/19 23:37
 * @modified: by
 */
public enum ActiveEnum {
    ACTIVE_N(0, "无效"),
    ACTIVE_Y(1, "有效");

    private long key;

    private String value;

    ActiveEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    public long getKey() {
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
