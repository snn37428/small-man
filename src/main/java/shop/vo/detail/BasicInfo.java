package shop.vo.detail;

/**
 * 类说明
 *
 * @author: songningning
 * @date: created in 2018/4/15 15:10
 * @modified: by
 */
public class BasicInfo {
    private String categoryId;
    private String characteristic;
    private String Id;
    private String minPrice;
    private String name;
    private String status;
    private String paixu;
    private String pic;
    private int stores;

    public int getStores() {
        return stores;
    }

    public void setStores(int stores) {
        this.stores = stores;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaixu() {
        return paixu;
    }

    public void setPaixu(String paixu) {
        this.paixu = paixu;
    }
}
