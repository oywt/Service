package com.hc.oywt.service.web.controller;

import com.hc.oywt.service.web.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/homepage")
public class HomeController {

    private int i = 0;

    @RequestMapping("/hot")
    public String handleHome(){
        return "Hot";
    }

    @RequestMapping(method = RequestMethod.GET,value = "/getJson",produces = "application/json")
    public @ResponseBody
    List<User> getJson(){
        List<User> users = new ArrayList<User>(2);

        users.add(new User(++i,"test"+i));

        System.out.println(i+"线程id"+Thread.currentThread().getId());
       // users.add(new User(18,"test2"));

        return users;
    }
}
