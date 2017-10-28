package shop.model;

import java.util.Date;

/**
 * Created by songningning1 on 2017/10/23.
 */
public class BuyerReceiverModel {
    private int id;//id
    private int bugerId;//会员
    private String receiverName;//收货人
    private int areaId;//地区编号
    private String areaTreePath;
    private String province;//省
    private String city;//城市
    private String district;//地区
    private String phone;//电话
    private String address;//地址
    private int isDefault;//是否默认
    private Date created;
    private Date updated;
    private int active;//是否删除

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBugerId() {
        return bugerId;
    }

    public void setBugerId(int bugerId) {
        this.bugerId = bugerId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAreaTreePath() {
        return areaTreePath;
    }

    public void setAreaTreePath(String areaTreePath) {
        this.areaTreePath = areaTreePath;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
}
