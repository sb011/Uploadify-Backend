package com.smit.Backend.Services.Interfaces;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.smit.Backend.Models.ResponseModels.FileResponse;

/**
 * File service interface.
 */
public interface IFileService {
    /**
     * This method handles upload requests.
     * 
     * @param file   file
     * @param userId user ID
     * @return file response
     */
    FileResponse uploadFile(MultipartFile file, String userId);

    /**
     * This method handles download requests.
     * 
     * @param publicId public ID
     * @return file response
     */
    FileResponse getFile(String publicId);

    /**
     * This method handles get all files requests.
     * 
     * @param userId user ID
     * @return list of file responses
     */
    List<FileResponse> getAllFile(String userId);

    /**
     * This method handles delete file requests.
     * 
     * @param id     ID
     * @param userId user ID
     */
    void deleteFile(String id, String userId);
}
