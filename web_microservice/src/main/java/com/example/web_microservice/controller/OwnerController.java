package com.example.web_microservice.controller;

import com.example.owner_microservice.models.Owner;
import com.example.owner_microservice.models.OwnerDataTransferObject;
import com.example.owner_microservice.models.OwnerPage;
import com.example.owner_microservice.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/owner/")
public class OwnerController {
    private final OwnerService ownerService;
    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @DeleteMapping("delete/{username}")
    public ResponseEntity<String> deleteOwner(@PathVariable("username") String username){

        Owner owner = ownerService.getOwnerByUsername(username);
        ownerService.deleteOwner(owner);
        return new ResponseEntity<>("Owner was deleted successfully", HttpStatus.OK);
    }

    @GetMapping("details/{username}")
    public ResponseEntity<OwnerDataTransferObject> getOwnerDetails(@PathVariable("username") String username){
        return new ResponseEntity<>(ownerService.getOwnerDtoByUsername(username), HttpStatus.OK);
    }

    @GetMapping("all")
    public ResponseEntity<OwnerPage> getAllUsersDetails(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {

        return new ResponseEntity<>(ownerService.getAllOwnerDtoWithPagination(pageNumber, pageSize), HttpStatus.OK);
    }
}