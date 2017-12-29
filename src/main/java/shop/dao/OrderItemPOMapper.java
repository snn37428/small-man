package shop.dao;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import shop.pojo.OrderItemPO;

import javax.annotation.Resource;

@Service("OrderItemPOMapper")
public interface OrderItemPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderItemPO record);

    int insertSelective(OrderItemPO record);

    OrderItemPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderItemPO record);

    int updateByPrimaryKey(OrderItemPO record);
}