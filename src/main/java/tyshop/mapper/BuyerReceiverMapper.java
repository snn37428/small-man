package tyshop.mapper;

import tyshop.model.BuyerReceiverModel;

import java.util.List;

/**
 * Created by songningning1 on 2017/10/26.
 */
public interface BuyerReceiverMapper {

    /**
     * 插入数据
     *
     * @param buyerReceiverModel
     * @return
     */
    int insert(BuyerReceiverModel buyerReceiverModel);

    /**
     * 获取BuyerReceiver集合
     * @return
     */
    List<BuyerReceiverModel> listReceiverBySellerId(String SellerId);
}
