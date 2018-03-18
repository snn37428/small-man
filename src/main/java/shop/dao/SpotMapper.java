package shop.dao;

import shop.pojo.Spot;

public interface SpotMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Spot record);

    int insertSelective(Spot record);

    Spot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Spot record);

    int updateByPrimaryKey(Spot record);
}