package com.salton123.xmly.util;

import java.text.DecimalFormat;

/**
 * User: newSalton@outlook.com
 * Date: 2019/1/3 9:23 PM
 * ModifyTime: 9:23 PM
 * Description:
 */

public class SizeUtils {

    /** gb to byte **/
    public static final long GB_2_BYTE = 1073741824;
    /** mb to byte **/
    public static final long MB_2_BYTE = 1048576;
    /** kb to byte **/
    public static final long KB_2_BYTE = 1024;

    private SizeUtils() {
        throw new AssertionError();
    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return B/KB/MB/GB
     */
    public static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }


}
