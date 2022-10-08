package com.proyecto.proyecto.bean;

import java.util.*;

public class ArchivosViewModel {
    private Integer id;

    private String nombreArchivo;

    public List<String> messages = new ArrayList<>();

    public ArchivosViewModel(Integer id, String nombreArchivo) {
        this.id = id;
        this.nombreArchivo = nombreArchivo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
}