package com.company.poc.springreactive.controller;

import com.company.poc.springreactive.config.InventoryConfiguration;
import com.company.poc.springreactive.domain.Medicine;
import com.company.poc.springreactive.service.InventoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@Api
public class InventoryController {
    @Autowired
    InventoryService inventoryService;

    @Autowired
    InventoryConfiguration configuration;

    @RequestMapping(value = "/medicine", method = RequestMethod.GET)
    public Flux<Medicine> getMedicines() {
        return inventoryService.getMedicines();
    }

    @RequestMapping(value = "/medicine", method = RequestMethod.POST)
    public void addMedicines(@ApiParam(name = "medicines", required = true) @RequestBody List<Medicine> medicines) {
        inventoryService.addMedicines(medicines);
    }
}
