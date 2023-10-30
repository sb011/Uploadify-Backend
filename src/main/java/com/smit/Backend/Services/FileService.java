package com.smit.Backend.Services;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.smit.Backend.Enumerable.MediaTypes;
import com.smit.Backend.Execptions.BadRequestException;
import com.smit.Backend.Helpers.CloudinaryHelper;
import com.smit.Backend.Models.DtoModels.FileUploadDto;
import com.smit.Backend.Models.EntityModels.FileModel;
import com.smit.Backend.Models.ResponseModels.FileResponse;
import com.smit.Backend.Repositories.Interfaces.IFileRepository;
import com.smit.Backend.Services.Interfaces.IFileService;

@Service
public class FileService implements IFileService {
    private final IFileRepository fileRepository;
    private final CloudinaryHelper cloudinaryHelper;

    @Autowired
    public FileService(IFileRepository fileRepository, CloudinaryHelper cloudinaryHelper) {
        this.fileRepository = fileRepository;
        this.cloudinaryHelper = cloudinaryHelper;
    }

    public FileResponse uploadFile(MultipartFile file) {
        if (file.isEmpty() || file.getSize() == 0 || file.getOriginalFilename() == null) {
            throw new BadRequestException("Uploaded file is empty");
        }
        var fileExtension = file.getOriginalFilename().split("\\.")[1];
        if (EnumUtils.isValidEnum(MediaTypes.class, fileExtension) == false) {
            throw new BadRequestException("File type not allowed" + fileExtension);
        }

        var fileSize = file.getSize();
        if (fileSize > 5 * 1024 * 1024) {
            throw new BadRequestException("File size should not exceed 5MB");
        }

        FileUploadDto media;
        try {
            media = cloudinaryHelper.uploadMedia(file);
        } catch (IOException exception) {
            throw new BadRequestException(exception.getMessage());
        }

        var expiresAt = LocalDateTime.now().plusDays(1);
        var fileEntity = new FileModel(media.getUrl(), fileExtension, media.getSize(), media.getPublicId(),
                expiresAt);

        var response = fileRepository.save(fileEntity);
        return new FileResponse(response.getId(), response.getType(),
                response.getSize(), response.getPublicId(), response.getExpiresAt(), response.getCreatedAt(),
                response.getUpdatedAt());
    }
}
