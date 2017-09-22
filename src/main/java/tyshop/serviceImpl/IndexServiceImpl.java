package tyshop.serviceImpl;

import org.springframework.stereotype.Service;
import tyshop.base.BaseMap.ResMap;
import tyshop.model.BannerModel;
import tyshop.service.IndexService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by songningning1 on 2017/9/17.
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Override
    public Map listBanner() {

        BannerModel bannerModel = new BannerModel();
        bannerModel.setBusinessId(1);
        bannerModel.setPicUrl("https://cdn.it120.cc/apifactory/2017/04/23/ddba8b8438f5163144f340ef400a90a7.jpg");

        BannerModel bannerModel2 = new BannerModel();
        bannerModel2.setBusinessId(2);
        bannerModel2.setPicUrl("https://cdn.it120.cc/apifactory/2017/04/23/b93e2e307ec6695d258cb72143af0d54.jpg");

        BannerModel bannerModel3 = new BannerModel();
        bannerModel3.setBusinessId(3);
        bannerModel3.setPicUrl("https://cdn.it120.cc/apifactory/2017/04/23/6aa1e03d895ad64014c034e555d28a97.jpg");

        List list = new ArrayList();
        list.add(bannerModel);
        list.add(bannerModel2);
        list.add(bannerModel3);
        return ResMap.getSuccessMap(list);
    }
}
