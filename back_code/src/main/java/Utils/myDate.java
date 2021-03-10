package Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 设置日期格式
 */
public class myDate {
    public static String getDate(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }
}
