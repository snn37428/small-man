package tyshop.base.BaseMap;

import tyshop.base.EnumCode.ResEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by songningning1 on 2017/9/9.
 */
public class ResMap {

    /** 返回失败
     * @param key 错误码
     * @param value 错误说明
     * @return
     */
    public static Map<String, Object> getFailedMap(int key, String value) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", key);
        map.put("msg", value);
        map.put("success", true);
        map.put("data", null);
        return map;
    }

    /**
     * 返回成功数据
     * @param data 返回的data数据
     * @return
     */
    public static <T> Map getSuccessMap(T data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", ResEnum.ON_ERROR.getKey());
        map.put("msg", ResEnum.ON_ERROR.getValue());
        map.put("success", true);
        map.put("data", data);
        return map;
    }

    public static Map<String, Object> getMap(Object data) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("success", true);
        map.put("data", data);
        return map;
    }

}
