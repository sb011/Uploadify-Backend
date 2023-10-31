package com.smit.Backend.Services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smit.Backend.Enumerable.MediaExtensionTypes;
import com.smit.Backend.Execptions.BadRequestException;
import com.smit.Backend.Execptions.UnauthorizedException;
import com.smit.Backend.Helpers.CloudinaryHelper;
import com.smit.Backend.Models.DtoModels.FileUploadDto;
import com.smit.Backend.Models.EntityModels.FileModel;
import com.smit.Backend.Models.ResponseModels.FileResponse;
import com.smit.Backend.Repositories.Interfaces.IFileRepository;
import com.smit.Backend.Services.Interfaces.IFileService;

/**
 * File service class.
 */
@Service
public class FileService implements IFileService {
    private final IFileRepository fileRepository;
    private final CloudinaryHelper cloudinaryHelper;

    /**
     * Constructor.
     * 
     * @param fileRepository   file repository
     * @param cloudinaryHelper cloudinary helper
     */
    @Autowired
    public FileService(IFileRepository fileRepository, CloudinaryHelper cloudinaryHelper) {
        this.fileRepository = fileRepository;
        this.cloudinaryHelper = cloudinaryHelper;
    }

    /**
     * This method handles upload requests.
     * 
     * @param file   file
     * @param userId user ID
     * @return file response
     */
    public FileResponse uploadFile(MultipartFile file, String userId) {
        // validate request
        if (file.isEmpty() || file.getSize() == 0 || file.getOriginalFilename() == null) {
            throw new BadRequestException("Uploaded file is empty");
        }
        var fileExtension = file.getOriginalFilename().split("\\.")[1];
        if (EnumUtils.isValidEnum(MediaExtensionTypes.class, fileExtension) == false) {
            throw new BadRequestException("File type not allowed" + fileExtension);
        }

        // validate file size
        var fileSize = file.getSize();
        if (fileSize > 5 * 1024 * 1024) {
            throw new BadRequestException("File size should not exceed 5MB");
        }

        // upload file to cloudinary
        FileUploadDto media;
        try {
            media = cloudinaryHelper.uploadMedia(file);
        } catch (IOException exception) {
            throw new BadRequestException(exception.getMessage());
        }

        var expiresAt = LocalDateTime.now().plusDays(1);
        var fileEntity = new FileModel(media.getUrl(), fileExtension, media.getSize(), media.getPublicId(), userId,
                file.getContentType(), file.getOriginalFilename(), expiresAt);

        var response = fileRepository.save(fileEntity);
        return new FileResponse(response.getId(), response.getType(),
                response.getSize(), response.getPublicId(), null, response.getUserId(), response.getMediaType(),
                response.getFileName(),
                response.getExpiresAt(),
                response.getCreatedAt(),
                response.getUpdatedAt());
    }

    /**
     * This method handles get file requests.
     * 
     * @param publicId public ID
     * @return file response
     */
    public FileResponse getFile(String publicId) {
        var file = fileRepository.findByPublicId(publicId).orElseThrow(() -> new BadRequestException("File not found"));

        // check if file has expired
        if (file.getExpiresAt() != null && file.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("File has expired");
        }

        return new FileResponse(file.getId(), file.getType(), file.getSize(), file.getPublicId(), file.getUrl(),
                file.getUserId(), file.getMediaType(), file.getFileName(), file.getExpiresAt(), file.getCreatedAt(),
                file.getUpdatedAt());
    }

    /**
     * This method handles get all files requests.
     * 
     * @param userId user ID
     * @return list of file responses
     */
    public List<FileResponse> getAllFile(String userId) {
        List<FileResponse> files = fileRepository.findByUserId(userId)
                .stream()
                .filter(file -> file.getExpiresAt() == null || file.getExpiresAt().isAfter(LocalDateTime.now()))
                .map(file -> new FileResponse(file.getId(), file.getType(), file.getSize(), file.getPublicId(),
                        file.getUrl(),
                        file.getUserId(), file.getMediaType(), file.getFileName(), file.getExpiresAt(),
                        file.getCreatedAt(),
                        file.getUpdatedAt()))
                .toList();

        return files;
    }

    /**
     * This method handles delete file requests.
     * 
     * @param id     ID
     * @param userId user ID
     */
    public void deleteFile(String id, String userId) {
        var file = fileRepository.findById(id).orElseThrow(() -> new BadRequestException("File not found"));
        if (!file.getUserId().equals(userId)) {
            throw new UnauthorizedException("You are not authorized to delete this file");
        }

        // delete file from cloudinary
        try {
            cloudinaryHelper.deleteFile(file.getPublicId());
        } catch (IOException exception) {
            throw new BadRequestException(exception.getMessage());
        }

        // delete file from database
        fileRepository.deleteById(id);
    }
}
