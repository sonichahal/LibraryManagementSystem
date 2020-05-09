package com.dz.student.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo {

    @RequestMapping(method = RequestMethod.GET, value = "/helloWorld")
    public String helloWorld() {
        return "Hello Student World";
    }
}
