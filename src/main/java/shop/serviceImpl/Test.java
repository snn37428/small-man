package shop.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import shop.dao.SpotMapper;
import shop.pojo.Spot;

/**
 * 类说明
 *
 * @author: songningning
 * @date: created in 2018/3/18 19:25
 * @modified: by
 */
public class Test {
@Autowired
    private SpotMapper orderPOMapper;

    public void test1 () {
        Spot se = new Spot();
        se.setPlcId(25);
        se.setSpotName("gag");
        orderPOMapper.insert(se);
        System.out.println("8");
    }


}
