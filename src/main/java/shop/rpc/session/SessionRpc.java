package shop.rpc.session;

public interface SessionRpc {
    /**
     * 获取微信Session
     * @param code
     * @return
     */
    String getSessionWX(String code);
}
