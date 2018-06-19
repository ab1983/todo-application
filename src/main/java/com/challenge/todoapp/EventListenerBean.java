package com.challenge.todoapp;

import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.challenge.todoapp.service.AccessService;

@Component
@Profile("!test")
public class EventListenerBean {
 
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
    	event.getApplicationContext().getBean(AccessService.class).populateDatabase();
    }
}