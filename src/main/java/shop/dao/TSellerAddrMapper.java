package shop.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import shop.pojo.Auc;
import shop.pojo.TSellerAddr;
import shop.pojo.TSellerAddrExample;

import java.util.List;

@Repository("TSellerAddrMapper")
public interface TSellerAddrMapper {
    int countByExample(TSellerAddrExample example);

    int deleteByExample(TSellerAddrExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TSellerAddr record);

    int insertSelective(TSellerAddr record);

    List<TSellerAddr> selectByExample(TSellerAddrExample example);

    List<TSellerAddr> selectAddressList(Auc auc);

    TSellerAddr selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TSellerAddr record, @Param("example") TSellerAddrExample example);

    int updateByExample(@Param("record") TSellerAddr record, @Param("example") TSellerAddrExample example);

    int updateByPrimaryKeySelective(TSellerAddr record);

    int updateByPrimaryKey(TSellerAddr record);
}