package tyshop.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tyshop.base.BaseMap.ResMap;
import tyshop.mapper.ProductMapper;
import tyshop.model.ProductModel;
import tyshop.service.ProductService;
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

    @Override
    public ProductModel getProductById(int id) {
        return productMapper.getProductById(2);
    }

    @Override
    public Map listProduct() {
        return ResMap.getSuccessMap(this.convertProduct(productMapper.listProduct()));
    }

    /**
     * 转化为product视图对象
     * @param listProductModel
     * @return
     */
    private List<ProductVo> convertProduct(List<ProductModel> listProductModel) {

        List<ProductVo> productVos = new ArrayList<ProductVo>();

        if(CollectionUtils.isEmpty(listProductModel)) {
            return productVos;
        }

        for(ProductModel productModel : listProductModel) {
            if(productModel == null) {
                continue;
            }
            ProductVo productVo = new ProductVo();
            productVo.setId(productModel.getId());
            productVo.setName(productModel.getName());
            productVo.setNameDesc(productModel.getNameDesc());
            productVo.setIcon(productModel.getImage());
            productVos.add(productVo);
        }
        return productVos;
    }
}
