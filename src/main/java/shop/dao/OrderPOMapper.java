package shop.dao;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import shop.pojo.OrderPO;
@Service("orderPOMapper")
public interface OrderPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderPO record);

    int insertSelective(OrderPO record);

    OrderPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderPO record);

    int updateByPrimaryKey(OrderPO record);

}