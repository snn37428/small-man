package shop.dao;

import org.springframework.stereotype.Repository;
import shop.pojo.OrderPO;
@Repository("orderPOMapper")
public interface OrderPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderPO record);

    int insertSelective(OrderPO record);

    OrderPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderPO record);

    int updateByPrimaryKey(OrderPO record);

}