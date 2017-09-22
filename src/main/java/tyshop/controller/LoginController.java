package tyshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tyshop.base.EnumCode.ResEnum;
import tyshop.service.LoginService;
import tyshop.base.BaseMap.ResMap;
import java.util.Map;

/**
 * Created by songningning1 on 2017/9/7.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
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
