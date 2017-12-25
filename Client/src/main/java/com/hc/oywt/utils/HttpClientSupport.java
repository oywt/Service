package com.hc.oywt.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hc.oywt.model.TimeMessage;
import com.hc.oywt.model.User;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

public class HttpClientSupport {

    private static CloseableHttpClient httpClient = null;

    static {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();

        poolingHttpClientConnectionManager.setMaxTotal(10000);

        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(5000);

        // poolingHttpClientConnectionManager.setMaxPerRoute(4000);


        httpClient = HttpClients.custom().setConnectionManager(poolingHttpClientConnectionManager).build();

    }

    public static CloseableHttpClient getHttpClient(){
        return httpClient;
    }
    public static String get(InputStream inputStream){
        StringBuffer stringBuffer = new StringBuffer("");
        try {


            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream)
            );

            String line = null;

            while ((line=reader.readLine())!=null){

                stringBuffer.append(line);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }

    /**
     * 字符串转为User对象
     * @param str json字符串
     * @return User对象
     */
    public static User StrConverUser(String str){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            User user = objectMapper.readValue(str,User.class);

            return user;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static TimeMessage StrConverTimeMessage(String str){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            TimeMessage timeMessage = objectMapper.readValue(str,TimeMessage.class);

            return timeMessage;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static HttpGet getHttpGet(String path){

        HttpGet get = null;
        try {
            URI uri = new URIBuilder()
                    .setScheme("http")
                    .setHost("localhost")
                    .setPath(":8080/Service/homepage/"+path)
                    .build();
            get = new HttpGet(uri);
            System.out.println("请求url===="+get.getURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        return get;
    }
}
