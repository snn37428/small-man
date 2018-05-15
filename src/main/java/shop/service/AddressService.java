package shop.service;

import shop.pojo.Auc;
import shop.pojo.TSellerAddr;
import shop.vtp.AddressVtp;

import java.util.Map;

public interface AddressService {
    /**
     * 增加地址
     *
     * @return
     */
    Map insertAddress(AddressVtp addressVtp);

    /**
     * 更改地址
     *
     * @param id
     * @return
     */
    Map deleteAddress(String id);

    /**
     * 更改地址
     *
     * @param id
     * @return
     */
    Map updteAddress(AddressVtp addressVtp);

    /**
     * 获取全部地址
     *
     * @param auc
     * @return
     */
    Map detailId(Auc auc);

    /**
     * 获取全部地址,有Id查询
     *
     * @param auc
     * @return
     */
    Map detail(Auc auc);

    /**
     * 获取默认地址
     * @param auc
     * @return
     */
    Map defaultAddress(Auc auc);
}
