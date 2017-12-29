package shop.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import shop.pojo.TSellerAddr;
@Service("TSellerAddrMapper")
public interface TSellerAddrMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TSellerAddr record);

    int insertSelective(TSellerAddr record);

    TSellerAddr selectByPrimaryKey(Long id);

      int updateByPrimaryKeySelective(TSellerAddr record);

    int updateByPrimaryKey(TSellerAddr record);
}