package shop.base.BaseMap;

import com.alibaba.fastjson.JSON;

/**
 * 类说明
 *
 * @author: songningning
 * @date: created in 2018/1/30 20:50
 * @modified: by
 */
public class ResJson {
    private Object Data;
    private String msg;

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
