package shop.serviceImpl;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import shop.base.BaseMap.ResMap;
import shop.base.EnumCode.ResEnum;
import shop.dao.TSellerAddrMapper;
import shop.pojo.Auc;
import shop.pojo.TSellerAddr;
import shop.service.AddressService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("AddressServiceImpl")
public class AddressServiceImpl implements AddressService {

    private static final Logger logger = Logger.getLogger(AddressServiceImpl.class);

    private static final String phoneReg = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";

    @Resource(name = "TSellerAddrMapper")
    private TSellerAddrMapper tSellerAddrMapper;

    @Override
    public Map insertAddress(TSellerAddr sellerAddr) {

        if (sellerAddr == null) {
            logger.info("增加地址，参数为空，param:" + JSON.toJSONString(sellerAddr));
            return ResMap.getNullParamMap();
        }

        if (this.validateParam(sellerAddr)) {
            return ResMap.getFailedMap(ResEnum.REQ_PARAM_ERROR.getKey(), ResEnum.REQ_PARAM_ERROR.getValue());
        }

        if (tSellerAddrMapper.insert(sellerAddr) > 0) {
            return ResMap.rightCodeMap("数据库插入成功");
        } else {
            return ResMap.errCodeMap("数据库插入失败");
        }
    }

    @Override
    public Map updateAddress(TSellerAddr sellerAddrress) {

        if (sellerAddrress == null) {
            logger.info("更新地址，参数为空，param:" + JSON.toJSONString(sellerAddrress));
            return ResMap.getNullParamMap();
        }

        if (this.validateParam(sellerAddrress)) {
            return ResMap.getFailedMap(ResEnum.REQ_PARAM_ERROR.getKey(), ResEnum.REQ_PARAM_ERROR.getValue());
        }

        if (tSellerAddrMapper.updateByPrimaryKey(sellerAddrress) > 0) {
            return ResMap.getSuccessMap("数据库插入成功");
        } else {
            return ResMap.getFailedMap("数据库插入失败");
        }
    }

    @Override
    public Map detail(Auc auc) {

        if (auc == null) {
            logger.info("获取地址，参数为空，param:" + JSON.toJSONString(auc));
            return ResMap.getNullParamMap();
        }

        if (StringUtils.isBlank(auc.getOpendId())) {
            logger.info("获取全部地址，参数存有空值，param:" + JSON.toJSONString(auc));
            return ResMap.getNullParamMap();
        }

        List<TSellerAddr> addressList = tSellerAddrMapper.selectAddressList(auc);

        if (CollectionUtils.isEmpty(addressList)) {
            logger.info("获取全部地址，查询数据为空，param:" + JSON.toJSONString(auc));
            return ResMap.getMap(ResMap.getFailedMap("null"));
        }

       return ResMap.getMap(ResMap.getSuccessMap(addressList));

    }


    private Boolean validateParam(TSellerAddr sellerAddr) {

        if (StringUtils.isBlank(sellerAddr.getCity())) {
            logger.error("增加，更新地址时，城市为空，param:" + JSON.toJSONString(sellerAddr));
            return true;
        }

        if (StringUtils.isBlank(sellerAddr.getSellerId())) {
            logger.error("增加，更新地址时，openId为空，param:" + JSON.toJSONString(sellerAddr));
            return true;
        }

        String phoneString = sellerAddr.getPhone();

        if (StringUtils.isBlank(phoneString)) {
            logger.error("增加，更新地址时，手机号码为空，param:" + JSON.toJSONString(sellerAddr));
            return true;
        }

        if (StringUtils.isNotBlank(phoneString)) {
            if (phoneString.length() != 11) {
                logger.error("增加，更新地址时，手机号码位数错误，param:" + JSON.toJSONString(sellerAddr));
                return true;
            } else {
                Pattern p = Pattern.compile(phoneReg);
                Matcher m = p.matcher(phoneString);
                boolean isMatch = m.matches();
                if (isMatch) {
                    return false;
                } else {
                    logger.error("增加，更新地址时，手机号码不符合规则，param:" + JSON.toJSONString(sellerAddr));
                    return true;
                }
            }
        }

        if (StringUtils.isBlank(sellerAddr.getContactName())) {
            logger.error("增加，更新地址时，名字为空，param:" + JSON.toJSONString(sellerAddr));
            return true;
        }

        if (StringUtils.isBlank(sellerAddr.getProvince())) {
            logger.error("增加，更新地址时，省为空，param:" + JSON.toJSONString(sellerAddr));
            return true;
        }

        if (StringUtils.isBlank(sellerAddr.getCity())) {
            logger.error("增加，更新地址时，城市为空，param:" + JSON.toJSONString(sellerAddr));
            return true;
        }

        return false;
    }
}
