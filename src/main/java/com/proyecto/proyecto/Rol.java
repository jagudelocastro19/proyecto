package com.proyecto.proyecto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Rol {

    public Rol() {

    }

    public Rol(String nombre, String descripcion) {
        this.setDescripcion(descripcion);
        this.setNombre(nombre);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 20, nullable = false, unique = true, name = "name")
    private String nombre;
    @Column(length = 50, nullable = true, unique = false, name = "description")
    private String descripcion;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<Usuario> usuarios;

    // getter and setter
    public boolean addOrder(Usuario usuario) {
        if (usuarios == null)
            usuarios = new ArrayList<>();
        return this.usuarios.add(usuario);
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Rol other = (Rol) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Rol [descripcion=" + descripcion + ", id=" + id + ", nombre=" + nombre + "]";
    }

}
