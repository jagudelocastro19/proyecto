package com.proyecto.proyecto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import com.proyecto.proyecto.ems.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioCrudTest {

    @Autowired
    private UsuarioRepository usrRepo;

    @Autowired
    private RolRepository roleRepo;

    @Test
    public void createUser() {
        Rol role = new Rol("nombre", "descripcion");
        Rol roleCreated = roleRepo.save(role);

        Usuario usr = new Usuario("username", "password", roleCreated, new Date());
        Usuario usrCreated = usrRepo.save(usr);
        System.out.println(role.toString());
        System.out.println(usrCreated.toString());
        assertNotNull(usrCreated.getId());
        assertTrue(usrCreated.getId() > 0);
    }

    @Test
    public void updateUser() {
        String newUsername = "username_changed";
        String newPassword = "password_changed";
        // Create Role
        Rol role = new Rol("nombre", "descripcion");
        Rol roleCreated = roleRepo.save(role);
        // Create User
        Usuario usr = new Usuario("username", "password", roleCreated, new Date());
        Usuario usrCreated = usrRepo.save(usr);
        // Search User
        Usuario usrSearched;
        Optional<Usuario> usrSearchedOptional = usrRepo.findById(usrCreated.getId());
        assertTrue(usrSearchedOptional.isPresent());
        usrSearched = usrSearchedOptional.get();
        // Update User
        usrSearched.setPassword(newPassword);
        usrSearched.setUsername(newUsername);
        Usuario usrUpdated = usrRepo.save(usrSearched);
        // test changes
        assertNotNull(usrUpdated.getId());
        assertTrue(usrUpdated.getId() > 0);
        assertEquals(newPassword, usrUpdated.getPassword());
        assertEquals(newUsername, usrUpdated.getUsername());
        // log
        System.out.println(roleCreated.toString());
        System.out.println(usrUpdated.toString());
    }

    @Test
    public void deleteUser() {
        // Create Role
        Rol role = new Rol("nombre", "descripcion");
        Rol roleCreated = roleRepo.save(role);
        // Create User
        Usuario usr = new Usuario("username", "password", roleCreated, new Date());
        Usuario usrCreated = usrRepo.save(usr);
        // Search User
        Usuario usrSearched;
        Optional<Usuario> usrSearchedOptional = usrRepo.findById(usrCreated.getId());
        assertTrue(usrSearchedOptional.isPresent());
        usrSearched = usrSearchedOptional.get();
        // delete user
        usrRepo.delete(usrSearched);
        // test correct delete
        usrSearchedOptional = usrRepo.findById(usrCreated.getId());
        assertFalse(usrSearchedOptional.isPresent());
        // log
        System.out.println(roleCreated.toString());
        System.out.println(usrSearchedOptional.toString());
    }

    @Test
    public void findAllUsers() {
        // Create Role
        Rol role = new Rol("nombre", "descripcion");
        Rol roleCreated = roleRepo.save(role);
        // Create Users
        Usuario usr1 = new Usuario("username1", "password1", roleCreated, new Date());
        Usuario usr2 = new Usuario("username2", "password2", roleCreated, new Date());
        Usuario usr3 = new Usuario("username3", "password3", roleCreated, new Date());
        Usuario usrCreated1 = usrRepo.save(usr1);
        Usuario usrCreated2 = usrRepo.save(usr2);
        Usuario usrCreated3 = usrRepo.save(usr3);
        // find all by role
        List<Usuario> usrs = usrRepo.findAllByRole(roleCreated);
        assertTrue(usrs.size() > 0);

    }
}
