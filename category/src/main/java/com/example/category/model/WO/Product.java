package com.example.category.model.WO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Product {
    private Long id;

    private String name;

    private BigDecimal price;

    private Long discount;

    private List<Long> categoryIds;
}
