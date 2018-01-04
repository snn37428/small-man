package shop.rpc.session.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import shop.base.wxfinal.WXLoginFinal;
import shop.rpc.session.SessionRpc;
import shop.serviceImpl.LoginServiceImpl;
import shop.utils.HttpRequest;

@Service("SessionRpcImpl")
public class SessionRpcImpl implements SessionRpc {

    private final static Logger log = LogManager.getLogger(LoginServiceImpl.class);

    @Override
    public String getSessionWX(String code) {
        //发送请求获取openId
        String params = "appid=" + WXLoginFinal.getWxAppid().trim() + "&secret=" + WXLoginFinal.getwxSecret().trim() + "&js_code=" + code.trim() + "&grant_type=" + WXLoginFinal.getGrant_type().trim();

        try {
            String data = HttpRequest.sendPost(WXLoginFinal.getUrl().trim(), params);
            if (StringUtils.isNotBlank(data)) {
                return data;
            }
        } catch (Exception e) {
            log.error("微信接口获取session，接口异常");
            return null;
        }
        log.info("微信接口获取session，返回值为空");
        return null;
    }
}
