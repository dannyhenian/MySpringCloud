package com.ssx.logging.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 日志输出基类
 *
 * @author danny
 * @create 2019-11-26 18:00
 */
@Data
public class LogBaseModel {
    /* 通用日志 */
    // 服务实例名
    @JSONField(ordinal=1)
    private String service;

    // 日志级别
    @JSONField(ordinal=2)
    private String level;

    // 操作时间
    @JSONField(ordinal=3)
    private String startTime;

    // 主机名
    @JSONField(ordinal=4)
    private String hostName;

    // 主机IP
    @JSONField(ordinal=5)
    private String hostIp;

    // 类名
    @JSONField(ordinal=6)
    private String className;

    // 方法名
    @JSONField(ordinal=7)
    private String methodName;

    // 耗时
    @JSONField(ordinal=8)
    private String cost;

    // 接口具体操作
    @JSONField(ordinal=9)
    private String apiOperation;

    // 返回码
    @JSONField(ordinal=10)
    private String levelCode;

    /* 业务日志 */
    // 系统代码
    @JSONField(ordinal=11)
    private String sys;

    // 业务代码
    @JSONField(ordinal=12)
    private String businessCode;

    // 操作码
    @JSONField(ordinal=13)
    private String activityCode;

    // 流水号
    @JSONField(ordinal=14)
    private String transID;

    // 消息
    @JSONField(ordinal=15)
    private String msg;

    // 请求体
    @JSONField(ordinal=16)
    private Object req;

    // 返回体
    @JSONField(ordinal=17)
    private Object resp;

    public LogBaseModel(){}

    public LogBaseModel(String sys,String businessCode,String activityCode,String transID){
        this.sys = sys;
        this.businessCode = businessCode;
        this.activityCode = activityCode;
        this.transID = transID;

    }

}
