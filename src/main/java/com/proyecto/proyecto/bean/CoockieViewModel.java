package com.proyecto.proyecto.bean;

import java.util.*;

public class CoockieViewModel {
    private String key;
    private String value;

    public List<CoockieViewModel> coockies = new ArrayList<>();

    public CoockieViewModel(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<CoockieViewModel> getCoockies() {
        return coockies;
    }

    public void setCoockies(List<CoockieViewModel> coockies) {
        this.coockies = coockies;
    }

}
