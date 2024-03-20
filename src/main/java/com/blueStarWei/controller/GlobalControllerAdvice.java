package com.blueStarWei.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice
public class GlobalControllerAdvice {

    /**
     * 参数预处理
     *
     * @param binder
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //将前端字符串日期转换为后端Date格式
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
        //去除字符串首尾的空格
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }

}
