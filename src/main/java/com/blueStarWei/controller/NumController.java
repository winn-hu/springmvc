package com.blueStarWei.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller、Service默认都是单例模式（好处：极大地节省内存资源，提高服务抗压能力）
 *
 * 单例模式容易出现的问题是：
 *     Controller中的实例变量不是线程安全的
 *     【刷新http://localhost:8092/springmvc/num/add，返回的数字会依次增加】
 *解决办法：
 *   尽量不要在 Controller 中定义成员变量；
 *   如果必须要定义一个非静态成员变量，那么可以通过注解 @Scope(“prototype”)，将Controller设置为多例模式。
 *   Controller 中使用 ThreadLocal 变量
 * 【刷新http://localhost:8092/springmvc/num/add，返回的数字会依次增加】
 *
 */
@RestController
@RequestMapping("/num")
public class NumController {
    private ThreadLocal<Integer> uniqueNum = ThreadLocal.withInitial(() -> 0);

    @RequestMapping("/add")
    public String addNum() {
        Integer num = uniqueNum.get();
        uniqueNum.set(++num);
        return Thread.currentThread().getName()+" "+num;
    }
}
