package shop.serviceImpl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import shop.base.BaseMap.ResMap;
import shop.base.EnumCode.ResEnum;
import shop.dao.ProductPOMapper;
import shop.pojo.ProductPO;
import shop.service.GoodsService;
import shop.vo.ProductVo;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("GoodsSerivceImpl")
public class GoodsSerivceImpl implements GoodsService {

    private static final Logger logger = Logger.getLogger(GoodsSerivceImpl.class);

    @Resource(name = "productPOMapper")
    private ProductPOMapper productPOMapper;

    @Override
    public Map productList(long categoryId) {

        if (categoryId == 0) {
            logger.info("获取商品列表，参数为空，param ｛ categoryId" + categoryId + "}");
            return ResMap.getNullParamMap();
        }

        List<ProductVo> productVoS = new ArrayList<ProductVo>();

        try {

            List<ProductPO> productPOList = productPOMapper.selectByCategoryId(categoryId);

            if (CollectionUtils.isEmpty(productPOList)) {
                logger.error("获取商品列表，读数据为空。 param:" + categoryId);
                return ResMap.nullDataMap("数据为空");
            }

            for (ProductPO productPO : productPOList) {
                ProductVo productVo = new ProductVo(productPO.getId(), productPO.getName(), productPO.getPrice(),productPO.getImage());
                productVoS.add(productVo);
            }

            logger.error("获取商品列表，读取数据成功，param:" + categoryId);
            return ResMap.successDataMap(productPOList, "返回数据成功");
        } catch (Exception e) {
            logger.error("获取商品列表，异常，param:" + categoryId);
            return ResMap.getFailedMap(ResEnum.INTERFACE_ERROR.getKey(), ResEnum.INTERFACE_ERROR.getValue());
        }
    }
}
