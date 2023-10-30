package com.smit.Backend.Helpers;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.smit.Backend.Execptions.BadRequestException;
import com.smit.Backend.Models.DtoModels.FileUploadDto;

@Component
public class CloudinaryHelper {
    private final Cloudinary cloudinary;

    @Autowired
    public CloudinaryHelper(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public FileUploadDto uploadMedia(MultipartFile file) throws IOException {
        Map<String, String> options = ObjectUtils.asMap(
                "public_id", UUID.randomUUID().toString(),
                "resource_type", "auto");

        Map uploadResult = cloudinary.uploader().upload(file.getBytes(), options);
        var media = new FileUploadDto((String) uploadResult.get("secure_url"),
                (String) uploadResult.get("resource_type"), (int) uploadResult.get("bytes"),
                (String) uploadResult.get("public_id"));
        return media;
    }

    public void deleteFile(String publicId) throws IOException {
        Map deleteResult = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());

        if (!"ok".equals(deleteResult.get("result"))) {
            throw new BadRequestException("Error deleting file from cloudinary");
            
        }
    }
}
