package shop.dao;

import shop.pojo.OrderItemPO;

public interface OrderItemPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderItemPO record);

    int insertSelective(OrderItemPO record);

    OrderItemPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderItemPO record);

    int updateByPrimaryKey(OrderItemPO record);
}