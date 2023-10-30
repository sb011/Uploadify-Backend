package com.smit.Backend.Services.Interfaces;

import org.springframework.web.multipart.MultipartFile;

import com.smit.Backend.Models.ResponseModels.FileResponse;

public interface IFileService {
    FileResponse uploadFile(MultipartFile file, String userId);

    FileResponse getFile(String publicId);

    void deleteFile(String id, String userId);
}
