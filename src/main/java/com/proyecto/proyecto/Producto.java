package com.proyecto.proyecto;

import java.util.ArrayList;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "product")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 20, nullable = false, unique = false, name = "name")
    private String nombre;

    @Column(length = 20, nullable = true, unique = false, name = "brand")
    private String marca;

    @Column(nullable = false, name = "price")
    private Float precio;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "product_category", 
        joinColumns = @JoinColumn(name = "id_product"), 
        inverseJoinColumns = @JoinColumn(name = "id_category")
    )
    private List<Categoria> categories;

    public List<Categoria> getCategories() {
        return categories;
    }

    public boolean addCategory(Categoria category) {
        if (categories == null)
            categories = new ArrayList<>();
        return this.categories.add(category);
    }
    
    public boolean removeCategory(Categoria category){
        if(categories == null){
            categories = new ArrayList<>();
            return true;
        }
        return this.categories.remove(category);
    }

    public Producto() {

    }

    public Producto(String nombre, String marca, Float precio) {
        this.setMarca(marca);
        this.setNombre(nombre);
        this.setPrecio(precio);
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
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
        Producto other = (Producto) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Producto [id=" + id + ", marca=" + marca + ", nombre=" + nombre + ", precio=" + precio + "]";
    }
}
