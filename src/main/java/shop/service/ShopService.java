package shop.service;

import java.util.Map;

/**
 * 类说明
 *
 * @author: songningning
 * @date: created in 2018/4/15 15:04
 * @modified: by
 */
public interface ShopService {

    /**
     * 获取商品详情
     * @param categoryId
     * @return
     */
    Map goodsDetail(String categoryId);
}
