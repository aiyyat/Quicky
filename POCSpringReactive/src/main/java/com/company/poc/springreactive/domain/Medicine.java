package com.company.poc.springreactive.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
@Document
@AllArgsConstructor
public class Medicine {
    @Id
    String name;
    String manufacturer;
    String quantity;
    BigDecimal unitPrice;
    List<Ingredient> ingredients;
}
