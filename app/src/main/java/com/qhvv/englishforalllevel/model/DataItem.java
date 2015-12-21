package com.qhvv.englishforalllevel.model;

import java.util.List;


public class DataItem {
    private String fileName;
    private String display;
    private List<DataItem> children;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public List<DataItem> getChildren() {
        return children;
    }

    public void setChildren(List<DataItem> children) {
        this.children = children;
    }

    public boolean isFileTest(){
        return children==null || children.size()==0;
    }
}
