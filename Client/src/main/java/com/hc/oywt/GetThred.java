package com.hc.oywt;

import com.hc.oywt.model.TimeMessage;
import com.hc.oywt.utils.HttpClientSupport;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.BlockingQueue;

public class GetThred implements Runnable {

    private CloseableHttpClient client ;

    private HttpGet httpGet;

    private BlockingQueue<String> queue;

    private final String EXIT = "EXIT";

    public GetThred(CloseableHttpClient client, HttpGet httpGet, BlockingQueue queue) {
        this.client = client;
        this.httpGet = httpGet;
        this.queue = queue;
    }


    public void run() {

        CloseableHttpResponse response = null;

        try {
            for (int i = 0; i < 100; i++) {
                response = client.execute(httpGet);

                InputStream inputStream = response.getEntity().getContent();

                String s = HttpClientSupport.get(inputStream);

                queue.put(s);

                System.out.println("生产值是"+s+"====生产者线程id"+Thread.currentThread().getId());

            }

            //此次任务结束，放入退出标识
            queue.put(EXIT);

        } catch (IOException e) {
                e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            if (response!=null) {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
