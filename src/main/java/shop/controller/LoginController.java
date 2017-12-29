package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.base.EnumCode.ResEnum;
import shop.service.LoginService;
import shop.base.BaseMap.ResMap;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by songningning1 on 2017/9/7.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Resource(name = "LoginServiceImpl")
    private LoginService loginService;

    @RequestMapping(value = "in")
    @ResponseBody
    public Map login(@RequestParam String code) {
        System.out.println("---");
        if (StringUtils.isEmpty(code)) {
            return ResMap.getFailedMap(ResEnum.REQ_PARAM_ERROR.getKey(), ResEnum.REQ_PARAM_ERROR.getValue());
        }
        return loginService.in(code);
    }

    @RequestMapping(value = "token")
    @ResponseBody
    public Map checkToken(@RequestParam String token) {
        return loginService.checkToken(token);
    }

}
