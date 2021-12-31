package com.blueStarWei.utils.tst;

import com.blueStarWei.utils.HttpClientUtil;


public class HttpTst {

    public static void main(String[] args) {
        String result =HttpClientUtil.post4randomIP("https://www.cnblogs.com/BlueStarWei/p/14553758.html", null);
        System.out.println(result);
    }
}
