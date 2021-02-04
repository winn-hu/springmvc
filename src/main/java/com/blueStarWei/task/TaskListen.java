package com.blueStarWei.task;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.Timer;

public class TaskListen  implements ServletContextListener {

    Timer timer = null;

    public void contextInitialized(ServletContextEvent sce) {
        timer = new Timer(true);
        timer.schedule(new MyTask(), 0, 1000);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        timer.cancel();
    }

}
