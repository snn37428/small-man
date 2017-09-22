package tyshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tyshop.service.IndexService;

import java.util.Map;

/**
 * Created by songningning1 on 2017/9/17.
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IndexService indexService;

    @RequestMapping(value = "banner/list")
    @ResponseBody
    public Map listBanner() {
        return indexService.listBanner();
    }


}
