package tyshop.mapper;

import tyshop.model.ProductModel;

import java.util.List;

/**
 * Created by songningning1 on 2017/9/25.
 */
public interface ProductMapper {
    /**
     * 插入数据
     *
     * @param productModel
     * @return
     */
    int insert(ProductModel productModel);

    /**
     * 通过主键修改数据
     *
     * @param productModel
     * @return
     */
    int updateById(ProductModel productModel);

    /**
     * 通过Id获取product
     *
     * @param id
     * @return
     */
    ProductModel getProductById(int id);

    /**
     * 获取Product结合
     * @return
     */
    List<ProductModel> listProduct();
}
