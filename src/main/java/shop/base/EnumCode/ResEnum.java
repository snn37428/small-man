package shop.base.EnumCode;

/**
 * Created by songningning1 on 2017/9/9.
 */
public enum ResEnum {
    ON_ERROR(0, "成功"),
    KEY_NULL_ERROR(1, "key为空或者有误"),
    REQ_PARAM_ERROR(2, "请求参数有误"),
    RES_RESULT_NULL(9, "接口返回值为空"),
    INTERFACE_ERROR(3, "接口异常"),
    RES_PARAM_ERROR(-1,"返回参数有误");

    private int key;

    private String value;

    ResEnum(int key, String value) {
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
