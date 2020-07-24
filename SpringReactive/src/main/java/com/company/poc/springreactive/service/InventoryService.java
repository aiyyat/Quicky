package com.company.poc.springreactive.service;

import com.company.poc.springreactive.domain.Medicine;
import com.company.poc.springreactive.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class InventoryService {
    @Autowired
    InventoryRepository inventoryRepo;

    public void addMedicines(List<Medicine> medicines) {
        Flux<Medicine> medicineFlux = Flux.fromIterable(medicines);
        inventoryRepo.saveAll(medicineFlux).subscribe();
    }

    public Flux<Medicine> getMedicines() {
        return inventoryRepo.findAll();
    }
}
