package com.util;

import org.springframework.http.HttpStatus;

public class Constants {
    public static final String ENCODE = "UTF-8";
    /**
     * 时间格式字符串 yyyy-MM-dd
     */
    public static final String DATE_YYYY_MM = "yyyy-MM";
    public static final String DATE_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATE_YYYY_MM_DD_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    //jwt 常量
    public static final int jwtExpirationHrs = 10;
    //1-成功  2-失败
    public final static String RETURN_CODE = "RETURN_CODE";
    public final static String RETURN_MSG = "RETURN_MSG";
    public final static String RETURN_DATA = "RETURN_DATA";

    //用户角色
    public static final String ADMIN_USER = "ROLE_ADMIN_USER";
    public static final String MANAGER_USER = "ROLE_MANAGER_USER";

    public static final String DEVELOPER_USER = "ROLE_DEVELOPER_USER";

    public static final String CLIENT_USER = "ROLE_CLIENT_USER";

    //jwt秘密
    public static final String jwtSecret = "LazEX6t1KGdhEIeD7qxUr/zOD5+yWcA+ZKb3icrvAaFCagZN1fjjZR/30AWq1/OGTn9/AlTi7wNgkLYmDpteZA==";
    //在店铺付款
    public static final int PUSH_TYPE_ON_SHOP_PAYMENT=1;
    //账号审核
    public static final int PUSH_TYPE_ACCOUNT_VERIFICATION=2;
    //加好友请求
    public static final int PUSH_TYPE_FRIEND_REQUEST=3;
    //群好友
    public static final int PUSH_TYPE_GROUP_REQUEST=4;
    //一对一红包
    public static final int PUSH_TYPE_ONE_TO_ONE_GIFT=5;
    //一对多红包
    public static final int PUSH_TYPE_ONE_TO_MANY_GIFT=6;
    //AA制
    public static final int PUSH_TYPE_SPLIT_BILL=7;
    //转账
    public static final int PUSH_TYPE_TRANSFER=8;
    //扫码收款
    public static final int PUSH_TYPE_SCAN_TRANSFER=9;
    //系统通知
    public static final int PUSH_TYPE_SYSTEM_NOTIFICATION=10;
    //金额提现
    public static final int PUSH_TYPE_WITHDRAW_PROCESS=11;
    //充值金额
    public static final int PUSH_TYPE_RECHARGE_PROCESS=12;
    public static final int PUSH_TYPE_ON_API_PAYMENT=13;
}