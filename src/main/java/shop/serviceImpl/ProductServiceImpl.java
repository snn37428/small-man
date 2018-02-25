package shop.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
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
import shop.vtp.GoodVtp;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by songningning1 on 2017/9/25.
 */
@Service("ProductServiceImpl")
public class ProductServiceImpl implements ProductService {

    private final static Logger log = LogManager.getLogger(ProductServiceImpl.class);

    @Resource(name = "productPOMapper")
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
        List<ProductVo> pvLists = this.bulidProductGoodsVO(productPOMapper.listProduct());
        redisUtils.set("listProduct", JSON.toJSON(pvLists).toString());
        return ResMap.getSuccessMap(pvLists);
    }

    @Override
    public Map goodsList(GoodVtp goodVtp) {
        //默认走商城模式
        goodVtp.setCategoryId("5");

        if (goodVtp == null || StringUtils.isBlank(goodVtp.getCategoryId())) {
            log.info("获取商品列表，参数为空, goodVtp: " + JSON.toJSONString(goodVtp));
            return ResMap.errCodeMap("参数为空");
        }
        List<ProductPO> productPOS = productPOMapper.selectByCategoryId(Long.parseLong(goodVtp.getCategoryId()));
        if (CollectionUtils.isEmpty(productPOS)) {
            ResMap.nullDataMap("查询商品列表数据为空，调用方法：goodsList");
        }
        List<ProductVo> dataVo = bulidProductGoodsVO(productPOS);
        return  ResMap.successDataMap(dataVo, "获取商品列表成功");
    }


    /**
     * 转化为product视图对象
     *
     * @param productPOS
     * @return
     */
    private List<ProductVo> bulidProductGoodsVO(List<ProductPO> productPOS) {

        List<ProductVo> productVos = new ArrayList<ProductVo>();

        if (CollectionUtils.isEmpty(productPOS)) {
            return productVos;
        }

        for (ProductPO productPO : productPOS) {
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
