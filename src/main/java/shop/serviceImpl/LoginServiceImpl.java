package shop.serviceImpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import shop.base.BaseMap.ResJson;
import shop.base.BaseMap.ResMap;
import shop.base.EnumCode.ResEnum;
import shop.base.wxfinal.WXLoginFinal;
import shop.dao.TyUserMapper;
import shop.pojo.Auc;
import shop.pojo.TyUser;
import shop.rpc.session.SessionRpc;
import shop.service.LoginService;
import shop.utils.AesCbcUtil;
import shop.utils.HttpRequest;
import shop.utils.Md5Utils;
import shop.utils.RedisUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by songningning1 on 2017/9/9.
 */
@Service("LoginServiceImpl")
public class LoginServiceImpl implements LoginService {

    private final static Logger log = LogManager.getLogger(LoginServiceImpl.class);

    private final static Long timeOut = 60 * 60 * 24 * 3L;

    @Resource(name = "TyUserMapper")
    private TyUserMapper tyUserMapper;

    @Resource(name = "SessionRpcImpl")
    private SessionRpc sessionRpc;

    @Autowired
    private RedisUtils redisUtils;


    public Map<String, Object> in(Auc auc) {

        if (auc == null || StringUtils.isBlank(auc.getCode())) {
            log.info("login.in,参数为空, auc: " + JSON.toJSONString(auc));
            return ResMap.errCodeMap("参数为空");
        }
        try {
            return this.getSessionByWX(auc.getCode());
        } catch (Exception e) {
            log.error("获取微信openId失败", e);
            return ResMap.getFailedMap(ResEnum.INTERFACE_ERROR.getKey(), ResEnum.INTERFACE_ERROR.getValue());
        }
    }

    /**
     * 微信接口opendId
     * @param code
     * @return
     */
    public Map getSessionByWX(String code) {
        String data = sessionRpc.getSessionWX(code);
        if (StringUtils.isBlank(data)) {
            log.info("获取微信openId失败,返回data为空,code:" + code);
            return ResMap.getFailedMap(ResEnum.RES_RESULT_NULL.getKey(), ResEnum.RES_RESULT_NULL.getValue());
        }
        try {
            JSONObject jsonData = JSONObject.parseObject(data);
            String openId = jsonData.get("openid").toString().trim();
            if (StringUtils.isBlank(openId)) {
                log.error("获取微信openId失败，微信接口返回openId数据为空, code:" + code);
                return ResMap.getFailedMap(ResEnum.RES_RESULT_NULL.getKey(), ResEnum.RES_RESULT_NULL.getValue());
            }
            String token = this.getTokenByOpenId(openId);
            if(StringUtils.isNotBlank(token)) {
                Map resMap = new HashMap();
                resMap.put("token", token);
                resMap.put("code", 0);
                return ResMap.successDataMap(resMap, "获取token成功");
            }
            String sessionKey = jsonData.get("session_key").toString().trim();
            if (StringUtils.isBlank(sessionKey)) {
                log.error("获取微信openId失败，微信接口返回sessionKey数据为空, code:" + code);
                return ResMap.getFailedMap(ResEnum.RES_RESULT_NULL.getKey(), ResEnum.RES_RESULT_NULL.getValue());
            }
            return insertSessionKey(openId, sessionKey, code);
        } catch (Exception e) {
            log.error("获取微信openId失败，微信接口解析数据, code:" + code, e);
            return ResMap.getFailedMap(ResEnum.RES_PARAM_ERROR.getKey(), ResEnum.RES_PARAM_ERROR.getValue());
        }
    }

    /**
     * 插入用户登录数据
     * @param openId
     * @param sessionKey
     * @param code
     * @return
     */
    private Map insertSessionKey(String openId, String sessionKey, String code) {
        log.info("获取微信openId成功, code:" + code);
        StringBuffer stringBuffer = new StringBuffer();
        String token = Md5Utils.string2MD5(stringBuffer.append(openId).append(sessionKey).toString());
//        try {
//            //redisUtils.set(token, token, WXLoginFinal.getTimeOut());
//            log.info("获取微信openId成功后,设置缓存成功,code:" + code + ",token:" + token);
//        } catch (Exception e) {
//            log.info("获取微信openId成功后,设置缓存失败,code:" + code + ",token:" + token, e);
//        }
        TyUser tyUser = new TyUser();
        tyUser.setToken(token);
        tyUser.setOpenid(openId);
        tyUser.setSessionkey(sessionKey);
        tyUser.setCreated(new Date());
        try {
            tyUserMapper.insert(tyUser);
            log.info("获取微信openId成功后,插入数据库成功,code:" + code + ",token:" + token);
        } catch (Exception e) {
            log.error("获取微信openId成功后,插入数据库失败,code:" + code + ",token:" + token, e);
        }
        Map resMap = new HashMap();
        resMap.put("token", token);
        resMap.put("code", 0);
        return ResMap.successDataMap(resMap, "获取token成功");
    }

    private String getTokenByOpenId(String openId) {
        List<TyUser> tyUser = tyUserMapper.selectByOpenId(openId);
        if(CollectionUtils.isEmpty(tyUser)) {
            log.info("数据库，查询token失败。openId:" + openId);
            return null;
        }
        TyUser ty = tyUser.get(tyUser.size() - 1);
        if (StringUtils.isNotBlank(openId) && new Date().getTime() - ty.getCreated().getTime() < timeOut) {
            log.info("数据库，查询openId成功" + openId);
            return ty.getToken();
        }
        return null;
    }

    @Override
    public Map checkOnlineToken(Auc auc) {

        if (auc == null || StringUtils.isBlank(auc.getToken())) {
            log.info("token验证参数为空, auc: " + JSON.toJSONString(auc));
            return ResMap.errCodeMap("参数为空");
        }

        if (checkToken(auc.getToken())) {
            log.info("token验证通过, token: " + auc.getToken());
            return ResMap.rightCodeMap("token验证通过");
        } else {
            log.info("token验证未通过, token: " + auc.getToken());
            return ResMap.errCodeMap("token验证未通过");
        }
    }

    public Boolean checkToken(String token) {

        //缓存，token验证
      /*  if (StringUtils.isNotBlank(redisUtils.get(token))) {
            log.info("缓存，获取微信openId成功！token:" + token);
            return true;
        }
      */
        //数据库，token验证
        TyUser tyUser = tyUserMapper.selectByToken(token);
        if (tyUser == null || StringUtils.isBlank(tyUser.getSessionkey())) {
            log.info("数据库，查询openId验证token，失败。token:" + token);
            return false;
        }
        //token时间计算
        if (StringUtils.isNotBlank(token) && new Date().getTime() - tyUser.getCreated().getTime() < timeOut) {
            log.info("数据库，验证token成功" + token);
            return true;
        }
        return false;
    }

    public String getOpenIdByToken(String token) {

        //缓存，token验证
      /*  if (StringUtils.isNotBlank(redisUtils.get(token))) {
            log.info("缓存，获取微信openId成功！token:" + token);
            return true;
        }
      */
        TyUser tyUser = tyUserMapper.selectByToken(token);
        if (tyUser == null || StringUtils.isBlank(tyUser.getOpenid())) {
            log.info("数据库，查询openId失败。token:" + token);
            return null;
        }
        if (StringUtils.isNotBlank(token)) {
            log.info("数据库，验证token成功" + token);
            return tyUser.getOpenid();
        }
        return null;
    }

    public Map checkSession(String encryptedData, String sessionKey, String vi) {

        String params = "appid=" + WXLoginFinal.getWxAppid().trim() + "&secret=" + WXLoginFinal.getwxSecret().trim() + "&js_code=" + sessionKey.trim() + "&grant_type=" + WXLoginFinal.getGrant_type().trim();
        String data = HttpRequest.sendPost("https://api.weixin.qq.com/sns/jscode2session?", params);
        JSONObject jsonData = JSONObject.parseObject(data);
        String session_key = jsonData.get("session_key").toString();
        String openid = jsonData.get("openid").toString();
        String expires_in = jsonData.get("expires_in").toString();
        new AesCbcUtil(session_key).decrypt(session_key, "1");

        return null;
    }
}
