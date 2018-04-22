package shop.serviceImpl;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import shop.base.BaseMap.ResMap;
import shop.dao.TProductImageMapper;
import shop.pojo.ConfigPojo;
import shop.pojo.TProductImage;
import shop.service.ShopService;
import shop.vo.detail.BasicInfo;
import shop.vo.detail.Category;
import shop.vo.detail.DetailVo;
import shop.vo.detail.Pic;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 类说明
 *
 * @author: songningning
 * @date: created in 2018/4/15 15:06
 * @modified: by
 */
@Service("shopServiceImpl")
public class ShopServiceImpl implements ShopService {

    private final static Logger log = LogManager.getLogger(ShopServiceImpl.class);

    @Resource(name = "configImgUrl")
    private ConfigPojo imgUrl;

    @Resource(name = "tProductImageMapper")
    private TProductImageMapper tProductImageMapper;

    @Override
    public Map goodsDetail(String categoryId) {

        BasicInfo basicInfo = new BasicInfo();
        basicInfo.setId("11");
        basicInfo.setName("兰蔻「小黑瓶」精华肌底眼部凝霜眼霜15ml 眼膜霜 淡化细纹黑眼圈");
        basicInfo.setMinPrice("23");
        basicInfo.setStores(21);
        basicInfo.setPic("https://cdn.it120.cc/apifactory/2017/04/26/a91ca6e8a3b73163664ef39eb8237d76.jpg");

        DetailVo dv = new DetailVo();
        dv.setBasicInfo(basicInfo);

        Category ca = new Category();
        ca.setIcon("https://cdn.it120.cc/apifactory/2017/04/23/d3d2c6e15e21b8cb6a7bbeabd4da5242.jpg");
        ca.setId("36");
        ca.setName("hahh de");
        dv.setCategory(ca);


        List<TProductImage> listProductImage = tProductImageMapper.listProductImage(Integer.parseInt(categoryId));
        if(CollectionUtils.isEmpty(listProductImage)) {
            log.info("商品详情页，获取商品详情时，上部图片列表为空，categoryId ：" + categoryId);
        }
        List<Pic> pics = new ArrayList<Pic>();
        for (TProductImage tProductImage : listProductImage) {
            if (StringUtils.isBlank(tProductImage.getLarge())) {
                log.info("商品详情页，获取商品详情时，部分图片列表为空，categoryId ：" + categoryId);
                continue;
            }
            Pic pic = new Pic();
            pic.setPic(imgUrl + tProductImage.getLarge());
            pic.setGoodsId(categoryId);
            pics.add(pic);
        }
        dv.setPics(pics);
        return ResMap.successDataMap(dv, "获取商品列表成功");
    }
}
