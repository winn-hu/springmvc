package com.blueStarWei.task;

import com.blueStarWei.utils.HttpClientUtil;
import com.blueStarWei.utils.MyBatisLogUtil;
import org.apache.ibatis.logging.Log;

public class CustomTask {

    private static final Log LOG = MyBatisLogUtil.getLog(CustomTask.class);
    int i = 0;

    public void log() {
        //String result = HttpClientUtil.post("https://www.cnblogs.com/BlueStarWei/p/14553758.html", null);
        //LOG.debug("访问第"+(++i)+"次."+result);
    }
}
