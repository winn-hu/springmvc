package com.blueStarWei.utils.tst;

import com.blueStarWei.utils.HttpClientUtil;
import com.blueStarWei.utils.JsonUtil;

import java.io.IOException;
import java.util.Map;

public class FluentPostTest {

    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8092/springmvc/hello/apiPost";
        String body = "{\"name\":\"Winn\",\"age\":22,\"id\":1}";
        String result = HttpClientUtil.fluentPost(url, body);
        Map map = JsonUtil.json2map(result);
        map.forEach((key, value) -> System.out.println(key + " : " + value));

    }
}
