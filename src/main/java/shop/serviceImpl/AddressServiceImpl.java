package shop.serviceImpl;

import org.springframework.stereotype.Service;
import shop.dao.TSellerAddrMapper;
import shop.pojo.TSellerAddr;
import shop.service.AddressService;

import javax.annotation.Resource;

@Service("AddressServiceImpl")
public class AddressServiceImpl implements AddressService {

    @Resource(name = "TSellerAddrMapper")
    private TSellerAddrMapper tSellerAddrMapper;

    @Override
    public int insertAddress() {

        tSellerAddrMapper.insert(new TSellerAddr());



        return 0;
    }
}
