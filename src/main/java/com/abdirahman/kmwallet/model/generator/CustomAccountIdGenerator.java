package com.abdirahman.kmwallet.model.generator;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class CustomAccountIdGenerator implements IdentifierGenerator {
    private static final String PREFIX = "KMACCOUNT_";

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        // Your custom logic to generate the ID with the preset prefix
        // In this example, using a simple concatenation
        return PREFIX + generateRandomId();
    }

    // Your custom logic to generate a random or unique ID
    private String generateRandomId() {
        // Implement your own logic to generate the ID
        // This can be a random number, UUID, or any other method you prefer
        // Example: return UUID.randomUUID().toString();
        return String.valueOf(System.currentTimeMillis());
    }
}
