package com.smit.Backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.smit.Backend.Helpers.JWTHelper;
import com.smit.Backend.Models.ResponseModels.FileResponse;
import com.smit.Backend.Services.Interfaces.IFileService;

@RestController
@RequestMapping("/api/files")
public class FileController {
    private final IFileService fileService;
    private final JWTHelper jwtHelper;

    @Autowired
    public FileController(IFileService fileService, JWTHelper jwtHelper) {
        this.fileService = fileService;
        this.jwtHelper = jwtHelper;
    }

    @PostMapping("/upload")
    public FileResponse uploadFile(@RequestParam MultipartFile file, @RequestHeader("Authorization") String token) {
        var tokenData = jwtHelper.validateToken(token.substring(7));
        var userId = tokenData.getClaim("userId").toString();
        userId = userId.substring(1, userId.length() - 1);
        return fileService.uploadFile(file, userId);
    }

    @GetMapping("/{id}")
    public FileResponse getFile(@PathVariable String id) {
        return fileService.getFile(id);
    }
}
