package tyshop.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tyshop.base.BaseMap.ResMap;
import tyshop.base.EnumCode.ResEnum;
import tyshop.base.wxfinal.LoginFinal;
import tyshop.service.LoginService;
import tyshop.utils.AesCbcUtil;
import tyshop.utils.HttpRequest;
import tyshop.utils.Md5Utils;
import tyshop.utils.RedisUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by songningning1 on 2017/9/9.
 */
@Service
public class LoginServiceImpl implements LoginService {


    private final static Logger log = LogManager.getLogger(LoginServiceImpl.class);

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Map in(String code) {

        try {
            String params = "appid=" + LoginFinal.getWxAppid().trim() + "&secret=" + LoginFinal.getwxSecret().trim() + "&js_code=" + code.trim() + "&grant_type=" + LoginFinal.getGrant_type().trim();
            //发送请求获取openId
            String data = HttpRequest.sendPost(LoginFinal.getUrl().trim(), params);

            if (StringUtils.isEmpty(data)) {
                return ResMap.getFailedMap(ResEnum.RES_RESULT_NULL.getKey(), ResEnum.RES_RESULT_NULL.getValue());
            }

            JSONObject jsonData = JSONObject.parseObject(data);
            String openId = jsonData.get("openid").toString().trim();
            String sessionKey = jsonData.get("session_key").toString().trim();

            if (StringUtils.isEmpty(openId) || StringUtils.isEmpty(sessionKey)) {
                return ResMap.getFailedMap(ResEnum.RES_RESULT_NULL.getKey(), ResEnum.RES_RESULT_NULL.getValue());
            }

            StringBuffer stringBuffer = new StringBuffer();
            String token = Md5Utils.string2MD5(stringBuffer.append(openId).append(sessionKey).toString());

            Map resMap = new HashMap();
            resMap.put("token", token);
            resMap.put("code", 0);
            redisUtils.set("token", token, LoginFinal.getTimeOut());

            return ResMap.getSuccessMap(resMap);

        } catch (Exception e) {
            log.error("获取微信openId失败");
        }
        return null;
    }

    public Map checkToken(String token) {

        Map map = new HashMap();
        //token验证失败
        if (StringUtils.isEmpty(redisUtils.get("token"))) {
            map.put("code", ResEnum.RES_PARAM_ERROR.getKey());
        }
        //token验证成功
        map.put("code", ResEnum.ON_ERROR.getKey());
        return ResMap.getMap(map);
    }

    public Map checkSession(String encryptedData, String sessionKey, String vi) {

        String params = "appid=" + LoginFinal.getWxAppid().trim() + "&secret=" + LoginFinal.getwxSecret().trim() + "&js_code=" + sessionKey.trim() + "&grant_type=" + LoginFinal.getGrant_type().trim();
        String data = HttpRequest.sendPost("https://api.weixin.qq.com/sns/jscode2session?", params);
        JSONObject jsonData = JSONObject.parseObject(data);
        String session_key = jsonData.get("session_key").toString();
        String openid = jsonData.get("openid").toString();
        String expires_in = jsonData.get("expires_in").toString();
        new AesCbcUtil(session_key).decrypt(session_key, "1");

        return null;
    }
}
