package com.nicoardizzoli.springbootmongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Adress {
    private String country;
    private String city;
    private String postCode;
}
