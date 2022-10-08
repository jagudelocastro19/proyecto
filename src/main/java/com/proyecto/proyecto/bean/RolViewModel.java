package com.proyecto.proyecto.bean;

import java.util.*;


public class RolViewModel {
    private Integer id;
    
    private String nombre;
    
    private String descripcion;

    public List<String> messages = new ArrayList<>();

    private List<RolViewModel> searchResults = new ArrayList<>();

    public RolViewModel(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public List<RolViewModel> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<RolViewModel> searchResults) {
        this.searchResults = searchResults;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
