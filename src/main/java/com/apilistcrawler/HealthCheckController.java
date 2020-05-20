package com.apilistcrawler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("")
@ResponseBody
public class HealthCheckController {

    @GetMapping("/ping")
    public String hello(){
        return new String("pong");
    }


}
