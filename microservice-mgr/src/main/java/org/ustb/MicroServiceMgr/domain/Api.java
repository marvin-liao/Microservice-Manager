package org.ustb.MicroServiceMgr.domain;

import java.util.Date;
import java.util.List;

public class Api {
    private int id;
    private String title;
    private String description;
    private String doc;
    private Date lastModify;
    private int result; // 1为正常，2为异常，3为禁用

    private String host;
    private String basePath;

    private List<ApiItem> items;

    //以下代码为自动生成
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public Date getLastModify() {
        return lastModify;
    }

    public void setLastModify(Date lastModify) {
        this.lastModify = lastModify;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public List<ApiItem> getItems() {
        return items;
    }

    public void setItems(List<ApiItem> items) {
        this.items = items;
    }
}
