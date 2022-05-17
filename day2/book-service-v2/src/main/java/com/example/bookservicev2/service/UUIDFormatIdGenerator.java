package com.example.bookservicev2.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * [REVIEW POINT] What is @{@link Profile}?
 */

@Service
@Profile("uuid")
public class UUIDFormatIdGenerator implements IdGenerator{
    @Override
    public String getNext() {
        return UUID.randomUUID().toString();
    }
}
