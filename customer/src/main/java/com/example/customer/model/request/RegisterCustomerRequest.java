package com.example.customer.model.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterCustomerRequest {
    @NotBlank
    String firstName;
    @NotBlank
    String lastName;
    @NotBlank
    String phoneNumber;
    String email;
}
