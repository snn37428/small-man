package com.modbus;

import net.wimpi.modbus.io.ModbusTCPTransaction;
import net.wimpi.modbus.msg.ReadInputDiscretesRequest;
import net.wimpi.modbus.msg.ReadInputDiscretesResponse;
import net.wimpi.modbus.msg.ReadInputRegistersRequest;
import net.wimpi.modbus.msg.ReadInputRegistersResponse;
import net.wimpi.modbus.net.TCPMasterConnection;

import java.net.InetAddress;

/**
 * Created by songningning1 on 2017/8/30.
 */
public class Test {
    public static void main (String args[]) {
        String ip = "127.0.0.1";
        int port = 502;
        int address = 9;
        int slaveId = 45;
        int result = 0;

        try {
            InetAddress addr = InetAddress.getByName(ip);
            TCPMasterConnection con = new TCPMasterConnection(addr);
            con.setPort(port);
            con.connect();

            ReadInputRegistersRequest req = new ReadInputRegistersRequest(address, 1);
            req.setUnitID(slaveId);

            ModbusTCPTransaction trans = new ModbusTCPTransaction(con);

            trans.setRequest(req);
            trans.execute();


            ReadInputRegistersResponse res = (ReadInputRegistersResponse) trans.getResponse();

            result = res.getRegisterValue(0);

            System.out.println(result);


            con.close();



        } catch (Exception e) {
            e.printStackTrace();
        }finally {

        }


    }
}
