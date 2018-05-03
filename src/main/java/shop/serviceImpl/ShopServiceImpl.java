package shop.serviceImpl;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import shop.base.BaseMap.ResMap;
import shop.dao.ProductPOMapper;
import shop.dao.TProductImageMapper;
import shop.pojo.ConfigPojo;
import shop.pojo.ProductPO;
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

    @Resource(name = "productPOMapper")
    private ProductPOMapper productPOMapper;

    @Override
    public Map goodsDetail(String categoryId) {

        DetailVo detailVo = new DetailVo();

        Category ca = new Category();
        ca.setIcon("http://localhost:8089/upload/2/20180421/Chrysanthemum1.jpg");
        ca.setId("36");
        ca.setName("hahh de");
        detailVo.setCategory(ca);
        List<Pic> pics = new ArrayList<Pic>();
        //上部滚动图片
        List<TProductImage> listProductImage = tProductImageMapper.listProductImage(Integer.parseInt(categoryId));
        if(CollectionUtils.isEmpty(listProductImage)) {
            log.info("商品详情页，获取商品详情时，上部图片列表为空，categoryId ：" + categoryId);
        }
        for (TProductImage tProductImage : listProductImage) {
            if (StringUtils.isBlank(tProductImage.getLarge())) {
                log.info("商品详情页，获取商品详情时，部分图片列表为空，categoryId ：" + categoryId);
                continue;
            }
            Pic pic = new Pic();
            pic.setPic(imgUrl.getImgUrl() + tProductImage.getLarge());
            pic.setGoodsId(categoryId);
            pic.setId(categoryId);
            pics.add(pic);
        }
        detailVo.setPics(pics);
        //价格，商品介绍
        ProductPO productPO = productPOMapper.selectByPrimaryKey(Long.parseLong(categoryId));
        if (StringUtils.isBlank(productPO.getIntroduction())) {
            log.info("商品详情页，获取商品详情时，商品内容为空，categoryId ：" + categoryId);
        }
        detailVo.setContent(productPO.getIntroduction());

        BasicInfo basicInfo = new BasicInfo();
        basicInfo.setId(String.valueOf(productPO.getId()));
        basicInfo.setName(productPO.getNameDesc());
        basicInfo.setMinPrice(productPO.getPrice());
        basicInfo.setStores(productPO.getStock());
        basicInfo.setPic(imgUrl.getImgUrl() + productPO.getImage());
        detailVo.setBasicInfo(basicInfo);


        return ResMap.successDataMap(detailVo, "获取商品列表成功");
    }
}
