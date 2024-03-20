package com.blueStarWei.task;

import com.blueStarWei.utils.MyBatisLogUtil;
import org.apache.ibatis.logging.Log;

import java.time.LocalTime;
import java.util.TimerTask;

public class MyTask extends TimerTask {

    private static final Log LOG = MyBatisLogUtil.getLog(CustomTask.class);

    @Override
    public void run() {
        //LOG.debug("MyTask ==> "+ LocalTime.now());
    }
}
