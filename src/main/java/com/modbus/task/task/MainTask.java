package com.modbus.task.task;

import com.modbus.task.util.Modbus4jUtils;
import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.locator.BaseLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 类说明
 *
 * @author: songningning
 * @date: created in 2018/4/4 18:46
 * @modified: by
 */
public class MainTask {

    Logger log = LoggerFactory.getLogger(MainTask.class);
    private Map taskSwitchMap;
    private Map dataSwitchMap;
    private Map tableIndexMap;

    /**
     * 读取[04 Input Registers 3x]类型 模拟量数据
     */
    public void readInputRegisters() {

        BaseLocator<Number> loc = BaseLocator.inputRegister(slaveId, offset, dataType);


        BatchRead<Integer> batch = new BatchRead<Integer>();
        BaseLocator<Number> value = BaseLocator.inputRegister(1, 1, DataType.TWO_BYTE_INT_UNSIGNED);
        batch.addLocator(0, value);










        Number value = null;
        try {
            value = Modbus4jUtils.getMaster().getValue(loc);
        } catch (ModbusTransportException e) {
            e.printStackTrace();
        } catch (ErrorResponseException e) {
            e.printStackTrace();
        } catch (ModbusInitException e) {
            e.printStackTrace();
        }
        return value;

    }















    public Map getTaskSwitchMap() {
        return taskSwitchMap;
    }

    public void setTaskSwitchMap(Map taskSwitchMap) {
        this.taskSwitchMap = taskSwitchMap;
    }

    public Map getDataSwitchMap() {
        return dataSwitchMap;
    }

    public void setDataSwitchMap(Map dataSwitchMap) {
        this.dataSwitchMap = dataSwitchMap;
    }

    public Map getTableIndexMap() {
        return tableIndexMap;
    }

    public void setTableIndexMap(Map tableIndexMap) {
        this.tableIndexMap = tableIndexMap;
    }
}
