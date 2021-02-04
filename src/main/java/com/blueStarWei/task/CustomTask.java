package com.blueStarWei.task;

import com.blueStarWei.utils.MyBatisLogUtil;
import org.apache.ibatis.logging.Log;

import java.time.LocalTime;

public class CustomTask {

    private static final Log LOG = MyBatisLogUtil.getLog(CustomTask.class);

    public void log() {
        LOG.debug("CustomTask ==> "+ LocalTime.now());
    }
}
