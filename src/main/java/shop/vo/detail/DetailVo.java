package shop.vo.detail;

import java.util.List;

/**
 * 类说明
 *
 * @author: songningning
 * @date: created in 2018/4/15 15:26
 * @modified: by
 */
public class DetailVo {
    private BasicInfo basicInfo;
    private Category category;
    private List<Pic> pics;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BasicInfo getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(BasicInfo basicInfo) {
        this.basicInfo = basicInfo;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Pic> getPics() {
        return pics;
    }

    public void setPics(List<Pic> pics) {
        this.pics = pics;
    }
}
