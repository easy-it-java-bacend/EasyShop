package com.example.order.model.WO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {
    Long id;
    String firstName;
    String lastName;
    String phoneNumber;
    String email;
}
