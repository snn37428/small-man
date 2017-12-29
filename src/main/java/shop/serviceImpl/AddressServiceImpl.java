package shop.serviceImpl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import shop.base.BaseMap.ResMap;
import shop.base.EnumCode.ResEnum;
import shop.dao.TSellerAddrMapper;
import shop.pojo.TSellerAddr;
import shop.service.AddressService;

import javax.annotation.Resource;
import java.util.Map;

@Service("AddressServiceImpl")
public class AddressServiceImpl implements AddressService {

    private static final Logger logger = Logger.getLogger(AddressServiceImpl.class);

    @Resource(name = "TSellerAddrMapper")
    private TSellerAddrMapper tSellerAddrMapper;

    @Override
    public Map insertAddress(String code) {

        if (StringUtils.isBlank(code)) {
            logger.error("增加地址，参数为空");
            return ResMap.getNullParamMap();
        }

        TSellerAddr jsonObject;
        try {
            jsonObject = JSON.parseObject(code, TSellerAddr.class);
        } catch (Exception e) {
            logger.error("增加地址，参数转化，异常");
            return ResMap.getNullParamMap();
        }

        if (this.validateParam(jsonObject)) {
            return ResMap.getFailedMap(ResEnum.REQ_PARAM_ERROR.getKey(), ResEnum.REQ_PARAM_ERROR.getValue());
        }

        if (tSellerAddrMapper.insert(jsonObject) > 0) {
            return ResMap.getSuccessMap("数据库插入成功");
        } else {
            return ResMap.getFailedMap("数据库插入失败");
        }
    }

    @Override
    public Map updateAddress(String code) {

        if (StringUtils.isBlank(code)) {
            logger.error("更改地址，参数为空");
            return ResMap.getNullParamMap();
        }

        return null;
    }


    private Boolean validateParam(TSellerAddr jsonObject) {

        if (jsonObject == null) {
            logger.error("增加，更新地址时，参数转对象为空");
            return false;
        }

        if (StringUtils.isBlank(jsonObject.getCity())) {
            logger.error("增加，更新地址时，城市为空");
            return false;
        }

        if (StringUtils.isBlank(jsonObject.getSellerId())) {
            logger.error("增加，更新地址时，openId为空");
            return false;
        }

        if (StringUtils.isBlank(jsonObject.getPhone())) {
            logger.error("增加，更新地址时，手机号码为空");
            return false;
        }

        if (StringUtils.isBlank(jsonObject.getContactName())) {
            logger.error("增加，更新地址时，名字号码为空");
            return false;
        }
        return true;
    }
}
