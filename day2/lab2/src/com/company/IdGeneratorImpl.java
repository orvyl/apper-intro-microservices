package com.company;

import java.util.UUID;

public class IdGeneratorImpl implements IdGenerator {
    public String getNext() {
        return UUID.randomUUID().toString();
    }
}
