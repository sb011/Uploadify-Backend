package com.smit.Backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.smit.Backend.Models.ResponseModels.FileResponse;
import com.smit.Backend.Services.Interfaces.IFileService;

@RestController
@RequestMapping("/api/files")
public class FileController {
    private final IFileService fileService;

    @Autowired
    public FileController(IFileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping("/upload")
    public FileResponse uploadFile(@RequestParam MultipartFile file) {
        return fileService.uploadFile(file);
    }

    @GetMapping("/{id}")
    public FileResponse getFile(@PathVariable String id) {
        return fileService.getFile(id);
    }
}
