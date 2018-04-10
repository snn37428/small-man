package com.modbus.task.util;

import com.modbus.task.pojo.Spot;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 类说明
 *
 * @author: songningning
 * @date: created in 2018/4/4 18:28
 * @modified: by
 */
public class LoadConfig {

    private static String ip = "";
    private static String port = "";
    private Properties configSpot;
    //布尔量数组对象
    private static List<Spot> CODELIST_ON = new ArrayList<Spot>();
    private static List<Spot> CODELIST_TH = new ArrayList<Spot>();
    private static List<Spot> CODELIST_FR = new ArrayList<Spot>();


    /**
     * 初始化配置
     */
    private void init() {
        System.out.println("程序初始化开始，加载配置============开始====================================");
        this.ip = configSpot.get("ip").toString();
        this.port = configSpot.get("port").toString();
        initBool();//1
        initInt();//3
        initReal();//4
        System.out.println("程序初始化开始，加载配置============成功====================================");
    }

    private void initBool() {
        System.out.println("程序初始化开始，加载布尔配置.....开始");
        String[] plcIdB = configSpot.get("plcId_Bool").toString().split("\\,");
        String[] spotNameB = configSpot.get("spotName_Bool").toString().split("\\,");
        String[] spotDescB = configSpot.get("spotDesc_Bool").toString().split("\\,");
        String[] addressIdB = configSpot.get("addressId_Bool").toString().split("\\,");
        if (plcIdB.length == 0 || spotNameB.length == 0 || spotDescB.length == 0 || addressIdB.length == 0) {
            System.out.println("程序初始化开始，加载布尔配置.....失败(配置存在空值)");
        }
        if (plcIdB.length != spotNameB.length || spotNameB.length != spotDescB.length || addressIdB.length != addressIdB.length) {
            System.out.println("程序初始化开始，加载布尔配置.....失败(配置不等长)");
        }
        doForList(plcIdB, spotNameB, spotDescB, addressIdB, CODELIST_ON);
        System.out.println("程序初始化开始，加载布尔配置.....成功");
    }

    private void initInt() {
        System.out.println("程序初始化开始，加载Int配置.....开始");
        String[] plcIdInt = configSpot.get("plcId_Int").toString().split("\\,");
        String[] spotNameInt = configSpot.get("spotName_Int").toString().split("\\,");
        String[] spotDescInt = configSpot.get("spotDesc_Int").toString().split("\\,");
        String[] addressIdInt = configSpot.get("addressId_Int").toString().split("\\,");
        if (plcIdInt.length == 0 || spotNameInt.length == 0 || spotDescInt.length == 0 || addressIdInt.length == 0) {
            System.out.println("程序初始化开始，加载Int配置.....失败(配置存在空值)");
        }
        if (plcIdInt.length != spotNameInt.length || spotNameInt.length != spotDescInt.length || spotDescInt.length != addressIdInt.length) {
            System.out.println("程序初始化开始，加载Int配置.....失败(布尔值相关项，配置不等长)");
        }
        doForList(plcIdInt, spotNameInt, spotDescInt, addressIdInt, CODELIST_TH);
        System.out.println("程序初始化开始，加载Int配置.....成功");

    }

    private void initReal() {
        System.out.println("程序初始化开始，加载Real配置.....开始");
        String[] plcIdReal = configSpot.get("plcId_Real").toString().split("\\,");
        String[] spotNameReal = configSpot.get("spotName_Real").toString().split("\\,");
        String[] spotDescReal = configSpot.get("spotDesc_Real").toString().split("\\,");
        String[] addressIdReal = configSpot.get("addressId_Real").toString().split("\\,");
        if (plcIdReal.length == 0 || spotNameReal.length == 0 || spotDescReal.length == 0 || addressIdReal.length == 0) {
            System.out.println("程序初始化开始，加载Real配置.....失败(配置存在空值)");
        }
        if (plcIdReal.length != spotNameReal.length || spotNameReal.length != spotDescReal.length || spotDescReal.length != addressIdReal.length) {
            System.out.println("程序初始化开始，加载Real配置.....失败(配置不等长)");
        }
        doForList(plcIdReal, spotNameReal, spotDescReal, addressIdReal, CODELIST_FR);
        System.out.println("程序初始化开始，加载Real配置.....成功");
    }

    private void doForList(String[] plcId, String[] spotName, String[] spotDesc, String[] addressId, List<Spot> list) {
        for (int i = 0; i < plcId.length; i++) {
            Spot sptInt = new Spot();
            sptInt.setPlcId(Integer.valueOf(plcId[i]));
            sptInt.setSpotName(spotName[i]);
            sptInt.setSpotDesc(spotDesc[i]);
            sptInt.setModbusAddress(Integer.valueOf(addressId[i]));
            list.add(sptInt);
        }
    }

    public static String getIp() {
        return ip;
    }

    public static void setIp(String ip) {
        LoadConfig.ip = ip;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        LoadConfig.port = port;
    }

    public Properties getConfigSpot() {
        return configSpot;
    }

    public void setConfigSpot(Properties configSpot) {
        this.configSpot = configSpot;
    }

    public static List<Spot> getCodelistOn() {
        return CODELIST_ON;
    }

    public static void setCodelistOn(List<Spot> codelistOn) {
        CODELIST_ON = codelistOn;
    }

    public static List<Spot> getCodelistTh() {
        return CODELIST_TH;
    }

    public static void setCodelistTh(List<Spot> codelistTh) {
        CODELIST_TH = codelistTh;
    }

    public static List<Spot> getCodelistFr() {
        return CODELIST_FR;
    }

    public static void setCodelistFr(List<Spot> codelistFr) {
        CODELIST_FR = codelistFr;
    }
}
