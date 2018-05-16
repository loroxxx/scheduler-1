package com.jinhui.scheduler.biz.imiqian.utils;

/**
 * Created by luozl on 2017/3/9.
 */
public class NumberUtils {
    /**
     *  判断字符串里是否包含字符
     * @param str
     * @return
     */
    public static boolean isIncludeChar(String str) {
        if(EmptyUtils.isEmpty(str)) {
          return false;
        }
        char [] charArray = str.toCharArray();
        for(int i=0;i<charArray.length;i++) {
            char c = charArray[i];
            if('0'!=c&&'1'!=c&&'2'!=c&&'3'!=c&&'4'!=c&&'5'!=c&&'6'!=c&&'7'!=c&&'8'!=c&&'9'!=c) {
                return true;
            }
        }
        return false;
    }
}
