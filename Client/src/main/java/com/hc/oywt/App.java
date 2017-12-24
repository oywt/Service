package com.hc.oywt;

import com.hc.oywt.consumer.ConsumerGetThred;
import com.hc.oywt.utils.HttpClientSupport;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException, ExecutionException, InterruptedException {
        CloseableHttpClient httpClient = HttpClientSupport.getHttpClient();

        ExecutorService executorService = Executors.newCachedThreadPool();

        HttpGet httpGet = new HttpGet("http://localhost:8080/Service/homepage/getJson.json");

        BlockingQueue<String> queue = new LinkedBlockingQueue<String>();//队列长度应根据业务来定


        for (int i = 0; i < 6; i++) {
            executorService.execute((new GetThred(httpClient, httpGet, queue)));

        }

        for (int i = 0; i < 6; i++) {
            executorService.execute(new ConsumerGetThred(queue));

        }

    }


}
