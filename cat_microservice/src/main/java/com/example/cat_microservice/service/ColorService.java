package com.example.cat_microservice.service;

import com.example.cat_microservice.models.Color;

import java.util.List;

public interface ColorService {
    Color getColorById(long colorId);
    List<Color> getAllColors();
    void addColor(Color color);
    void updateColor(Color color);
    void deleteColor(Color color);

}
