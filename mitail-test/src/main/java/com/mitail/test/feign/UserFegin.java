package com.mitail.test.feign;


import com.mitail.comment.NotBreakerConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "mitail-user", configuration = {NotBreakerConfiguration.class})
public interface UserFegin {

    @GetMapping("/hi")
    public String hi(@RequestParam(value = "name",defaultValue = "forezp",required = false)String name);
}
