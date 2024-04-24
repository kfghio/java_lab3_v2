package com.example.cat_microservice.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatDataTransferObject {
    private String name;
    private Date birthday;
    private String breed;
    private Color color;
}