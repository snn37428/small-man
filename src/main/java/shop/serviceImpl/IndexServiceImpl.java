package shop.serviceImpl;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import shop.base.BaseMap.ResMap;
import shop.dao.ProductPOMapper;
import shop.dao.TProductImageMapper;
import shop.model.BannerModel;
import shop.pojo.ConfigPojo;
import shop.pojo.ProductPO;
import shop.pojo.TProductImage;
import shop.service.IndexService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by songningning1 on 2017/9/17.
 */
@Service("IndexServiceImpl")
public class IndexServiceImpl implements IndexService {

    private final static Logger log = LogManager.getLogger(IndexServiceImpl.class);

    @Resource(name = "productPOMapper")
    private ProductPOMapper productPOMapper;

    @Resource(name = "tProductImageMapper")
    private TProductImageMapper tProductImageMapper;

    @Resource(name = "configImgUrl")
    private ConfigPojo imgUrl;

    public Map listBanner() {

        ProductPO prdt = productPOMapper.selectByNameDesc("主页轮播图");
        if (prdt == null || prdt.getId() == 0) {
            log.info("首页轮播图为空，未找到 ‘首页轮播图’ 字样");
        }
        List<TProductImage> tpImagelist = tProductImageMapper.listProductImage(prdt.getId().intValue());
        if (tpImagelist == null) {
            log.info("首页轮播图为空，未找到ID对应的分类图片");
        }
        List<BannerModel> list = new ArrayList<BannerModel>();
        for(TProductImage tp : tpImagelist) {
            if (tp == null || StringUtils.isBlank(tp.getLarge())) {
                log.info("首页轮播图为空，ID对应的图片为空");
                continue;
            }
            BannerModel bannerModel = new BannerModel();
            bannerModel.setBusinessId(tp.getId());
            bannerModel.setPicUrl(imgUrl.getImgUrl() + tp.getLarge());
            list.add(bannerModel);
        }
        return ResMap.getSuccessMap(list);
    }
}
