package com.marcos.imobflow.infrastructure.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.mysql.MySQLContainer;

@DataJpaTest
@Testcontainers
class MySqlImovelRepositoryIntegrationTest {

    @Container
    @ServiceConnection
    static MySQLContainer mysql =
            new MySQLContainer("mysql:8.0");

    @Autowired
    private JpaImovelRepository jpaImovelRepository;

    @Test
    void deveSalvarImovelNoBanco() {

        // Arrange

        // Act

        // Assert
    }
}