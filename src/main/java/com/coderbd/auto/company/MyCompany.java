package com.coderbd.auto.company;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MyCompany {
    private Long id;
    private String courseType;
    private String description;
    private String email;
    private String mobileNo;
    private double price;
}
