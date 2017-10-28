package shop.dao;

import shop.pojo.BuyerReceiverPO;

public interface BuyerReceiverPOMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BuyerReceiverPO record);

    int insertSelective(BuyerReceiverPO record);

    BuyerReceiverPO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BuyerReceiverPO record);

    int updateByPrimaryKey(BuyerReceiverPO record);
}