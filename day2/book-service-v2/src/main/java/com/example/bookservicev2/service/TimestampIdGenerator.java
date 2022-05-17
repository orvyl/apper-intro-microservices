package com.example.bookservicev2.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@Profile("timestamp")
public class TimestampIdGenerator implements IdGenerator {
    @Override
    public String getNext() {
        return ""+LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli();
    }
}
