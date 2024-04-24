package com.example.cat_microservice.service;

import com.example.cat_microservice.exception.NotFoundCatException;
import com.example.cat_microservice.models.Cat;
import com.example.cat_microservice.models.CatDataTransferObject;
import com.example.cat_microservice.models.CatPage;
import com.example.cat_microservice.repositories.CatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CatImplement implements CatService{
    private final CatRepository catRepository;
    private CatDataTransferObject mapCatToDto(Cat cat) {
        return CatDataTransferObject.builder()
                .color(cat.getColor())
                .breed(cat.getBreed())
                .name(cat.getName())
                .birthday(cat.getBirthday())
                .build();
    }

    @Autowired
    public CatImplement(CatRepository catRepository) {
        this.catRepository = catRepository;
    }
    @Override
    public List<Cat> getCatsByOwnerId(UUID ownerId) {
        return catRepository.findAllByOwnerId(ownerId);
    }

    @Override
    public List<Cat> getAllCats() {
        return catRepository.findAll();
    }

    @Override
    public Cat getCatById(UUID catId) {
        try {
            return catRepository.findById(catId).orElseThrow(() -> new NotFoundCatException("Cat id not found"));
        } catch (NotFoundCatException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Cat getCatByName(String catName) {
        return catRepository.findCatByName(catName);
    }

    @Override
    public CatPage getAllCatDtoWithPagination(int pageNumber, int pageSize, UUID ownerId) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Cat> pageOfOwners = catRepository.findAllByOwnerId(pageable, ownerId);

        List<Cat> ownersContent = pageOfOwners.getContent();
        List<CatDataTransferObject> ownersDtoContent = ownersContent.stream().map(this::mapCatToDto).toList();

        return CatPage.builder()
                .content(ownersDtoContent)
                .pageNumber(pageOfOwners.getNumber())
                .pageSize(pageOfOwners.getSize())
                .totalElement(pageOfOwners.getTotalElements())
                .totalPages(pageOfOwners.getTotalPages())
                .isLast(pageOfOwners.isLast())
                .build();
    }

    @Override
    public void addCat(Cat cat) {
        catRepository.save(cat);

    }

    @Override
    public void updateCat(Cat cat) {
        catRepository.save(cat);
    }

    @Override
    public void deleteCat(Cat cat) {
        catRepository.delete(cat);
    }

    @Override
    public void addOrUpdateCatWithDtoById(CatDataTransferObject catDto, UUID id) {
        Cat cat = null;
        try {
            cat = catRepository.findById(id).orElseThrow(() -> new NotFoundCatException("Cat's username doesn't exist"));
        } catch (NotFoundCatException e) {
            throw new RuntimeException(e);
        }
        Cat catResult = new Cat();
        catResult.setName(catDto.getName());
        catResult.setBreed(catDto.getBreed());
        catResult.setBirthday(catDto.getBirthday());
        catResult.setColor(catDto.getColor());
        catResult.setOwnerId(cat.getOwnerId());
        catResult.setId(cat.getId());
        catRepository.save(catResult);
    }
}
