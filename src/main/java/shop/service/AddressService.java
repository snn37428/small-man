package shop.service;

import shop.base.BaseMap.ResMap;
import shop.pojo.TSellerAddr;

import java.util.Map;

public interface AddressService {
    /**
     * 增加地址
     *
     * @return
     */
    Map insertAddress(TSellerAddr sellerAddr);

    /**
     * 更改地址
     * @param sellerAddr
     * @return
     */
    Map updateAddress(TSellerAddr sellerAddr);
}
