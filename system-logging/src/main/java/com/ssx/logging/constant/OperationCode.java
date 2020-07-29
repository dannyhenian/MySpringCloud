package com.ssx.logging.constant;

/**
 * 操作码
 *
 * @author danny
 * @create 2019-11-28 11:17
 */

public interface OperationCode {
    public final static String Recharge_Query_Code = "01001";  // 充值记录查询
    public final static String Consume_Query_Code = "01002";  // 消费记录查询
    public final static String UserMSG_Query_Code = "01003";  // 个人信息查询
    public final static String Coupon_Query_Code = "01004";  // 优惠券查询

    public final static String WX_Recharge_Code = "02001";  // 微信充值
}
