package shop.service;

import java.util.Map;

public interface GoodsService {
    /**
     * 通过categoryId获取Product 详情
     *
     * @param categoryId
     * @return
     */
    Map productList(long categoryId);
}
