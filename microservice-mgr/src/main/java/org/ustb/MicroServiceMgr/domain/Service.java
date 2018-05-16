package org.ustb.MicroServiceMgr.domain;

import java.util.List;

public class Service {
    private int id;
    private String name;
    private List<Instance> instance;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Instance> getInstance() {
        return instance;
    }

    public void setInstance(List<Instance> instance) {
        this.instance = instance;
    }

}


