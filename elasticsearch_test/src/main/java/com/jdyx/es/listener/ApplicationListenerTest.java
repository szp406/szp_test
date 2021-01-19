package com.jdyx.es.listener;

import com.jdyx.es.listener.even.TestEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationListenerTest implements ApplicationListener<TestEvent> {
    @Override
    public void onApplicationEvent(TestEvent testEvent) {
        System.out.println("事件内容 = " +  testEvent.getMessage());
    }
}