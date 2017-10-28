package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.service.ManService;

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
        return manService.cleanRedis(key);
    }
}
