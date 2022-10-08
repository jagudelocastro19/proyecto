package com.proyecto.proyecto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer>{
    public abstract  java.util.List<Usuario> findAllByRole(Rol role);
}
