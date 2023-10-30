package com.smit.Backend.Repositories.Interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.smit.Backend.Models.EntityModels.FileModel;

public interface IFileRepository extends MongoRepository<FileModel, String> {
    Optional<FileModel> findByPublicId(String publicId);

    List<FileModel> findByUserId(String userId);
}
