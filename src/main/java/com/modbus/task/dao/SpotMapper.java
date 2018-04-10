package com.modbus.task.dao;

import com.modbus.task.pojo.Spot;

public interface SpotMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Spot record);

    int insertSelective(Spot record);

    Spot selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Spot record);

    int updateByPrimaryKey(Spot record);
}