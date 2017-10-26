package tyshop.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tyshop.service.OrderService;
import tyshop.utils.GenerateNum;
import tyshop.utils.RedisUtils;

import java.util.Map;

/**
 * Created by songningning1 on 2017/10/17.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private RedisUtils redisUtils;

    @Override
    public Map create(String str) {



        //GenerateNum.getInstance().GenerateOrder());
        return null;
    }

    @Override
    public Map getOrderByToken(int token) {
        return null;
    }


}
