package tyshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tyshop.service.ManService;
import tyshop.utils.RedisUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by songningning1 on 2017/10/13.
 */
@Controller
@RequestMapping("/man/redis")
public class ManController {

    @Autowired
    private ManService manService;

    @RequestMapping(value = "clean")
    @ResponseBody
    public Map cleanRedis(@RequestParam String key) {
        Map map = new HashMap();
        map.put("哈哈", "我");
        return map;
    }
}
