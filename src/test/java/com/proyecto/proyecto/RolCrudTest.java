package com.proyecto.proyecto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.Test;
import com.proyecto.proyecto.ems.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.*;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class RolCrudTest {
    @Autowired
    private RolRepository roleRepo;

    @Test
    public void createRole() {
        // Definiciones
        String roleName = "role_name";
        String roleDescription = "role_description";
        // Procesos
        Rol role = new Rol(roleName, roleDescription);
        Rol roleSaved = roleRepo.save(role);
        // Pruebas
        assertNotNull(roleSaved.getId());
        assertTrue(roleSaved.getId() > 0);
        // Logs
        System.out.println(roleSaved.toString());
    }

    @Test
    public void updateRole() {
        // Definiciones
        String roleName = "role_name";
        String roleDescription = "role_description";
        String newRoleName = "new_role_name";
        String newRoleDescription = "new_role_description";
        // Procesos
        // Crear
        Rol role = new Rol(roleName, roleDescription);
        Rol roleSaved = roleRepo.save(role);
        // Buscar ese registro
        Optional<Rol> roleSearchedOptional = roleRepo.findById(roleSaved.getId());
        Rol roleSearched = roleSearchedOptional.get();
        // actualizar registro
        roleSearched.setDescripcion(newRoleDescription);
        roleSearched.setNombre(newRoleName);
        Rol roleUpdated = roleRepo.save(roleSearched);
        // Pruebas
        assertNotNull(roleUpdated.getId());
        assertTrue(roleUpdated.getId() > 0);
        assertTrue(roleUpdated.getNombre() == newRoleName);
        assertTrue(roleUpdated.getDescripcion() == newRoleDescription);
        // Logs
        System.out.println(roleUpdated.toString());
    }

    @Test
    public void deleteRole() {
        // Definiciones
        String roleName = "role_name";
        String roleDescription = "role_description";
        // Procesos
        // Crear
        Rol role = new Rol(roleName, roleDescription);
        Rol roleSaved = roleRepo.save(role);
        // Buscar ese registro
        Optional<Rol> roleSearchedOptional = roleRepo.findById(roleSaved.getId());
        Rol roleSearched = roleSearchedOptional.get();
        // eliminarlo registro
        Integer roleId = roleSaved.getId();
        roleRepo.deleteById(roleSaved.getId());
        // Volver a buscarlo
        Optional<Rol> roleDeletedOptional = roleRepo.findById(roleId);
        // Pruebas
        assertFalse(roleDeletedOptional.isPresent());
        // Logs
        System.out.println(roleSaved.toString());
        System.out.println(roleSearched.toString());
    }

    @Test
    public void findAllRoles() {
        // Definiciones
        String roleName1 = "role_name1";
        String roleDescription1 = "role_description1";
        String roleName2 = "role_name2";
        String roleDescription2 = "role_description2";
        String roleName3 = "role_name3";
        String roleDescription3 = "role_description3";
        // Procesos
        // Crear 3
        Rol role1 = new Rol(roleName1, roleDescription1);
        Rol role2 = new Rol(roleName2, roleDescription2);
        Rol role3 = new Rol(roleName3, roleDescription3);
        List<Rol> listToSaved = new ArrayList<>();
        listToSaved.add(role1);
        listToSaved.add(role2);
        listToSaved.add(role3);
        roleRepo.saveAll(listToSaved);
        // Buscar todos los registros
        List<Rol> listSearched = roleRepo.findAll();
        // Pruebas
        assertTrue(listSearched.size() > 0);
        // Logs
        listSearched.forEach(
                role -> {
                    System.out.println(role.toString());
                });
    }

    @Test
    public void findOneRole() {
        // Definiciones
        String roleName = "role_name";
        String roleDescription = "role_description";
        // Procesos
        // Crear
        Rol role = new Rol(roleName, roleDescription);
        Rol roleSaved = roleRepo.save(role);
        // buscar
        Optional<Rol> roleSearchedOptional = roleRepo.findById(roleSaved.getId());
        // Pruebas
        assertTrue(roleSearchedOptional.isPresent());
        // Logs
        System.out.println(roleSearchedOptional.get().toString());
    }
}
