package com.example.util;

import java.util.List;

/**
 * FileName:com.wps140.util.CommandUtil.java
 * Created on 2016/9/30
 * Version V1.0
 */

public class CommandUtil {

    private volatile static CommandUtil instance;

    private CommandUtil() {
    }

    public static CommandUtil getInstance() {
        synchronized (CommandUtil.class) {
            if (instance == null) instance = new CommandUtil();
        }
        return instance;
    }

    // 设备： DEVICE
    // 手机客户端：APP
    // ACK 成功应答  NACK 失败应答
    public static final String COMMAND_PREFIX = "5555AAAA";

    /**
     * 车载模式
     */
    public static final int TYPE_VEHICLE = 1;

    /**
     * 便携模式
     */
    public static final int TYPE_PORTABLE = 2;

    /*  车载设备消息头请求与反馈消息固定前缀    */
    /**
     * 连接消息请求
     */
    public static final String APP_TO_VEHICLE_LOGIN_REQ = "F001";
    /**
     * 连接消息应答
     */
    public static final String VEHICLE_TO_APP_LOGIN_ACK = "F002";
    public static final String VEHICLE_TO_APP_LOGIN_NACK = "F003";

    /**
     * 扫频配置  启动扫频
     */
    public static final String APP_TO_VEHICLE_SCAN_CFG = "F004";
    /**
     * 扫频应答
     */
    public static final String VEHICLE_TO_APP_SCAN_ACK = "F005";
    public static final String VEHICLE_TO_APP_SCAN_NACK = "F006";
    /**
     * 扫频数据上报
     */
    public static final String VEHICLE_TO_APP_SCAN_RPT = "F007";

    /**
     * 基站配置
     */
    public static final String APP_TO_VEHICLE_BS_PARAM_CFG = "F008";
    /**
     * 基站配置应答
     */
    public static final String VEHICLE_TO_APP_BS_CFG_ACK = "F009";
    public static final String VEHICLE_TO_APP_BS_CFG_NACK = "F00A";

    /**
     * 测量启动
     */
    public static final String APP_TO_VEHICLE_UE_MEAS_START_REQ = "F00B";
    /**
     * 测量启动应答
     */
    public static final String VEHICLE_TO_APP_UE_MEAS_START_ACK = "F00C";
    public static final String VEHICLE_TO_APP_UE_MEAS_START_NACK = "F00D";

    /**
     * 测量停止
     */
    public static final String APP_TO_VEHICLE_UE_MEAS_STOP_REQ = "F010";
    /**
     * 测量停止应答
     */
    public static final String VEHICLE_TO_APP_UE_MEAS_STOP_ACK = "F011";
    public static final String VEHICLE_TO_APP_UE_MEAS_STOP_NACK = "F012";

    /**
     * IMSI采集上报
     */
    public static final String APP_VEHICLE_TO_AP_UE_IDENTITY_RPT = "F013";

    /**
     * 测量数据上报
     */
    public static final String VEHICLE_TO_AP_UE_MEAS_RPT = "F014";

    /**
     * 设备状态查询
     */
    public static final String APP_TO_VEHICLE_STATE_REQ = "F015";
    /**
     * 设备状态应答
     */
    public static final String VEHICLE_TO_APP_STATE_RPT = "F016";

    /**
     * 心跳接口
     */
    public static final String APP_TO_VEHICLE_KEEP_LIVE_REQ = "F017";

    public static final String VEHICLE_TO_APP_KEEP_LIVE_ACK = "F018";

    /**
     * 修改密码
     */
    public static final String APP_TO_VEHICLE_PW_MODIFY_REQ = "F019";
    /**
     * 修改密码应答
     */
    public static final String VEHICLE_TO_APP_PW_MODIFY_ACK = "F01A";
    public static final String VEHICLE_TO_APP_PW_MODIFY_NACK = "F01B";

    /*      以下是便携设备             */
    /**
     * 便携设备连接请求
     */
    public static final String APP_TO_PORTABLE_LOGIN_REQ = "E001";
    /**
     * 便携设备连接应答
     */
    public static final String PORTABLETO_APP_LOGIN_ACK = "E002";
    public static final String PORTABLETO_APP_LOGIN_NACK = "E003";

    /**
     * 测量配置请求
     */
    public static final String APP_TO_PORTABLEBS_PARAM_CFG = "E004";
    /**
     * 测量配置应答
     */
    public static final String PORTABLETO_APP_BS_CFG_ACK = "E005";
    public static final String PORTABLETO_APP_BS_CFG_NACK = "E006";

    /**
     * 启动测量请求
     */
    public static final String APP_TO_PORTABLEUE_MEAS_START_REQ = "E007";
    /**
     * 测量启动应答
     */
    public static final String PORTABLETO_APP_UE_MEAS_START_ACK = "E008";
    public static final String PORTABLETO_APP_UE_MEAS_START_NACK = "E009";

    /**
     * 测量停止
     */
    public static final String APP_TO_PORTABLEUE_MEAS_STOP_REQ = "E010";
    /**
     * 测量停止应答
     */
    public static final String PORTABLETO_APP_UE_MEAS_STOP_ACK = "E011";
    public static final String PORTABLETO_APP_UE_MEAS_STOP_NACK = "E012";

    /**
     * 数据上报
     */
    public static final String PORTABLETO_AP_UE_MEAS_RPT = "E013";

    /**
     * 心跳接口
     */
    public static final String APP_TO_PORTABLEKEEP_LIVE_REQ = "E014";
    public static final String PORTABLETO_APP_KEEP_LIVE_ACK = "E015";

//    public static final int APP_TO_VEHICLE_LOGIN_REQ = 0xF001;
//    public static final int VEHICLE_TO_APP_LOGIN_ACK = 0xF002;
//    public static final int VEHICLE_TO_APP_LOGIN_NACK = 0xF003;
//
//    public static final int APP_TO_VEHICLE_SCAN_CFG = 0xF004;
//
//    public static final int VEHICLE_TO_APP_SCAN_ACK = 0xF005;
//    public static final int VEHICLE_TO_APP_SCAN_NACK = 0xF006;
//
//    public static final int VEHICLE_TO_APP_SCAN_RPT = 0xF007;
//
//    public static final int APP_TO_VEHICLE_BS_PARAM_CFG = 0xF008;
//
//    public static final int VEHICLE_TO_APP_BS_CFG_ACK = 0xF009;
//    public static final int VEHICLE_TO_APP_BS_CFG_NACK = 0xF00A;
//
//    public static final int APP_TO_VEHICLE_UE_MEAS_START_REQ = 0xF00B;
//    public static final int VEHICLE_TO_APP_UE_MEAS_START_ACK = 0xF00C;
//    public static final int VEHICLE_TO_APP_UE_MEAS_START_NACK = 0xF00D;
//
//    public static final int APP_TO_VEHICLE_UE_MEAS_STOP_REQ = 0xF010;
//    public static final int VEHICLE_TO_APP_UE_MEAS_STOP_ACK = 0xF011;
//    public static final int VEHICLE_TO_APP_UE_MEAS_STOP_NACK = 0xF012;
//
//    public static final int APP_VEHICLE_TO_AP_UE_IDENTITY_RPT = 0xF013;
//
//    public static final int VEHICLE_TO_AP_UE_MEAS_RPT = 0xF014;
//
//    public static final int APP_TO_VEHICLE_STATE_REQ = 0xF015;
//
//    public static final int VEHICLE_TO_APP_STATE_RPT = 0xF016;
//
//    public static final int APP_TO_VEHICLE_KEEP_LIVE_REQ = 0xF017;
//    public static final int VEHICLE_TO_APP_KEEP_LIVE_ACK = 0xF018;
//
//    public static final int APP_TO_VEHICLE_PW_MODIFY_REQ = 0xF019;
//    public static final int VEHICLE_TO_APP_PW_MODIFY_ACK = 0xF01A;
//    public static final int VEHICLE_TO_APP_PW_MODIFY_NACK = 0xF01B;
//
//    public static final int APP_TO_PORTABLE_LOGIN_REQ = 0xE001;
//    public static final int PORTABLETO_APP_LOGIN_ACK = 0xE002;
//    public static final int PORTABLETO_APP_LOGIN_NACK = 0xE003;
//
//    public static final int APP_TO_PORTABLEBS_PARAM_CFG = 0xE004;
//
//    public static final int PORTABLETO_APP_BS_CFG_ACK = 0xE005;
//    public static final int PORTABLETO_APP_BS_CFG_NACK = 0xE006;
//
//    public static final int APP_TO_PORTABLEUE_MEAS_START_REQ = 0xE007;
//    public static final int PORTABLETO_APP_UE_MEAS_START_ACK = 0xE008;
//    public static final int PORTABLETO_APP_UE_MEAS_START_NACK = 0xE009;
//
//    public static final int APP_TO_PORTABLEUE_MEAS_STOP_REQ = 0xE010;
//    public static final int PORTABLETO_APP_UE_MEAS_STOP_ACK = 0xE011;
//    public static final int PORTABLETO_APP_UE_MEAS_STOP_NACK = 0xE012;
//
//    public static final int PORTABLETO_AP_UE_MEAS_RPT = 0xE013;
//
//    public static final int APP_TO_PORTABLEKEEP_LIVE_REQ = 0xE014;
//    public static final int PORTABLETO_APP_KEEP_LIVE_ACK = 0xE015;

    /**
     * 拼接心跳接口命令
     * @param type {@link  CommandUtil#TYPE_VEHICLE}  {@link CommandUtil#TYPE_PORTABLE}  连接车载或是便携设备
     * @return
     */
    public String jointHeartBeatCommand(int type){
        return  null;
    }

    /**
     * 拼接连接命令
     * @param type {@link  CommandUtil#TYPE_VEHICLE}  {@link CommandUtil#TYPE_PORTABLE}  连接车载或是便携设备
     * @param curVersion    APP版本
     * @param userName      用户名
     * @param passWord      登录密码
     * @return
     */
    public String  jointLoginCommand(int type ,String curVersion,String userName,String passWord){
        StringBuilder sb = new StringBuilder();
        sb.append(curVersion);
        sb.append(userName);
        sb.append(passWord);
        return jointCommand(type == TYPE_VEHICLE?APP_TO_VEHICLE_LOGIN_REQ:APP_TO_PORTABLE_LOGIN_REQ,sb.toString());
    }

    /**
     * 修改密码/重置密码
     * @param type  {@link  CommandUtil#TYPE_VEHICLE}  {@link CommandUtil#TYPE_PORTABLE}  连接车载或是便携设备
     * @param curVersion    APP版本
     * @param userName      用户名
     * @param passWord      新的登录密码
     * @return
     */
    public String jointModifyPWCommand(int type,String curVersion,String userName,String passWord){
        StringBuilder sb = new StringBuilder();
        sb.append(curVersion);
        sb.append(userName);
        sb.append(passWord);
        return  jointCommand(APP_TO_VEHICLE_PW_MODIFY_REQ,sb.toString());
    }

    /**
     * 车载设备
     * 扫频配置
     * @param mode      工作模式 0-TDD 1-FDD
     * @param chNum     频点数量  1-6
     * @param earfcn    频点  0-35563
     * @return
     */
    public String jointScanCFG(int mode,int chNum,int earfcn){
        StringBuilder sb = new StringBuilder();
        sb.append(mode);
        sb.append(chNum);
        sb.append(earfcn);
        return  jointCommand(APP_TO_VEHICLE_SCAN_CFG,sb.toString());
    }


    /**
     * 车载设备
     * 基站配置
     * @param workMode      工作模式  0-Mode1 1-Mode2 2-Mode3 3-Mode4
     * @param mode          模式  0-TDD 1-FDD
     * @param mnc           运营商  0-移动  1-联通 2-电信
     * @param chNum         频点数目 1-6
     * @param earfcn        频点
     * @param pci           小区标示 0-503
     * @param bandWidth     系统带宽  0-20M 1-10M 2-5M
     * @param powerLevel    发射功率 1-5
     * @param redirectType  指派选择  workMode=3时有效 0-GSM 1-TD-SCDMA 2-WCDMA 3-LTE 4-CDMA-EVDO 5-CDMA-TX
     * @param redirectRFCH  指派频点  workMode=3时有效
     * @param blackList     黑名单     黑、白名单仅需传递IMEI与IMSI(可空)
     * @param whiteList     白名单
     * @return
     */
    public String jointBSParamCFG(int workMode , int mode, int mnc, int chNum, int earfcn, int pci, int bandWidth, int powerLevel, int  redirectType, int redirectRFCH, List<Devicebean> blackList,List<Devicebean> whiteList){
        StringBuilder sb = new StringBuilder();

        return  jointCommand(APP_TO_VEHICLE_BS_PARAM_CFG,sb.toString());
    }

    /**
     * 车载设备
     * 测量启动
     * @param imei
     * @param imsi  可以为空
     * @return
     */
    private  String jointUEMeasStartREQ(String imei , String imsi){
        StringBuilder sb = new StringBuilder();
        sb.append(imei);
        sb.append(imsi==null?"":imsi);
        return  jointCommand(APP_TO_VEHICLE_UE_MEAS_START_REQ,sb.toString());
    }

    /**
     * 便携设备
     * 测量配置
     * @param mode      0-TDD 1-FDD
     * @param earfcn    下行频点
     * @param pci       小区标示：0-503
     * @param bandWidth 系统带宽：0-20M 1-10M 2-5M
     * @return
     */
    private String jointBSParamCFG(int mode, int earfcn ,int pci , int bandWidth){
        StringBuilder sb = new StringBuilder();
        sb.append(mode);
        sb.append(earfcn);
        sb.append(pci);
        sb.append(bandWidth);
        return  jointCommand(APP_TO_PORTABLEBS_PARAM_CFG,sb.toString());
    }

    public String jointCommand(String command, String content){
        StringBuilder sb = new StringBuilder();
        sb.append(COMMAND_PREFIX);
        sb.append(command);
        if (content == null) {
            sb.append(Integer.toHexString(0));
        } else {
            sb.append(Integer.toHexString(content.length()));
            sb.append(content);
        }
        return  sb.toString();
    }

    /**
     * 解析命令  判断应答成功还是失败
     * @param command
     */
    public boolean analysisCommand(String command){

        switch (command.substring(COMMAND_PREFIX.length(),COMMAND_PREFIX.length()+4)){
            case VEHICLE_TO_APP_LOGIN_ACK: //车载设备连接成功
                return  true;
            case VEHICLE_TO_APP_LOGIN_NACK: // 车载设备连接失败
                return  true;
            case VEHICLE_TO_APP_BS_CFG_ACK:
                return  true;
        }
        return  true;
    }

}
