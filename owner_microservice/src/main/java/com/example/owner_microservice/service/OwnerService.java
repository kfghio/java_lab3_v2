package com.example.owner_microservice.service;

import com.example.owner_microservice.models.Owner;
import com.example.owner_microservice.models.OwnerDataTransferObject;
import com.example.owner_microservice.models.OwnerPage;

import java.util.UUID;

public interface OwnerService {
    void updateOwner(Owner owner);
    void addOwner(Owner owner);
    void deleteOwner(Owner owner);
    Owner getOwnerById(UUID ownerId);
    Owner getOwnerByUsername(String username);
    OwnerPage getAllOwnerDtoWithPagination(int pageNumber, int pageSize);
    OwnerDataTransferObject getOwnerDtoByUsername(String username);
}
