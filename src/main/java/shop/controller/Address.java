package shop.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.base.BaseMap.ResMap;
import shop.pojo.Auc;
import shop.pojo.TSellerAddr;
import shop.service.AddressService;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("user/shipping-address")
public class Address {

    private static final Logger logger = Logger.getLogger(Address.class);

    @Resource(name = "AddressServiceImpl")
    private AddressService addressService;

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public @ResponseBody Map add(TSellerAddr SellerAddr) {
        try {
            return addressService.insertAddress(SellerAddr);
        } catch (Exception e) {
            logger.error("添加地址，异常" + e);
            return ResMap.getExceptionMap();
        }
    }

    @RequestMapping(value = "update")
    public @ResponseBody Map update(TSellerAddr SellerAddr) {
        try {
            return addressService.updateAddress(SellerAddr);
        } catch (Exception e) {
            logger.error("添加地址，异常" + e);
            return ResMap.getExceptionMap();
        }
    }

    @RequestMapping(value = "detail")
    public @ResponseBody Map detail (Auc auc) {
        try {
            return addressService.detail(auc);
        } catch (Exception e) {
            logger.error("获得用户全部地址，异常" + e);
            return ResMap.getExceptionMap();
        }
    }
}
