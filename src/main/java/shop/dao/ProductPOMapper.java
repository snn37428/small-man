package shop.dao;

import org.springframework.stereotype.Repository;
import shop.pojo.ProductPO;

import java.util.List;

@Repository("productPOMapper")
public interface ProductPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductPO record);

    int insertSelective(ProductPO record);

    ProductPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductPO record);

    int updateByPrimaryKeyWithBLOBs(ProductPO record);

    int updateByPrimaryKey(ProductPO record);

    List<ProductPO> listProduct();
}