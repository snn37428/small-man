package shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import shop.model.ProductModel;
import shop.service.ProductService;

import java.util.Map;

/**
 * Created by songningning1 on 2017/9/25.
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "category/all")
    @ResponseBody
    public Map login() {
        //ProductModel fe = productService.getProductById(2);
       return null;
    }
}
