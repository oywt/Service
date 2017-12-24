package com.hc.oywt.utils;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
}
