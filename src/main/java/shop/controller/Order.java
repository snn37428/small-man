package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.service.OrderService;
import shop.vtp.PayOrderVtp;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by songningning1 on 2017/10/17.
 */
@Controller
@RequestMapping("/order")
public class Order {

    @Resource(name = "OrderServiceImpl")
    private OrderService orderService;

    @RequestMapping(value = "create")
    public  @ResponseBody Map create(PayOrderVtp payOrderVtp) {
        orderService.create(payOrderVtp);
        return null;
    }
}
