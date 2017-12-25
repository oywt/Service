package com.hc.oywt.service.web.controller;

import com.hc.oywt.service.model.TimeMessage;
import com.hc.oywt.service.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

@Controller
@RequestMapping("/homepage")
public class HomeController {

    private final ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(100000);
    private int i = 0;

    @RequestMapping("/hot")
    public String handleHome(){
        return "Hot";
    }

    @RequestMapping(method = RequestMethod.GET,value = "/getJson",produces = "application/json")
    public @ResponseBody
    User getJson(){

        System.out.println(i+"线程id"+Thread.currentThread().getId());

        concurrentHashMap.put(i,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        return new User(i++,"test"+i);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/{id}",produces = "application/json")
    public @ResponseBody
    TimeMessage accessTime(@PathVariable("id") int id ){

        System.out.println("id 是"+id);

        Object o = concurrentHashMap.get(id);

        System.out.println("time"+o.toString());

        return  new TimeMessage(id,String.valueOf(o));
    }

}
