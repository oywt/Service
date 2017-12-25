package com.hc.oywt.consumer;

import com.hc.oywt.model.TimeMessage;
import com.hc.oywt.model.User;
import com.hc.oywt.utils.HttpClientSupport;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.BlockingQueue;

public class ConsumerGetThred implements Runnable {

    private CloseableHttpClient client ;

    public ConsumerGetThred(CloseableHttpClient client, BlockingQueue<String> queue) {
        this.client = client;
        this.queue = queue;
    }

    private BlockingQueue<String> queue ;

    private boolean exitFlag =true;


    public void run() {
        while (exitFlag){
            try {
                String take = queue.take();

                if("EXIT".equals(take) && queue.isEmpty()){
                    //消费的是退出标识，并且Queue为空时，结束消费者，并将退出标识再次放入Queue中
                    exitFlag = false;

                    queue.put(take);
                }

                if(!"EXIT".equals(take)){
                    System.out.println("====消费者线程id"+Thread.currentThread().getId());

                    User user = HttpClientSupport.StrConverUser(take);

                    System.out.println("消费者消耗的值是=="+take+"我的id=="+user.getId());

                    TimeMessage jsonById = getJsonById(user.getId());

                    System.out.println(jsonById);

                }



            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

    public TimeMessage getJsonById(int id){
        HttpGet httpGet = HttpClientSupport.getHttpGet(String.valueOf(id));

        try {
            CloseableHttpResponse execute = client.execute(httpGet);

            InputStream inputStream = execute.getEntity().getContent();

            String json = HttpClientSupport.get(inputStream);

            TimeMessage timeMessage = HttpClientSupport.StrConverTimeMessage(json);

            return timeMessage;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
