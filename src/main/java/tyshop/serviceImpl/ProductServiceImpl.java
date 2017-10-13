package tyshop.serviceImpl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tyshop.base.BaseMap.ResMap;
import tyshop.mapper.ProductMapper;
import tyshop.model.ProductModel;
import tyshop.service.ProductService;
import tyshop.utils.RedisUtils;
import tyshop.vo.ProductVo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by songningning1 on 2017/9/25.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public ProductModel getProductById(int id) {
        return productMapper.getProductById(id);
    }

    @Override
    public String listProduct() {
        //缓存读取
        if (redisUtils.get("listProduct") != null) {
            return redisUtils.get("listProduct");
        }
        List<ProductVo> pvList = this.convertProduct(productMapper.listProduct());
        redisUtils.set("listProduct", JSON.toJSONString(pvList));
        return JSON.toJSONString(ResMap.getSuccessMap(pvList));
    }

    /**
     * 转化为product视图对象
     *
     * @param listProductModel
     * @return
     */
    private List<ProductVo> convertProduct(List<ProductModel> listProductModel) {

        List<ProductVo> productVos = new ArrayList<ProductVo>();

        if (CollectionUtils.isEmpty(listProductModel)) {
            return productVos;
        }

        for (ProductModel productModel : listProductModel) {
            if (productModel == null) {
                continue;
            }
            ProductVo productVo = new ProductVo();
            BeanUtils.copyProperties(productVo, productModel);
            productVo.setIcon(productModel.getImage());
            productVos.add(productVo);
        }
        return productVos;
    }
}
