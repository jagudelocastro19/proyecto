package com.proyecto.proyecto.bean;

import java.util.*;

import com.proyecto.proyecto.Rol;
import com.proyecto.proyecto.Usuario;

public class UsuarioViewModel {
    private Integer id;
    private String username;
    private String password;
    private Date fecha_expiracion;

    private Rol role;

    public Rol getRole() {
        return role;
    }

    public void setRole(Rol role) {
        this.role = role;
    }

    public List<Rol> roles = new ArrayList<>();

    public List<String> messages = new ArrayList<>();

    private List<Usuario> searchResults = new ArrayList<>();

    public UsuarioViewModel(Integer id, String username, String password, Date fecha_expiracion, Rol role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fecha_expiracion = fecha_expiracion;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getFecha_expiracion() {
        return fecha_expiracion;
    }

    public void setFecha_expiracion(Date fecha_expiracion) {
        this.fecha_expiracion = fecha_expiracion;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public List<Usuario> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<Usuario> searchResults) {
        this.searchResults = searchResults;
    }


}
