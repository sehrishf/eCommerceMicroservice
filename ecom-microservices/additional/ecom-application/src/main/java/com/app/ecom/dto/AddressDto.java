package com.app.ecom.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class AddressDto {


    private Long id;

    private String street;

    private String city;

    private String state;

    private String postalCode;

    private String country;

    private String zipCode;
}
