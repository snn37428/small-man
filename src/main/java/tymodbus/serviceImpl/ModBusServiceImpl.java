package tymodbus.serviceImpl;

import com.modbus.ModbusUtil;
import org.springframework.stereotype.Component;
import tymodbus.service.ModbusService;

/**
 * Created by songningning1 on 2017/8/30.
 */
@Component
public class ModBusServiceImpl implements ModbusService {

    private static final String ip = "127.0.0.1";
    private static final int port = 502;

   // @Scheduled(cron="0/1 * *  * * ? ")
    @Override
    public void ReadTask() {

        System.out.println("-----------=======------------");
    }

    public boolean isReadBySolenoid() {

        if (ModbusUtil.readDigitalInput(ip, port, 24, 5) == 1) {
            return true;
        }

        return false;
    }
}
