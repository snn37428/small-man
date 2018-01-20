package shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.pojo.Auc;
import shop.service.LoginService;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by songningning1 on 2017/9/7.
 */
@Controller
@RequestMapping("/user")
public class Login {

    @Resource(name = "LoginServiceImpl")
    private LoginService loginService;

    @RequestMapping(value = "wxapp/login")
    public @ResponseBody Map login(Auc auc) {
        System.out.println("---");
        return loginService.in(auc);
    }

    @RequestMapping(value = "check-token")
    public @ResponseBody
    Map checkToken(Auc auc) {
        return loginService.checkOnlineToken(auc);
    }

}
