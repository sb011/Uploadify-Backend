package com.smit.Backend.Repositories.Interfaces;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.smit.Backend.Models.EntityModels.UserModel;

/**
 * User repository interface.
 * 
 * extends MongoRepository
 */
public interface IUserRepository extends MongoRepository<UserModel, String> {
    Optional<UserModel> findByEmail(String email);
}
