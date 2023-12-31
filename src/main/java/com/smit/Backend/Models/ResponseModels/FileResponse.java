package com.smit.Backend.Models.ResponseModels;

import java.time.LocalDateTime;

/**
 * File response model class.
 */
public class FileResponse {
    private String id;
    private String type;
    private int size;
    private String publicId;
    private String url;
    private String userId;
    private String mediaType;
    private String fileName;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // getters
    public String getId() {
        return id;
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

    public String getUrl() {
        return url;
    }

    public String getUserId() {
        return userId;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getFileName() {
        return fileName;
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

    public void setType(String type) {
        this.type = type;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    // constructor
    public FileResponse(String id, String type, int size, String publicId, String url, String userId, String mediaType,
            String fileName,
            LocalDateTime expiresAt,
            LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.type = type;
        this.size = size;
        this.publicId = publicId;
        this.url = url;
        this.userId = userId;
        this.mediaType = mediaType;
        this.fileName = fileName;
        this.expiresAt = expiresAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public FileResponse() {
    }

    // toString
    @Override
    public String toString() {
        return "FileResponse [createdAt=" + createdAt + ", expiresAt=" + expiresAt + ", fileName=" + fileName + ", id="
                + id + ", mediaType=" + mediaType + ", publicId=" + publicId + ", size=" + size + ", type=" + type
                + ", updatedAt=" + updatedAt + ", url=" + url + ", userId=" + userId + "]";
    }
}
