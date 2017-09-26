package tyshop.service;

import tyshop.model.ProductModel;

import java.util.Map;

/**
 * Created by songningning1 on 2017/9/25.
 */
public interface ProductService {

    /**
     * 通过Id获取Product
     * @param id
     * @return
     */
    ProductModel getProductById(int id);

    /**
     * 获取product集合
     * @return
     */
    Map listProduct();
}
