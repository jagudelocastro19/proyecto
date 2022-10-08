package com.proyecto.proyecto;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class Usuario {
    public Usuario() {
    }

    public Usuario(String username, String password, Rol role, Date fecha_expiracion) {
        this.setUsername(username);
        this.setPassword(password);
        this.setRole(role);
        this.setFecha_expiracion(fecha_expiracion);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 20, unique = true, name = "usuario")
    private String username;
    @Column(nullable = false, length = 20, unique = false, name = "contrasena")
    private String password;
    // @Column(nullable = false, unique = false, name = "id_role")
    // private Integer idRole;
    @Column(nullable = true, unique = false, name = "expiration_date")
    private Date fecha_expiracion;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "id_role")
    private Rol role;

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

    public Rol getRole() {
        return this.role;
    }

    public void setRole(Rol role) {
        this.role = role;
    }

    public Date getFecha_expiracion() {
        return fecha_expiracion;
    }

    public void setFecha_expiracion(Date fecha_expiracion) {
        this.fecha_expiracion = fecha_expiracion;
    }

    @Override
    public String toString() {
        return "Usuario [fecha_expiracion=" + fecha_expiracion + ", id=" + id + ", role=" + this.role.getId()
                + ", username="
                + username + "]";
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
        Usuario other = (Usuario) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

}
