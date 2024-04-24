package com.example.cat_microservice.repositories;

import com.example.cat_microservice.models.Color;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColorRepository extends JpaRepository<Color, Long> {
}
