package com.example.demo.ws.service;

import javax.jws.WebService;
import java.util.Date;


@WebService
public interface HelloWorld {

    String sayHi(String text);

    Date currentDate();

}
