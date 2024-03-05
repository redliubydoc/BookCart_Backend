package com.raj.ms.identity.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test")
public class TestController {

  @RequestMapping(method = RequestMethod.GET)
  public String test() {
    return "hello world";
  }

}
