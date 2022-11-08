package com.util;

import java.net.URL;

/**
 * <检查工具类><br>
 * 主要用于一些信息的检查,比如非空验证
 */
public class ValidationUtil {

    /**
     * 是否是一个空字符串
     *
     * @param string 待检查字符串
     * @return 若为true则表示是一个空字符串
     */
    public static boolean isNullString(String string) {
        return null == string || string.trim().length() == 0;
    }


    /**
     * 检查字符串是否为 url
     *
     * @param url 待检查字符串
     * @return 如果为真，则表示字符串是 url
     */
    public static boolean isValidUrl(String url)
    {
        try {
            new URL(url).toURI();
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }


    /**
     * 是否是一个对象
     *
     * @param object 待检查对象
     * @return 若为true则表示是一个空对象
     */
    public static boolean isNullObject(Object object) {
        try {
            return null == object || object.equals("");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /**
     * 判断是否为空
     *
     * @param object
     * @return
     */
    public static boolean isNotBlank(Object object) {
        try {
            if (object == null) {
                return false;
            } else if ("null".equals(object)
                    || "null".equals(String.valueOf(object).trim())) {
                return false;
            } else {
                String inputStr = String.valueOf(object);
                inputStr = inputStr.replaceAll(" ", "");
                if ("".equals(inputStr)) {
                    return false;
                } else {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static String removeNullString(Object input) {
        String inputStr = "";

        // inputStr=(String)input;

        if (input == null)
            inputStr = "";
        else if (input.equals("null"))
            inputStr = "";
        else {
            inputStr = (String) input;
            inputStr = inputStr.replaceAll(" ", "");
        }
        return inputStr;
    }

    public static String specialChar(Object input) {
        String inputStr = "";
        inputStr = (String) input;

        if (input == null)
            return inputStr;
        else if (input.equals("null"))
            inputStr = null;
        else {
            inputStr = (String) input;
            inputStr = inputStr.replaceAll(" ", "").replaceAll("\'", "");
            if (inputStr.equals("")) {
                inputStr = null;
            }
        }
        return inputStr;

    }

    public static String findForward2(String defStr, String str1, String str2) {
        String val = "";
        if (str2 != null && !str2.equals("") && !str2.equals("null")) {
            val = str2.replaceAll(":", "&");
        } else if (str1 != null && !str1.equals("") && !str1.equals("null")) {
            val = str1.replaceAll(":", "&");
        } else {
            val = defStr.replaceAll(":", "&");
        }
        System.out.println("returnPage2==========" + val);
        return val;
    }

    public static String findForward(String defStr, String str1, String str2) {
        String val = "";
        if (str2 != null && !str2.equals("") && !str2.equals("null")) {
            val = str2;
        } else if (str1 != null && !str1.equals("") && !str1.equals("null")) {
            val = str1;
        } else {
            val = defStr;
        }
        System.out.println("returnPage==========" + val);
        return val;
    }

    public static String formatDate(Object input) {
        String inputStr = "";
        inputStr = (String) input;
        if (inputStr != null && !inputStr.equals("")
                && !inputStr.equals("null") && inputStr.length() > 10) {
            inputStr = inputStr.substring(0, 10);
        } else {
            inputStr = "";
        }
        return inputStr;

    }

    public static int dealNullInt(Object obj, int i) {
        int values = 0;
        try {
            if (obj != null) {
                String tmp = obj.toString();
                values = Integer.parseInt(tmp);
            } else {
                values = i;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //Log.logger.error("System ex",ex);
            values = i;
        }
        return values;
    }

    public static long converLong(String obj, int i) {
        long values = 0;
        try {

            values = Long.parseLong(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
            // Log.logger.error("System ex",ex);
            values = i;
        }
        return values;
    }

    public static long dealNullLong(Object obj, int i) {
        long values = 0;
        try {
            values = (Long) obj;
        } catch (Exception ex) {
            // Log.error("ValidationUtil.dealNullLong the null" + ex.getMessage());

            values = i;
        }
        return values;
    }

    /**
     * 格式化字符串数据位金额型
     *
     * @param doubleStr
     * @return
     */
    public static String cutDouble2(String doubleStr) {
        String result = "0.00";
        try {
            double d = Double.parseDouble(doubleStr);
            result = new java.text.DecimalFormat("#.00").format(d);
        } catch (Exception ex) {
            System.out.println("ValidationUtili.cutDouble2 error-->"
                    + ex.getMessage());
            // ex.printStackTrace();
        }
        return result;
    }

    /**
     * 格式化Double型 为金额型
     *
     * @param doubleValue
     * @return
     */
    public static String cutDouble2(Double doubleValue) {
        String result = "0.00";
        try {
            double d = dealNullDouble(doubleValue, 0.0);
            result = new java.text.DecimalFormat("#.00").format(d);
        } catch (Exception ex) {
            System.out.println("ValidationUtili.cutDouble2 error-->"
                    + ex.getMessage());
            // ex.printStackTrace();
        }
        return result;
    }

    /**
     * 检验是否为数字
     *
     * @param numz
     * @return
     */
    public static boolean numMatch(String numz) {
        boolean checkBool = false;
        if (numz == null)
            numz = "0";
        checkBool = numz.matches("[0-9]+");
        return checkBool;
    }

    /**
     * 检查是否是电话号码
     *
     * @param phoneNumber
     * @return boolean
     */
    public static boolean phoneNumberMatch(String phoneNumber) {
        boolean checkBool = false;
        if (phoneNumber == null)
            phoneNumber = "0";
        checkBool = phoneNumber.matches("[0-9+]+");
        return checkBool;
    }

    public static long coverLong(String strLong, int i) {
        long values = 0;
        try {
            if (strLong != null) {
                values = Long.parseLong(strLong);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            //  Log.logger.error("System ex",ex);
            values = i;
        }
        return values;
    }

    public static double coverDouble(String strLong, double i) {
        double values = 0;
        try {
            if (strLong != null) {
                values = Double.parseDouble(strLong);
            }
        } catch (Exception ex) {
            //Log.error("coverDouble:" + ex.getMessage());
            ;
            values = i;
        }
        return values;
    }

    public static int dealNullInt(String str, int i) {
        int values = 0;
        try {
            values = Integer.parseInt(str);
        } catch (Exception ex) {
            ex.printStackTrace();
            //Log.logger.error("System ex",ex);
            values = i;
        }
        return values;
    }

    public static int dealNullInt(Object obj) {
        int values = 0;
        try {
            values = (Integer) obj;
        } catch (Exception ex) {
            System.out.println("DealNullInt:" + ex.getMessage());
            values = 0;
        }
        return values;
    }

    public static String errorInfoFilter(String errorInfo) {
        if (errorInfo == null)
            errorInfo = "";

        errorInfo = errorInfo.replaceAll("\"", "?");
        errorInfo = errorInfo.replace("\n", "");
        return errorInfo;
    }

    public static int coverInt(String str) {
        int value = 0;
        try {
            value = Integer.parseInt(str);
        } catch (Exception ex) {
            value = 0;
            System.out.println("coverInt failure!!" + str);
        }
        return value;
    }

    public static int coverInt(String str, int i) {
        int value = 0;
        try {
            value = Integer.parseInt(str);
        } catch (Exception ex) {
            value = i;
            System.out.println("coverInt convert failure!!" + str);
        }
        return value;
    }

    public static String cutStr(String content, int lenght) {
        if (content == null) {
            content = "";
        }
        if (content.length() > lenght) {
            content = content.substring(0, lenght);
        }
        return content;
    }

    public static String getNameWithoutExtension(String fileName) {
        return fileName.substring(0, fileName.lastIndexOf("."));
    }

    /*
     * This method was fixed after Kris Barnhoorn (kurioskronic) submitted SF
     * bug #991489
     */
    public static String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public static long dealNullLong(Long obj, long dValue) {
        long values = 0;
        try {
            values = obj;
        } catch (Exception ex) {
            System.out.println("DealNullLong:" + ex.getMessage());
            values = dValue;
        }
        return values;
    }

    public static Double dealNullDouble(Object obj, double dValue) {
        double values = 0;
        try {
            values = (Double) obj;
        } catch (Exception ex) {
            System.out.println("dealNullDouble:" + ex.getMessage());
            values = dValue;
        }
        return values;
    }


}
