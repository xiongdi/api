package com.huiqia.api.web;

import com.huiqia.api.exception.MyException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String index() {
        return "hello, world!";
    }

    @RequestMapping("/world")
    public String hello()throws Exception {
        throw new Exception("aaa");
    }

    @RequestMapping("/json")
    public String json() throws MyException {
        throw new MyException("error");
    }

}
