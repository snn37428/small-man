package shop.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.base.BaseMap.ResMap;
import shop.pojo.Auc;
import shop.serviceImpl.ShopServiceImpl;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("shop")
public class Shop {

    @Resource(name = "shopServiceImpl")
    private ShopServiceImpl shopServiceImpl;

    @RequestMapping(value = "goods/detail")
    public @ResponseBody Map list(Auc auc) {
        return shopServiceImpl.goodsDetail(auc.getId());
    }
}
