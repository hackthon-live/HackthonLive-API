package com.iydsj.sw.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yanyan.wang
 * @date 2015-11-22 22:51
 */
public class VerifyUtils {

    public static boolean isMobileNum(String phone) {
        Pattern p = Pattern.compile("^[1][3,4,5,8][0-9]{9}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

}
