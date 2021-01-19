package com.jdyx.es.listener.even;

import org.springframework.context.ApplicationEvent;

public class TestEvent extends ApplicationEvent {

    private String message;

    public TestEvent(Object source) {
        super(source);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}