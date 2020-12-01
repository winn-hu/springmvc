package com.blueStarWei.utils;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;

public class MyBatisLogUtil {

    static {
        //指定使用STDOUT_LOGGING日志组件
        LogFactory.useStdOutLogging();
    }

    /**
     * 获取MyBatis的Log实例
     * @param clazz 实用类的class
     * @return MyBatis的Log实例
     */
    public static Log getLog(Class<?> clazz) {
        return LogFactory.getLog(clazz);
    }

}
