package com.example.product.model.WO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import java.util.List;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Category {
    Long id;
    String name;
    List<Long> productIds;
}
