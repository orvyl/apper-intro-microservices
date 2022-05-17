package com.example.bookservicev2.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("timestamp")
public class TimestampIdGenerator implements IdGenerator {
    @Override
    public String getNext() {
        return null;
    }
}
