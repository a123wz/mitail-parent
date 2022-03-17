package com.mitail.test.controller;

import com.mitail.test.feign.UserFegin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FeginTestController {

    @Autowired
    private UserFegin userFegin;

    @GetMapping("/hi")
    public String hi(@RequestParam(value = "name",defaultValue = "forezp",required = false)String name){
        return userFegin.hi(name);
    }

    @GetMapping("/hi1")
    public String hi1(String name){
        return "111111111";
    }

    @GetMapping("/hi111")
    public String hi111(@RequestParam(value = "name",defaultValue = "forezp",required = false)String name){
        return "111111111";
    }

    @GetMapping("/hi11")
    public String hi11(@RequestParam(value = "name",defaultValue = "forezp",required = false)String name){
        return "111111111";
    }

    @GetMapping("/hi2")
    public List<String> hi2(@RequestParam(value = "name",defaultValue = "forezp",required = false)String name){
        List<String> list = new ArrayList<>();
        for(int i=0;i<10;i++){
            list.add(i+"");
        }
        return list;
    }

}
