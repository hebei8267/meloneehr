/**
 * 2008/03/18
 * 
 * @author 何 貝
 */
package cn.hb.core.util;

public class StringUtil {
    /**
     * 字符串是否为空
     * 
     * @param str 判断字符串
     * @return 判断结果
     */
    public static boolean isEmpty(String str) {
        if (str != null && !str.equals("")) {
            return false;
        }
        return true;
    }

    /**
     * 取得文件名称
     * 
     * @param filePath 文件全路径
     * @return 文件名称
     */
    public static String getFileName(String filePath) {
        if (!isEmpty(filePath)) {
            return filePath.substring(filePath.lastIndexOf('\\') + 1);
        }
        return "";
    }

}
