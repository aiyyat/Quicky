package com.company.poc.springreactive;

import com.company.poc.springreactive.domain.Medicine;
import com.company.poc.springreactive.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@SpringBootApplication
public class PocSpringReactiveApplication {
    public static void main(String[] args) {
        SpringApplication.run(PocSpringReactiveApplication.class, args);
    }
}

