package com.smit.Backend.Repositories.Interfaces;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.smit.Backend.Models.EntityModels.FileModel;

public interface IFileRepository extends MongoRepository<FileModel, String> {
}
