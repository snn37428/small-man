package shop.service;

import shop.pojo.Auc;

import java.util.Map;

/**
 * Created by songningning1 on 2017/9/9.
 */
public interface LoginService {

    /**
     * 获取微信openId
     * @param auc
     * @return
     */
    Map in(Auc auc);

    /**
     * 验证token
     * @param token
     * @return
     */
    Boolean checkToken(String token);

    /**
     * 验证，解密session
     * @param encryptedData
     * @param sessionKey
     * @param vi
     * @return
     */
    Map checkSession(String encryptedData, String sessionKey, String vi);

    /**
     * 验证前台传值，token
     * @param auc
     * @return
     */
    Map checkOnlineToken(Auc auc);

    /**
     * 通过token获取用户opendId
     * @param token
     * @return
     */
    String getOpenIdByToken(String token);

}
