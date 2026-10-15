package com.example.studylistviewdata;

/**
 * Created by Jay on 2015/9/21 0021.
 */
public class DataOne {
    private int imgId;
    private String content;

    public DataOne() {}

    public DataOne(int imgId, String content) {
        this.imgId = imgId;
        this.content = content;
    }

    public int getImgId() {
        return imgId;
    }

    public String getContent() {
        return content;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

    public void setContent(String content) {
        this.content = content;
    }
}