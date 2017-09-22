package tyshop.service;

import java.util.Map;

/**
 * Created by songningning1 on 2017/9/9.
 */
public interface LoginService {

    /**
     * 获取微信openId
     * @param code
     * @return
     */
    Map in(String code);

    /**
     * 验证token
     * @param token
     * @return
     */
    Map checkToken(String token);

    /**
     * 验证，解密session
     * @param encryptedData
     * @param sessionKey
     * @param vi
     * @return
     */
    Map checkSession(String encryptedData, String sessionKey, String vi);
}
