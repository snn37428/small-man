package shop.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import shop.pojo.TProductImage;
import shop.pojo.TProductImageExample;
@Repository("tProductImageMapper")
public interface TProductImageMapper {
    int countByExample(TProductImageExample example);

    int deleteByExample(TProductImageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TProductImage record);

    int insertSelective(TProductImage record);

    List<TProductImage> listProductImage(Integer id);

    List<TProductImage> selectByExample(TProductImageExample example);

    TProductImage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TProductImage record, @Param("example") TProductImageExample example);

    int updateByExample(@Param("record") TProductImage record, @Param("example") TProductImageExample example);

    int updateByPrimaryKeySelective(TProductImage record);

    int updateByPrimaryKey(TProductImage record);
}