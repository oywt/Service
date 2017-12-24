package com.hc.oywt.consumer;

import java.util.concurrent.BlockingQueue;

public class ConsumerGetThred implements Runnable {
    private BlockingQueue queue ;

    private boolean temp =true;
    public ConsumerGetThred(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        while (true&&temp){
            try {
                System.out.println("消费者消耗的值是=="+queue.take()+"====消费者线程id"+Thread.currentThread().getId()+"队列长度"+queue.size());
                if(queue.size()<=0){
                    Thread.sleep(1000);
                }
//                if(queue.size()<=0){
//                  temp=false;
//                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }
}
