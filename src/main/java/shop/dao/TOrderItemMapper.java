package shop.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import shop.pojo.TOrderItem;
import shop.pojo.TOrderItemExample;
@Repository("tOrderItemMapper")
public interface TOrderItemMapper {
    int countByExample(TOrderItemExample example);

    int deleteByExample(TOrderItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TOrderItem record);

    int insertSelective(TOrderItem record);

    List<TOrderItem> selectByExample(TOrderItemExample example);

    TOrderItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TOrderItem record, @Param("example") TOrderItemExample example);

    int updateByExample(@Param("record") TOrderItem record, @Param("example") TOrderItemExample example);

    int updateByPrimaryKeySelective(TOrderItem record);

    int updateByPrimaryKey(TOrderItem record);
}