package shop.service;

import shop.pojo.ProductPO;

import java.util.Map;

/**
 * Created by songningning1 on 2017/9/25.
 */
public interface ProductService {

    /**
     * 通过Id获取Product 详情
     *
     * @param id
     * @return
     */
    ProductPO getProductById(long id);

    /**
     * 获取product集合
     *
     * @return
     */
    Map listProduct();
}
