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
import shop.vtp.AddressVtp;
import shop.vtp.AddressVtpUp;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
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

    //验证token
    @Resource(name = "LoginServiceImpl")
    private LoginServiceImpl loginService;

    @Override
    public Map insertAddress(AddressVtp addressVtp) {

        if (addressVtp == null) {
            logger.info("增加地址，参数为空，param:" + JSON.toJSONString(addressVtp));
            return ResMap.getNullParamMap();
        }

        if (this.validateParam(addressVtp)) {
            return ResMap.getFailedMap(ResEnum.REQ_PARAM_ERROR.getKey(), ResEnum.REQ_PARAM_ERROR.getValue());
        }

        String openId = getOpenId(addressVtp.getToken());
        if (StringUtils.isBlank(openId)) {
            return ResMap.getFailedMap(ResEnum.RES_RESULT_NULL.getKey(), ResEnum.RES_RESULT_NULL.getValue());
        }

        TSellerAddr sellerAddr = new TSellerAddr();
        sellerAddr.setContactName(addressVtp.getLinkMan());//姓名
        sellerAddr.setPhone(addressVtp.getMobile());//手机
        sellerAddr.setAddr(addressVtp.getAddress());//地址
        sellerAddr.setSellerId(openId);//token
        sellerAddr.setProvinceId(addressVtp.getProvinceId());
        sellerAddr.setProvinceName(addressVtp.getProvinceName());
        sellerAddr.setCityId(addressVtp.getCityId());
        sellerAddr.setCityName(addressVtp.getCityName());
        sellerAddr.setDistrictId(Integer.valueOf(addressVtp.getDistrictId()));
        sellerAddr.setDistrictName(addressVtp.getDistrictName());
        sellerAddr.setActive((addressVtp.getIsDefault().equals("active")) ? 1 : 0);
        sellerAddr.setActive(1);
        sellerAddr.setZipCode(addressVtp.getCode());
        sellerAddr.setCreated(new Date());

        if (tSellerAddrMapper.insert(sellerAddr) > 0) {
            return ResMap.rightCodeMap("数据库插入成功");
        } else {
            return ResMap.errCodeMap("数据库插入失败");
        }
    }

    @Override
    public Map deleteAddress(String id) {
        if (StringUtils.isBlank(id)) {
            logger.info("更新地址，参数为空，param:" + id);
            return ResMap.getNullParamMap();
        }
        TSellerAddr tSellerAddr = new TSellerAddr();
        tSellerAddr.setId(Long.valueOf(id));
        tSellerAddr.setActive(0);
        if (tSellerAddrMapper.updateByPrimaryKeySelective(tSellerAddr) > 0) {
            return ResMap.getSuccessMap("数据库修改成功");
        } else {
            return ResMap.getFailedMap("数据库修改失败");
        }
    }

    @Override
    public Map updteAddress(AddressVtp addressVtp) {
        if (this.validateParam(addressVtp)) {
            return ResMap.getFailedMap(ResEnum.REQ_PARAM_ERROR.getKey(), ResEnum.REQ_PARAM_ERROR.getValue());
        }
        TSellerAddr sellerAddr = new TSellerAddr();
        sellerAddr.setContactName(addressVtp.getLinkMan());//姓名
        sellerAddr.setPhone(addressVtp.getMobile());//手机
        sellerAddr.setAddr(addressVtp.getAddress());//地址
        sellerAddr.setProvinceId(addressVtp.getProvinceId());
        sellerAddr.setProvinceName(addressVtp.getProvinceName());
        sellerAddr.setCityId(addressVtp.getCityId());
        sellerAddr.setCityName(addressVtp.getCityName());
        sellerAddr.setDistrictId(Integer.valueOf(addressVtp.getDistrictId()));
        sellerAddr.setDistrictName(addressVtp.getDistrictName());
        sellerAddr.setActive((addressVtp.getIsDefault().equals("active")) ? 1 : 0);
        sellerAddr.setZipCode(addressVtp.getCode());
        sellerAddr.setCreated(new Date());
        sellerAddr.setId(Long.valueOf(addressVtp.getId()));
        if (tSellerAddrMapper.updateByPrimaryKeySelective(sellerAddr) > 0) {
            return ResMap.rightCodeMap("数据库插入成功");
        } else {
            return ResMap.errCodeMap("数据库插入失败");
        }
    }

    @Override
    public Map defaultAddress(Auc auc) {
        if (auc == null || StringUtils.isBlank(auc.getToken())) {
            logger.info("获取当个地址，参数为空，param:" + JSON.toJSONString(auc));
            return ResMap.getNullParamMap();
        }
        String openId = getOpenId(auc.getToken());
        if (StringUtils.isBlank(openId)) {
            return ResMap.getFailedMap(ResEnum.RES_RESULT_NULL.getKey(), ResEnum.RES_RESULT_NULL.getValue());
        }
        TSellerAddr tSellerAddr = tSellerAddrMapper.selectByPrimaryMinId(openId);
        if (tSellerAddr == null) {
            logger.info("获取地址，参数为空，param:" + JSON.toJSONString(auc));
            return ResMap.getNullParamMap();
        }
        AddressVtpUp addressVtpUp = new AddressVtpUp();
        addressVtpUp.setId(String.valueOf(tSellerAddr.getId()));
        addressVtpUp.setDistrictId(String.valueOf(tSellerAddr.getDistrictId()));
        addressVtpUp.setProvinceId(tSellerAddr.getProvinceId());
        addressVtpUp.setLinkMan(tSellerAddr.getContactName());
        addressVtpUp.setCityId(tSellerAddr.getCityId());
        addressVtpUp.setMobile(tSellerAddr.getPhone());
        addressVtpUp.setAddress(tSellerAddr.getAddr());
        addressVtpUp.setProvinceStr(tSellerAddr.getProvinceName());
        addressVtpUp.setCityStr(tSellerAddr.getCityName());
        addressVtpUp.setAreaStr(tSellerAddr.getDistrictName());
        addressVtpUp.setCode(tSellerAddr.getZipCode());
        return ResMap.getMap(ResMap.getSuccessMap(addressVtpUp));
    }


    @Override
    public Map detailId(Auc auc) {
        if (auc == null || StringUtils.isBlank(auc.getId())) {
            logger.info("获取当个地址，参数为空，param:" + JSON.toJSONString(auc));
            return ResMap.getNullParamMap();
        }
        TSellerAddr tSellerAddr = tSellerAddrMapper.selectByPrimaryKey(Long.valueOf(auc.getId()));
        if (tSellerAddr == null) {
            logger.info("获取地址，参数为空，param:" + JSON.toJSONString(auc));
            return ResMap.getNullParamMap();
        }
        AddressVtpUp addressVtpUp = new AddressVtpUp();
        addressVtpUp.setId(String.valueOf(tSellerAddr.getId()));
        addressVtpUp.setDistrictId(String.valueOf(tSellerAddr.getDistrictId()));
        addressVtpUp.setProvinceId(tSellerAddr.getProvinceId());
        addressVtpUp.setLinkMan(tSellerAddr.getContactName());
        addressVtpUp.setCityId(tSellerAddr.getCityId());
        addressVtpUp.setMobile(tSellerAddr.getPhone());
        addressVtpUp.setAddress(tSellerAddr.getAddr());
        addressVtpUp.setProvinceStr(tSellerAddr.getProvinceName());
        addressVtpUp.setCityStr(tSellerAddr.getCityName());
        addressVtpUp.setAreaStr(tSellerAddr.getDistrictName());
        addressVtpUp.setCode(tSellerAddr.getZipCode());
        return ResMap.getMap(ResMap.getSuccessMap(addressVtpUp));
    }


    @Override
    public Map detail(Auc auc) {

        if (auc == null) {
            logger.info("获取地址，参数为空，param:" + JSON.toJSONString(auc));
            return ResMap.getNullParamMap();
        }
        String openId = getOpenId(auc.getToken());
        if (StringUtils.isBlank(openId)) {
            logger.info("获取全部地址，Token有空值，param:" + JSON.toJSONString(auc));
            return ResMap.getNullParamMap();
        }
        List<TSellerAddr> addressList = tSellerAddrMapper.selectListAddress(openId);
        if (CollectionUtils.isEmpty(addressList)) {
            logger.info("获取全部地址，查询数据为空，param:" + JSON.toJSONString(auc));
            return ResMap.getMap(ResMap.getFailedMap("null"));
        }
        List<AddressVtp> addressListVo = convertAddressVtp(addressList);
        if (CollectionUtils.isEmpty(addressListVo)) {
            logger.info("获取全部地址，查询数据后转视图，param:" + JSON.toJSONString(addressListVo));
            return ResMap.getMap(ResMap.getFailedMap("null"));
        }
       return ResMap.getMap(ResMap.getSuccessMap(addressListVo));
    }


    private Boolean validateParam(AddressVtp addressVtp) {

        if (StringUtils.isBlank(addressVtp.getToken())) {
            logger.error("增加，更新地址时，token为空，param:" + JSON.toJSONString(addressVtp));
            return true;
        }

        String phoneString = addressVtp.getMobile();

        if (StringUtils.isBlank(phoneString)) {
            logger.error("增加，更新地址时，手机号码为空，param:" + JSON.toJSONString(addressVtp));
            return true;
        }

        if (StringUtils.isNotBlank(phoneString)) {
            if (phoneString.length() != 11) {
                logger.error("增加，更新地址时，手机号码位数错误，param:" + JSON.toJSONString(addressVtp));
                return true;
            } else {
                Pattern p = Pattern.compile(phoneReg);
                Matcher m = p.matcher(phoneString);
                boolean isMatch = m.matches();
                if (isMatch) {
                    return false;
                } else {
                    logger.error("增加，更新地址时，手机号码不符合规则，param:" + JSON.toJSONString(addressVtp));
                    return true;
                }
            }
        }

        if (StringUtils.isBlank(addressVtp.getAddress())) {
            logger.error("增加，更新地址时，名字为空，param:" + JSON.toJSONString(addressVtp));
            return true;
        }

        if (StringUtils.isBlank(addressVtp.getProvinceId())) {
            logger.error("增加，更新地址时，省为空，param:" + JSON.toJSONString(addressVtp));
            return true;
        }

        if (StringUtils.isBlank(addressVtp.getCityId())) {
            logger.error("增加，更新地址时，城市为空，param:" + JSON.toJSONString(addressVtp));
            return true;
        }

        return false;
    }

    private String getOpenId(String token) {
        String openId = loginService.getOpenIdByToken(token);
        if (openId == null) {
            logger.info("获取全部地址，token验证未通过");
            return null;
        }
        return openId;
    }

    /**
     * 地址列表转换视图
     * @param addressList
     * @return
     */
    private List<AddressVtp> convertAddressVtp (List<TSellerAddr> addressList) {
        List<AddressVtp> addressVtpList = new ArrayList<AddressVtp>();
        for (TSellerAddr tSellerAddr : addressList) {
            if (tSellerAddr == null || StringUtils.isBlank(tSellerAddr.getContactName()) || StringUtils.isBlank(tSellerAddr.getPhone())
            || StringUtils.isBlank(tSellerAddr.getAddr())) {
                logger.info("获取全部地址，数据为空");
                continue;
            }
            AddressVtp addressVtp = new AddressVtp();
            addressVtp.setAddress(tSellerAddr.getAddr());
            addressVtp.setIsDefault(1 == tSellerAddr.getActive().intValue() ? "active" : "null");
            addressVtp.setLinkMan(tSellerAddr.getContactName());
            addressVtp.setMobile(tSellerAddr.getPhone());
            addressVtp.setId(String.valueOf(tSellerAddr.getId()));
            addressVtpList.add(addressVtp);
        }
        return addressVtpList;
    }
}
