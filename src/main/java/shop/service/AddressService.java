package shop.service;

import shop.base.BaseMap.ResMap;

import java.util.Map;

public interface AddressService {
    /**
     * 增加地址
     *
     * @return
     */
    Map insertAddress(String code);

    /**
     * 更改地址
     * @param code
     * @return
     */
    Map updateAddress(String code);
}
