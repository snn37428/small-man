package shop.dao;

import shop.pojo.ProductPO;

public interface ProductPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductPO record);

    int insertSelective(ProductPO record);

    ProductPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductPO record);

    int updateByPrimaryKey(ProductPO record);
}