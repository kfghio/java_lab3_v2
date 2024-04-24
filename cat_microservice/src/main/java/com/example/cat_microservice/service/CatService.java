package com.example.cat_microservice.service;
import java.util.*;
import com.example.cat_microservice.models.CatDataTransferObject;
import com.example.cat_microservice.models.Cat;
import com.example.cat_microservice.models.CatPage;

public interface CatService {
    List<Cat> getCatsByOwnerId(UUID ownerId);
    List<Cat> getAllCats();
    Cat getCatById(UUID catId);
    Cat getCatByName(String catName);
    CatPage getAllCatDtoWithPagination(int pageNumber, int pageSize, UUID ownerId);
    void addCat(Cat cat);
    void updateCat(Cat cat);
    void deleteCat(Cat cat);
    void addOrUpdateCatWithDtoById(CatDataTransferObject catDto, UUID id);

}
