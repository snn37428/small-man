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
import shop.service.LoginService;
import shop.utils.AesCbcUtil;
import shop.utils.HttpRequest;
import shop.utils.Md5Utils;
import shop.utils.RedisUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by songningning1 on 2017/9/9.
 */
@Service("LoginServiceImpl")
public class LoginServiceImpl implements LoginService {


    private final static Logger log = LogManager.getLogger(LoginServiceImpl.class);

    @Autowired
    private RedisUtils redisUtils;


    public Map in(String code) {
        try {
            String params = "appid=" + WXLoginFinal.getWxAppid().trim() + "&secret=" + WXLoginFinal.getwxSecret().trim() + "&js_code=" + code.trim() + "&grant_type=" + WXLoginFinal.getGrant_type().trim();
            //发送请求获取openId
            String data = HttpRequest.sendPost(WXLoginFinal.getUrl().trim(), params);

            if (StringUtils.isBlank(data)) {
                return ResMap.getFailedMap(ResEnum.RES_RESULT_NULL.getKey(), ResEnum.RES_RESULT_NULL.getValue());
            }

            JSONObject jsonData = JSONObject.parseObject(data);
            String openId = jsonData.get("openid").toString().trim();
            String sessionKey = jsonData.get("session_key").toString().trim();

            if (StringUtils.isBlank(openId) || StringUtils.isBlank(sessionKey)) {
                return ResMap.getFailedMap(ResEnum.RES_RESULT_NULL.getKey(), ResEnum.RES_RESULT_NULL.getValue());
            }

            StringBuffer stringBuffer = new StringBuffer();
            String token = Md5Utils.string2MD5(stringBuffer.append(openId).append(sessionKey).toString());

            Map resMap = new HashMap();
            resMap.put("token", token);
            resMap.put("code", 0);
            redisUtils.set("token", token, WXLoginFinal.getTimeOut());

            return ResMap.getSuccessMap(resMap);

        } catch (Exception e) {
            log.error("获取微信openId失败");
        }
        return null;
    }

    public Map checkToken(String token) {

        Map map = new HashMap();
        //token验证失败
        if (org.apache.commons.lang3.StringUtils.isEmpty(redisUtils.get("token"))) {
            map.put("code", ResEnum.RES_PARAM_ERROR.getKey());
        }
        //token验证成功
        map.put("code", ResEnum.ON_ERROR.getKey());
        return ResMap.getMap(map);
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
