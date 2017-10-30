package shop.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import shop.base.BaseMap.ResMap;
import shop.dao.ProductPOMapper;
import shop.pojo.ProductPO;
import shop.service.ProductService;
import shop.utils.RedisUtils;
import shop.vo.ProductVo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by songningning1 on 2017/9/25.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductPOMapper productPOMapper;

    @Autowired
    private RedisUtils redisUtils;


    public ProductPO getProductById(long id) {
        return productPOMapper.selectByPrimaryKey(id);
    }

    public Map listProduct() {
        //缓存读取
        String pvList = redisUtils.get("listProduct");

        if (StringUtils.isNotBlank(pvList)) {
            return ResMap.getSuccessMap(JSONObject.parseArray(pvList, ProductVo.class));
        }
        List<ProductVo> pvLists = this.convertProduct(productPOMapper.listProduct());
        redisUtils.set("listProduct", JSON.toJSON(pvLists).toString());
        return ResMap.getSuccessMap(pvLists);
    }


    /**
     * 转化为product视图对象
     *
     * @param listProductModel
     * @return
     */
    private List<ProductVo> convertProduct(List<ProductPO> listProductModel) {

        List<ProductVo> productVos = new ArrayList<ProductVo>();

        if (CollectionUtils.isEmpty(listProductModel)) {
            return productVos;
        }

        for (ProductPO productPO : listProductModel) {
            if (productPO == null) {
                continue;
            }
            ProductVo productVo = new ProductVo();
            BeanUtils.copyProperties(productVo, productPO);
            productVo.setIcon(productPO.getImage());
            productVos.add(productVo);
        }
        return productVos;
    }
}
