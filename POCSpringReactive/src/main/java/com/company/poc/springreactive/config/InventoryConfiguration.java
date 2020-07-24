package com.company.poc.springreactive.config;

import com.company.poc.springreactive.repository.InventoryRepository;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@ConfigurationProperties
@Data
@EnableReactiveMongoRepositories(basePackageClasses= InventoryRepository.class)
public class InventoryConfiguration {
}
