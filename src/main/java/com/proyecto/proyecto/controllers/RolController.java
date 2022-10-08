package com.proyecto.proyecto.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.HttpMethodConstraint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.proyecto.CategoriaRepository;
import com.proyecto.proyecto.Rol;
import com.proyecto.proyecto.RolRepository;
import com.proyecto.proyecto.bean.RolViewModel;

@CrossOrigin
@Controller
@RequestMapping("/rol")
public class RolController {

    @Autowired
    RolRepository roleRepo;

    @RequestMapping(value = "/")
    public String index(RolViewModel rolViewModel) {
        return "roles";
    }

    @RequestMapping(value = "/crear/", method = RequestMethod.GET)
    public String create(RolViewModel rolViewModel) {
        return "roles/crear";
    }

    @RequestMapping(value = "/crear/", method = RequestMethod.POST)
    public String createPost(RolViewModel rolViewModel) {
        String roleName = rolViewModel.getNombre();
        String roleDescription = rolViewModel.getDescripcion();
        // Procesos
        Rol roleToSaved = new Rol(roleName, roleDescription);
        Rol roleSaved = roleRepo.save(roleToSaved);
        Boolean validation = roleSaved.getId() != null && roleSaved.getId() > 0;
        // update model
        if (validation) {
            rolViewModel.messages.add("El rol fue creado correctamente");
        } else {
            rolViewModel.messages.add("Hubo algun algun problema al crear el rol, por favor intentelo mas tarde.");
        }
        rolViewModel.setId(roleSaved.getId());
        return "roles";
    }

    @RequestMapping(value = "/actualizar/{id}")
    public String actualizar(RolViewModel rolViewModel) {
        Optional<Rol> roleSearchedOptional = roleRepo.findById(rolViewModel.getId());
        if (roleSearchedOptional.isPresent()) {
            Rol rolSearched = roleSearchedOptional.get();
            rolViewModel.setNombre(rolSearched.getNombre());
            rolViewModel.setDescripcion(rolSearched.getDescripcion());
        } else {
            rolViewModel.messages.add("Por favor introduzca el id correctamente");
            return "roles";
        }
        return "roles/actualizar";
    }

    @RequestMapping(value = "/actualizar/", method = RequestMethod.POST)
    public String actualizarPost(RolViewModel rolViewModel) {
        Optional<Rol> roleSearchedOptional = roleRepo.findById(rolViewModel.getId());
        if (roleSearchedOptional.isPresent()) {
            Rol rolSearched = roleSearchedOptional.get();
            rolSearched.setDescripcion(rolViewModel.getDescripcion());
            rolSearched.setNombre(rolViewModel.getNombre());
            roleRepo.save(rolSearched);
            rolViewModel.messages.add("Rol actualizado correctamente");
            return "roles";
        }
        rolViewModel.messages.add("Por favor introduzca el id correctamente");
        return "roles/actualizar";
    }

    @RequestMapping(value = "/buscar/{id}")
    public String buscarById(RolViewModel rolViewModel) {
        Optional<Rol> roleSearchedOptional = roleRepo.findById(rolViewModel.getId());
        if (roleSearchedOptional.isPresent()) {
            Rol rolSearched = roleSearchedOptional.get();
            RolViewModel rvm = new RolViewModel(
                    rolSearched.getNombre(),
                    rolSearched.getDescripcion());
            rvm.setId(rolViewModel.getId());
            rolViewModel.getSearchResults().add(rvm);
        }
        return "roles/buscar";
    }

    @RequestMapping(value = "/buscar/")
    public String buscarTodos(RolViewModel rolViewModel) {
        List<Rol> rolesSearched = roleRepo.findAll();
        RolViewModel rvm;
        for (Rol role : rolesSearched) {
            rvm = new RolViewModel(role.getNombre(), role.getDescripcion());
            rvm.setId(role.getId());
            rolViewModel.getSearchResults().add(rvm);
        }
        return "roles/buscar";
    }

    @RequestMapping(value = "/buscar/", method = RequestMethod.POST)
    public String buscarPost(RolViewModel rolViewModel) {
        if (rolViewModel.getId() != null) {
            // buscamos por id
            Optional<Rol> roleSearchedOptional = roleRepo.findById(rolViewModel.getId());
            if (roleSearchedOptional.isPresent()) {
                Rol rolSearched = roleSearchedOptional.get();
                RolViewModel rvm = new RolViewModel(
                        rolSearched.getNombre(),
                        rolSearched.getDescripcion());
                rvm.setId(rolSearched.getId());
                rolViewModel.getSearchResults().add(rvm);
            }
        } else {
            // buscamos todos los roles
            List<Rol> rolesSearched = roleRepo.findAll();
            RolViewModel rvm;
            for (Rol role : rolesSearched) {
                rvm = new RolViewModel(role.getNombre(), role.getDescripcion());
                rvm.setId(role.getId());
                rolViewModel.getSearchResults().add(rvm);
            }
        }
        return "roles/buscar";
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(RolViewModel rolViewModel) {
        Optional<Rol> roleSearchedOptional = roleRepo.findById(rolViewModel.getId());
        if (roleSearchedOptional.isPresent()) {
            roleRepo.delete(roleSearchedOptional.get());
            rolViewModel.setId(null);
            rolViewModel.setNombre(null);
            rolViewModel.setDescripcion(null);
            rolViewModel.messages.add("Rol Eliminado Correctamente");
            return "roles";
        }
        return "roles";
    }
}
