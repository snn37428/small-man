package shop.vtp;

public class PayOrderVtp {

    private String token;
    private String orderId;
    private String totalFee;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(String totalFee) {
        this.totalFee = totalFee;
    }

    public PayOrderVtp() {
    }

    public PayOrderVtp(String token, String orderId, String totalFee) {
        this.token = token;
        this.orderId = orderId;
        this.totalFee = totalFee;
    }
}
