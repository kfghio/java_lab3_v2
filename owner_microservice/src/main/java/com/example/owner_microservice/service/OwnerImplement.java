package com.example.owner_microservice.service;

import com.example.owner_microservice.exception.NotFoundOwnerException;
import com.example.owner_microservice.models.Owner;
import com.example.owner_microservice.models.OwnerDataTransferObject;
import com.example.owner_microservice.models.OwnerPage;
import com.example.owner_microservice.repositories.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OwnerImplement implements OwnerService {
    private final OwnerRepository ownerRepository;

    private OwnerDataTransferObject mapOwnerToDto(Owner owner) {
        return OwnerDataTransferObject.builder()
                .birthday(owner.getBirthday())
                .build();
    }

    @Autowired
    public OwnerImplement(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }
    @Override
    public void updateOwner(Owner owner) {
        ownerRepository.save(owner);
    }

    @Override
    public void addOwner(Owner owner) {
        ownerRepository.save(owner);
    }

    @Override
    public void deleteOwner(Owner owner) {
        ownerRepository.delete(owner);
    }

    @Override
    public Owner getOwnerById(UUID ownerId) {
        try {
            return ownerRepository.findById(ownerId).orElseThrow(() -> new NotFoundOwnerException("Owner's id doesn't exist"));
        } catch (NotFoundOwnerException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Owner getOwnerByUsername(String username) {
        try {
            return ownerRepository.findOwnerByUsername(username).orElseThrow(() -> new NotFoundOwnerException("Owner's id doesn't exist"));
        } catch (NotFoundOwnerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OwnerPage getAllOwnerDtoWithPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Owner> pageOfOwners = ownerRepository.findAll(pageable);

        List<Owner> ownersContent = pageOfOwners.getContent();
        List<OwnerDataTransferObject> ownersDtoContent = ownersContent.stream().map(this::mapOwnerToDto).toList();

        return OwnerPage.builder()
                .content(ownersDtoContent)
                .pageNumber(pageOfOwners.getNumber())
                .pageSize(pageOfOwners.getSize())
                .totalElement(pageOfOwners.getTotalElements())
                .totalPages(pageOfOwners.getTotalPages())
                .isLast(pageOfOwners.isLast())
                .build();
    }

    @Override
    public OwnerDataTransferObject getOwnerDtoByUsername(String username) {
        Owner owner = getOwnerByUsername(username);
        return mapOwnerToDto(owner);
    }
}
