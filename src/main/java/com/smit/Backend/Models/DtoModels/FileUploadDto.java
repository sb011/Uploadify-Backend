package com.smit.Backend.Models.DtoModels;

public class FileUploadDto {
    private String url;
    private String type;
    private int size;
    private String publicId;

    // getters
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

    // setters
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

    // constructor
    public FileUploadDto(String url, String type, int size, String publicId) {
        this.url = url;
        this.type = type;
        this.size = size;
        this.publicId = publicId;
    }

    public FileUploadDto() {
    }

    // toString
    @Override
    public String toString() {
        return "MediaModel [publicId=" + publicId + ", size=" + size + ", type=" + type + ", url=" + url + "]";
    }
}
