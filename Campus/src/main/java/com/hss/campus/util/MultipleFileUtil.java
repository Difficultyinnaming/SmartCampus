package com.hss.campus.util;

import java.util.List;

public class MultipleFileUtil {
    private List<String> fileName;

    public MultipleFileUtil() {
    }

    public MultipleFileUtil(List<String> fileName) {
        this.fileName = fileName;
    }

    public List<String> getFileName() {
        return fileName;
    }

    public void setFileName(List<String> fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "img{" +
                "fileName=" + fileName +
                '}';
    }
}
