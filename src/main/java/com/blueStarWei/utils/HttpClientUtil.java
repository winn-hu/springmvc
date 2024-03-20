package com.blueStarWei.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Random;

public class HttpClientUtil {

    public static String post(String url, Map<String, Object> parameters) {
        String result = "";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            if (parameters != null) {
                StringEntity requestEntity = new StringEntity(JsonUtil.map2json(parameters), ContentType.APPLICATION_JSON);
                requestEntity.setContentType("application/json;charset=UTF-8");
                post.setEntity(requestEntity);
            }
            response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity responseEntity = response.getEntity();
                result = EntityUtils.toString(responseEntity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    System.out.println("Response close IOException!" + e);
                }
            }
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    System.out.println("HttpClient close IOException!" + e);
                }
            }
        }
        return result;
    }

    public static String post4randomIP(String url, Map<String, Object> paramters) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            String ip = randIP();
            URLConnection conn = new URL(url).openConnection();
            //X-Forwarded-For : 表示 HTTP 请求端真实 IP
            conn.setRequestProperty("X-Forwarded-For", ip);
            //HTTP_X_FORWARDED_FOR: 浏览当前页面的用户计算机的网关.
            conn.setRequestProperty("HTTP_X_FORWARDED_FOR", ip);
            //HTTP_CLIENT_IP:客户端的ip.
            conn.setRequestProperty("HTTP_CLIENT_IP", ip);
            //REMOTE_ADDR:浏览当前页面的用户计算机的ip地址.
            conn.setRequestProperty("REMOTE_ADDR", ip);

            conn.setRequestProperty("Host", "");
            conn.setRequestProperty("Connection", "keep-alive");
            conn.setRequestProperty("Content-Length", "17");
            conn.setRequestProperty("Accept", "application/json");
            conn.setRequestProperty("Origin", "ORIGIN");
            conn.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Referer", "REFERER");
            conn.setRequestProperty("Accept-Encoding", "gzip, deflate");
            conn.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,ja;q=0.4,pt;q=0.2");

            conn.setDoOutput(true);
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());
            if (paramters != null) {
                out.print(map2RequestParams(paramters));
            }
            out.flush();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String randIP() {
        Random random = new Random(System.currentTimeMillis());
        return (random.nextInt(255) + 1) + "." +
                (random.nextInt(255) + 1) + "." +
                (random.nextInt(255) + 1) + "." +
                (random.nextInt(255) + 1);
    }

    public static String map2RequestParams(Map<String, Object> parameters) {
        StringBuilder params = new StringBuilder();
        parameters.forEach((key, value) -> params.append("&").append(key).append("=").append(value));
        return params.substring(1);
    }


    /**
     * <dependency>
     * <groupId>org.apache.httpcomponents</groupId>
     * <artifactId>httpclient</artifactId>
     * <version>4.5.1</version>
     * </dependency>
     * <dependency>
     * <groupId>org.apache.httpcomponents</groupId>
     * <artifactId>fluent-hc</artifactId>
     * <version>4.5.1</version>
     * </dependency>
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String fluentPost(String url, String params) throws IOException {
        String result = "";
        Request request = Request.Post(url);
        request.bodyString(params, ContentType.APPLICATION_JSON);
        request.setHeader("User-Agent", "Apipost client Runtime/+https://www.apipost.cn/");
        request.setHeader("Content-Type", "application/json");
        HttpResponse httpResponse = request.execute().returnResponse();
        System.out.println(httpResponse.getStatusLine());
        if (httpResponse.getEntity() != null) {
            result = EntityUtils.toString(httpResponse.getEntity());
        }
        return result;
    }
}
