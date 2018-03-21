package com.example.demo.ws.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jws.WebService;
import java.util.Date;

@WebService(endpointInterface = "com.example.demo.ws.service.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public String sayHi(String text) {
        logger.info("sayHi called");
        return "Hello " + text;
    }

    @Override
    public Date currentDate() {
        logger.info("currentDate called");
        return new Date();
    }

}
