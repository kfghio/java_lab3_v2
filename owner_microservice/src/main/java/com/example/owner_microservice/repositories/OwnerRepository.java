package com.example.owner_microservice.repositories;

import com.example.owner_microservice.models.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OwnerRepository extends JpaRepository<Owner, UUID> {
    Optional<Owner> findOwnerByUsername(String username);
}