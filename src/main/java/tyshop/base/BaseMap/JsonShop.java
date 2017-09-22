package tyshop.base.BaseMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by songningning1 on 2017/9/9.
 */
public class JsonShop {

        public final static Map<String, Object> put(Object data, Object resMeg) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("data", data);
            return map;
        }
}
