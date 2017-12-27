package shop.dao;

import org.springframework.stereotype.Repository;
import shop.pojo.OrderItemPO;
@Repository("orderItemPOMapper")
public interface OrderItemPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderItemPO record);

    int insertSelective(OrderItemPO record);

    OrderItemPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderItemPO record);

    int updateByPrimaryKey(OrderItemPO record);
}