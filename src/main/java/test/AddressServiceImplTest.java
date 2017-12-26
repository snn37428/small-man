package test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import shop.serviceImpl.AddressServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-config.xml"})
public class AddressServiceImplTest {
    @Autowired
    private AddressServiceImpl addressServiceImpl;

    @Test
    public void tset(){
        addressServiceImpl.insertAddress();
    }

}