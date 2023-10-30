package com.smit.Backend.Models.EntityModels;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

public class FileModel {
    private String id;
    private String url;
    private String type;
    private int size;
    private String publicId;
    private LocalDateTime expiresAt;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    // getters
    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public String getPublicId() {
        return publicId;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // setters
    public void setId(String id) {
        this.id = id;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    // constructor
    public FileModel(String id, String url, String type, int size, String publicId, LocalDateTime expiresAt,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.url = url;
        this.type = type;
        this.size = size;
        this.publicId = publicId;
        this.expiresAt = expiresAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public FileModel(String url, String type, int size, String publicId, LocalDateTime expiresAt) {
        this.url = url;
        this.type = type;
        this.size = size;
        this.publicId = publicId;
        this.expiresAt = expiresAt;
    }

    public FileModel() {
    }

    // toString
    @Override
    public String toString() {
        return "FileModel [createdAt=" + createdAt + ", expiresAt=" + expiresAt + ", id=" + id + ", publicId="
                + publicId + ", size=" + size + ", type=" + type + ", updatedAt=" + updatedAt + ", url=" + url + "]";
    }
}
