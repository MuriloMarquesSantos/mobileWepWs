package com.appsdeveloperblog.app.ws.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.appsdeveloperblog.app.ws.io.repository"})
@EnableJpaAuditing
@EntityScan(basePackages = {"com.appsdeveloperblog.app.ws.io.entity"})
public class PersistenceConfiguration {
}
