package com.example.cat_microservice.service;

import com.example.cat_microservice.models.Color;
import com.example.cat_microservice.repositories.ColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorImplement implements ColorService{

    private final ColorRepository colorRepository;

    @Autowired
    public ColorImplement(ColorRepository colorRepository) {
        this.colorRepository = colorRepository;
    }
    @Override
    public Color getColorById(long colorId) {
        return colorRepository.findById(colorId).orElseThrow(() -> new IllegalArgumentException("Givno a ne cvet"));
    }

    @Override
    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }

    @Override
    public void addColor(Color color) {
        colorRepository.save(color);
    }

    @Override
    public void updateColor(Color color) {
        colorRepository.save(color);
    }

    @Override
    public void deleteColor(Color color) {
        colorRepository.delete(color);
    }
}
