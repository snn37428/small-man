package shop.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import shop.pojo.TyUser;
import shop.pojo.TyUserExample;
@Repository("TyUserMapper")
public interface TyUserMapper {
    int countByExample(TyUserExample example);

    int deleteByExample(TyUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TyUser record);

    int insertSelective(TyUser record);

    List<TyUser> selectByExample(TyUserExample example);

    TyUser selectByPrimaryKey(Integer id);

    TyUser selectByToken(String token);

    int updateByExampleSelective(@Param("record") TyUser record, @Param("example") TyUserExample example);

    int updateByExample(@Param("record") TyUser record, @Param("example") TyUserExample example);

    int updateByPrimaryKeySelective(TyUser record);

    int updateByPrimaryKey(TyUser record);
}