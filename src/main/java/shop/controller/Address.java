package shop.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.base.BaseMap.ResMap;
import shop.pojo.Auc;
import shop.pojo.TSellerAddr;
import shop.serviceImpl.AddressServiceImpl;
import shop.vtp.AddressVtp;

import javax.annotation.Resource;
import java.util.Map;

@Controller
@RequestMapping("user")
public class Address {

    private static final Logger logger = Logger.getLogger(Address.class);

    @Resource(name = "AddressServiceImpl")
    private AddressServiceImpl addressService;

    @RequestMapping(value = "address/add")
    @ResponseBody
    public Map add(AddressVtp addressVtp) {
        try {
           return addressService.insertAddress(addressVtp);
        } catch (Exception e) {
            logger.error("添加地址，异常" + e);
            return ResMap.getExceptionMap();
        }
    }

    @RequestMapping(value = "address/list")
    @ResponseBody
    public Map add(Auc auc) {
        try {
            return addressService.detail(auc);
        } catch (Exception e) {
            logger.error("查询地址，异常" + e);
            return ResMap.getExceptionMap();
        }
    }

    @RequestMapping(value = "address/delete")
    public @ResponseBody Map delete(Auc auc) {
        try {
            return addressService.deleteAddress(auc.getId());
        } catch (Exception e) {
            logger.error("添加地址，异常" + e);
            return ResMap.getExceptionMap();
        }
    }

    @RequestMapping(value = "address/update")
    public @ResponseBody Map update(AddressVtp addressVtp) {
        try {
            return addressService.updteAddress(addressVtp);
        } catch (Exception e) {
            logger.error("添加地址，异常" + e);
            return ResMap.getExceptionMap();
        }
    }

    @RequestMapping(value = "address/detail")
    public @ResponseBody Map detail (Auc auc) {
        try {
           return addressService.detail(auc);
        } catch (Exception e) {
            logger.error("获得用户全部地址，异常" + e);
            return ResMap.getExceptionMap();
        }
    }

    @RequestMapping(value = "address/detail/id")
    public @ResponseBody Map detailId (Auc auc) {
        try {
            return addressService.detailId(auc);
        } catch (Exception e) {
            logger.error("获得用户全部地址，异常" + e);
            return ResMap.getExceptionMap();
        }
    }

    @RequestMapping(value = "address/detail/default")
    public @ResponseBody Map defaultAddress (Auc auc) {
        try {
            return addressService.defaultAddress(auc);
        } catch (Exception e) {
            logger.error("获得用户全部地址，异常" + e);
            return ResMap.getExceptionMap();
        }
    }

}
