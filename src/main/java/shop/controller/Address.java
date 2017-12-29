package shop.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.base.BaseMap.ResMap;
import shop.service.AddressService;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("user/shipping-address/")
public class Address {

    private static final Logger logger = Logger.getLogger(Address.class);

    @Resource(name = "AddressServiceImpl")
    private AddressService addressService;

    @RequestMapping(value = "add")
    @ResponseBody
    public Map add(@RequestParam String code) {
        try {
            return addressService.insertAddress(code);
        } catch (Exception e) {
            logger.error("添加地址，异常" + e);
            return ResMap.getExceptionMap();
        }
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public Map update(@RequestParam String code) {
        try {
            return addressService.insertAddress(code);
        } catch (Exception e) {
            logger.error("添加地址，异常" + e);
            return ResMap.getExceptionMap();
        }
    }
}
