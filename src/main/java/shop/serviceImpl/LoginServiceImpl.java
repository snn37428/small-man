package shop.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.base.BaseMap.ResMap;
import shop.base.EnumCode.ResEnum;
import shop.base.wxfinal.WXLoginFinal;
import shop.dao.TyUserMapper;
import shop.pojo.TyUser;
import shop.service.LoginService;
import shop.utils.AesCbcUtil;
import shop.utils.HttpRequest;
import shop.utils.Md5Utils;
import shop.utils.RedisUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
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

    @Autowired
    private RedisUtils redisUtils;


    public Map in(String code) {

        try {
            return this.getSession(code);
        } catch (Exception e) {
            log.error("获取微信openId失败", e);
            return ResMap.getFailedMap(ResEnum.INTERFACE_ERROR.getKey(), ResEnum.INTERFACE_ERROR.getValue());
        }
    }

    public Boolean checkToken(String token) {

        //缓存，token验证
        if (StringUtils.isNotBlank(redisUtils.get(token))) {
            log.info("缓存，获取微信openId成功！token:" + token);
            return true;
        }
        //数据库，token验证
        TyUser tyUser = tyUserMapper.selectByToken(token);
        if (tyUser == null || StringUtils.isBlank(tyUser.getSessionkey())) {
            log.info("数据库，查询openId失败。token:" + token);
            return false;
        }
        //token时间计算
        if (StringUtils.isNotBlank(token) && new Date().getTime() - tyUser.getCreate().getTime() < timeOut) {
            log.info("数据库，验证token成功" + token);
            return true;
        }
        return false;
    }

    private Map getSession(String code) {

        String params = new StringBuilder().append("appid=").append(WXLoginFinal.getWxAppid().trim()).append("&secret=").append(WXLoginFinal.getwxSecret().trim()).append("&js_code=").append(code.trim()).
                append(WXLoginFinal.getGrant_type().trim()).toString();

        // String params = "appid=" + WXLoginFinal.getWxAppid().trim() + "&secret=" + WXLoginFinal.getwxSecret().trim() + "&js_code=" + code.trim() + "&grant_type=" + WXLoginFinal.getGrant_type().trim();
        //发送请求获取openId
        String data = HttpRequest.sendPost(WXLoginFinal.getUrl().trim(), params);

        if (StringUtils.isBlank(data)) {
            log.info("获取微信openId失败,返回data为空,code:" + code);
            return ResMap.getFailedMap(ResEnum.RES_RESULT_NULL.getKey(), ResEnum.RES_RESULT_NULL.getValue());
        }

        JSONObject jsonData = JSONObject.parseObject(data);
        String openId = jsonData.get("openid").toString().trim();
        String sessionKey = jsonData.get("session_key").toString().trim();

        if (StringUtils.isBlank(openId) || StringUtils.isBlank(sessionKey)) {
            return ResMap.getFailedMap(ResEnum.RES_RESULT_NULL.getKey(), ResEnum.RES_RESULT_NULL.getValue());
        }
        log.info("获取微信openId成功, code:" + code);
        StringBuffer stringBuffer = new StringBuffer();
        String token = Md5Utils.string2MD5(stringBuffer.append(openId).append(sessionKey).toString());

        Map resMap = new HashMap();
        resMap.put("token", token);
        resMap.put("code", 0);
        redisUtils.set(token, token, WXLoginFinal.getTimeOut());
        log.info("获取微信openId成功后,设置缓存成功,code:" + code + "token:" + token);
        TyUser tyUser = new TyUser();
        tyUser.setToken(token);
        tyUser.setOpenid(openId);
        tyUser.setSessionkey(sessionKey);
        tyUserMapper.insert(tyUser);
        log.info("获取微信openId成功后,设置数据库成功,code:" + code + "token:" + token);
        return ResMap.getSuccessMap(resMap);
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
