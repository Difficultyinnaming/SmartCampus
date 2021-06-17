package com.hss.campus.entity.util;

import java.util.List;

//文件名
public class ImageResponse {
    private String fileName;
    private List<Object> file;

    public ImageResponse() {
    }

    public ImageResponse(List<Object> file) {
        this.file = file;
    }

    public ImageResponse(String fileName) {
        this.fileName = fileName;
    }

    public List<Object> getFile() {
        return file;
    }

    public void setFile(List<Object> file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "ImageResponse{" +
                "fileName='" + fileName + '\'' +
                ", file=" + file +
                '}';
    }
}
