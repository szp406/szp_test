package com.jdyx.es.controller;

import com.jdyx.es.listener.even.TestEvent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(tags="springboot listener 测试")
@RestController
@RequestMapping("/listener")
public class EvenTestController {
    @Autowired
    private ApplicationContext applicationContext;

    @ApiOperation(value = "发布事件", notes = "发布事件")
    @RequestMapping(value = "/publisherEven", method = { RequestMethod.GET })
    public String publisherEven(String msg) throws Exception {
        TestEvent testEvent = new TestEvent("");
        testEvent.setMessage(msg);
        applicationContext.publishEvent(testEvent);
        return "success";
    }
}
