package com.example.web_microservice.controller;

import com.example.cat_microservice.models.Cat;
import com.example.cat_microservice.models.CatDataTransferObject;
import com.example.cat_microservice.models.CatPage;
import com.example.cat_microservice.service.CatService;
import com.example.owner_microservice.models.Owner;
import com.example.owner_microservice.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cat/")
public class CatController {

    private final CatService catService;
    private final OwnerService ownerService;

    @Autowired
    public CatController(CatService catService, OwnerService ownerService) {
        this.catService = catService;
        this.ownerService = ownerService;
    }


    @PutMapping("update/{username}/{catId}")
    public ResponseEntity<String> updateCat(
            @RequestBody CatDataTransferObject catDto, @PathVariable("username") String username,
            @PathVariable("catId") UUID catId
    ){

        catService.addOrUpdateCatWithDtoById(catDto, catId);
        return new ResponseEntity<>("Cat was updated successfully", HttpStatus.OK);
    }

    @PostMapping("add/{username}/{catId}")
    public ResponseEntity<String> addCat(
            @RequestBody CatDataTransferObject catDto, @PathVariable("username") String username,
            @PathVariable("catId") UUID catId
    ){

        catService.addOrUpdateCatWithDtoById(catDto, catId);
        return new ResponseEntity<>("Cat was updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("delete/{username}/{catId}")
    public ResponseEntity<String> addOwner(
            @PathVariable("username") String username, @PathVariable("catId") UUID catId
    ){
        Cat cat = catService.getCatById(catId);
        catService.deleteCat(cat);
        return new ResponseEntity<>("Cat was updated successfully", HttpStatus.OK);
    }

    @GetMapping("details/{username}/{catId}")
    public ResponseEntity<List<Cat>> getCatDetails(
            @PathVariable("username") String username, @PathVariable("catId") UUID catId
    ){

        Owner owner = ownerService.getOwnerByUsername(username);
        return new ResponseEntity<>(catService.getCatsByOwnerId(owner.getId()), HttpStatus.OK);
    }

    @GetMapping("all/{username}")
    public ResponseEntity<CatPage> getAllUsersDetails(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
            @PathVariable("username") String username
    ) {

        Owner owner = ownerService.getOwnerByUsername(username);
        return new ResponseEntity<>(catService.getAllCatDtoWithPagination(pageNumber, pageSize, owner.getId()), HttpStatus.OK);
    }
}