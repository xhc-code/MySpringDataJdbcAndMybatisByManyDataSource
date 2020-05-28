package org.example.utils;

import java.util.UUID;

public class DataUtil {

    /**
     * 获取UUID值，用于保存记录
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
