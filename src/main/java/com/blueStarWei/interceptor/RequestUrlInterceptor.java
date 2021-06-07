package com.blueStarWei.interceptor;

import com.blueStarWei.utils.MyBatisLogUtil;
import org.apache.ibatis.logging.Log;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestUrlInterceptor implements HandlerInterceptor {

    private static final Log LOG = MyBatisLogUtil.getLog(RequestUrlInterceptor.class);

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        LOG.debug("RequestUrlInterceptor ==>  You can do auth control there.");
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
