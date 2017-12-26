package shop.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.dao.TSellerAddrMapper;
import shop.pojo.TSellerAddr;
import shop.service.AddressService;
@Service("AddressServiceImpl")
public class AddressServiceImpl implements AddressService {

    @Autowired
    private TSellerAddrMapper tSellerAddrMapper;

    @Override
    public int insertAddress() {

        tSellerAddrMapper.insert(new TSellerAddr());



        return 0;
    }
}
