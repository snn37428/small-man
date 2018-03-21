package shop.serviceImpl;

import net.wimpi.modbus.net.TCPMasterConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import shop.dao.SpotMapper;
import shop.pojo.Spot;

import java.net.InetAddress;
import java.util.List;
import java.util.Map;

/**
 * 类说明
 *
 * @author: songningning
 * @date: created in 2018/3/18 21:19
 * @modified: by
 */
public class ModBusTask {

    private Map switchMap;

    @Autowired
    private SpotMapper orderPOMapper;

    public void taskTest(){
        System.out.println("test===========");
    }

    //readInputRegister 3000 地址+1 例如：30004 传入 3
    public void inputRegister(){

        if ("0".equals(switchMap.get("inputRegister"))) {
            System.out.println("=====readInputRegister，3000地址，任务关闭");
        }

        List<Spot> ListInt = LoadConfigService.getSpotListInt();
        if (CollectionUtils.isEmpty(LoadConfigService.getSpotListInt())) {
            System.out.println("程序初始化开始，加载Int配置.....长度为零");
        }

        try {
            InetAddress addr = InetAddress.getByName(LoadConfigService.getIp());
            TCPMasterConnection con = new TCPMasterConnection(addr);
            con.setPort(Integer.parseInt(LoadConfigService.getPort()));
            //           con.connect();
            for(Spot spt : ListInt) {
//                ReadInputRegistersRequest req = new ReadInputRegistersRequest(Integer.valueOf(spt.getAddressId()), 1);
//                req.setUnitID(1);
//                ModbusTCPTransaction trans = new ModbusTCPTransaction(con);
//                trans.setRequest(req);
//                trans.execute();
//                ReadInputRegistersResponse res = (ReadInputRegistersResponse) trans.getResponse();
//                int data = res.getRegisterValue(0);
                spt.setValue(String.valueOf(0));
            }
            con.close();
        } catch (Exception e) {
            System.out.println("=====readInputRegister，3000地址，批量读取PLC数值异常");
        }

        Spot se = new Spot();
        se.setPlcId(25);
        se.setSpotName("gag");
        orderPOMapper.insert(se);
        System.out.println("test===========");

    }

    public Map getSwitchMap() {
        return switchMap;
    }

    public void setSwitchMap(Map switchMap) {
        this.switchMap = switchMap;
    }
}
