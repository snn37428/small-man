package shop.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.base.BaseMap.ResMap;

import java.util.Map;

@Controller
@RequestMapping("shop")
public class Goods {

    private static final Logger logger = Logger.getLogger(Goods.class);

    @RequestMapping(value = "goods/list")
    public @ResponseBody Map list(String categoryId) {

        try {

        } catch (Exception e) {
            logger.error("添加地址，异常" + e);
            return ResMap.getExceptionMap();
        }
        return null;
    }
}
