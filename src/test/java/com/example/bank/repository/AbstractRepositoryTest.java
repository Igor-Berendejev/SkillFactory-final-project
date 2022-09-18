package com.example.bank.repository;

import org.testcontainers.containers.PostgreSQLContainer;

public abstract class AbstractRepositoryTest {
    static PostgreSQLContainer container = new PostgreSQLContainer("postgres:latest")
            .withDatabaseName("bank")
            .withUsername("postgres")
            .withPassword("postgres");

    static {
        container.start();
    }
}
