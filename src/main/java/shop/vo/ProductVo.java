package shop.vo;

import java.io.Serializable;

/**
 * Created by songningning1 on 2017/9/26.
 */
public class ProductVo implements Serializable{

    private static final long serialVersionUID = 7169105244580471743L;
    private Long id;
    private int userId;
    private int type;
    private int sort;
    private boolean isUse;
    private String key;
    private String name;
    private String nameDesc;
    private String icon;
    private String level;
    private String minPrice;
    private String pic;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public boolean isUse() {
        return isUse;
    }

    public void setUse(boolean use) {
        isUse = use;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameDesc() {
        return nameDesc;
    }

    public void setNameDesc(String nameDesc) {
        this.nameDesc = nameDesc;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(String minPrice) {
        this.minPrice = minPrice;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public ProductVo() {
    }

    public ProductVo(Long id, String name, String minPrice, String pic) {
        this.id = id;
        this.name = name;
        this.minPrice = minPrice;
        this.pic = pic;
    }
}
