package com.mitail.test.controller;

import com.mitail.test.feign.UserFegin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeginTestController {

    @Autowired
    private UserFegin userFegin;

    @GetMapping("/hi")
    public String hi(@RequestParam(value = "name",defaultValue = "forezp",required = false)String name){
        return userFegin.hi(name);
    }

}
