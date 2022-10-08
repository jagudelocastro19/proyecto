package com.proyecto.proyecto.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.proyecto.CategoriaRepository;
import com.proyecto.proyecto.Rol;
import com.proyecto.proyecto.RolRepository;
import com.proyecto.proyecto.Usuario;
import com.proyecto.proyecto.UsuarioRepository;
import com.proyecto.proyecto.bean.UsuarioViewModel;

@CrossOrigin
@Controller
@RequestMapping("/usu")
public class UsuarioController {

    @Autowired
    RolRepository roleRepo;

    @Autowired
    UsuarioRepository userRepo;

    @RequestMapping(value = "/")
    public String index(UsuarioViewModel usuarioViewModel) {
        if (usuarioViewModel.roles.size() == 0) {
            usuarioViewModel.roles = roleRepo.findAll();
        }
        return "usuarios";
    }

    @RequestMapping(value = "/crear/")
    public String create(UsuarioViewModel usuarioViewModel) {
        if (usuarioViewModel.roles.size() == 0) {
            usuarioViewModel.roles = roleRepo.findAll();
        }
        return "users/crear";
    }

    @RequestMapping(value = "/crear/", method = RequestMethod.POST)
    public String createPost(UsuarioViewModel usuarioViewModel) {
        if (usuarioViewModel.roles.size() == 0) {
            usuarioViewModel.roles = roleRepo.findAll();
        }

        Optional<Rol> rolSearchedOptional = roleRepo.findById(usuarioViewModel.getRole().getId());
        Rol rolSearched = rolSearchedOptional.get();

        Usuario usrToSaved = new Usuario(
                usuarioViewModel.getUsername(),
                usuarioViewModel.getPassword(),
                rolSearched, new Date());
        Usuario usrSaved = userRepo.save(usrToSaved);
        Boolean validation = usrSaved.getId() != null && usrSaved.getId() > 0;
        if (validation) {
            usuarioViewModel.messages.add("Usuario creado correctamente.");
            return "usuarios";
        } else {
            usuarioViewModel.messages.add("Por favor ingrese datos validos para la creacion del usuario.");
            return "users/crear";
        }
    }

    @RequestMapping(value = "/actualizar/{id}")
    public String actualizar(UsuarioViewModel usuarioViewModel) {
        if (usuarioViewModel.roles.size() == 0) {
            usuarioViewModel.roles = roleRepo.findAll();
        }
        Optional<Usuario> usrSearchedOptional = userRepo.findById(usuarioViewModel.getId());
        if (usrSearchedOptional.isPresent()) {
            Usuario usrSearched = usrSearchedOptional.get();
            usuarioViewModel.setPassword(usrSearched.getPassword());
            usuarioViewModel.setUsername(usrSearched.getUsername());
            usuarioViewModel.setRole(usrSearched.getRole());
            return "users/actualizar";
        } else {
            usuarioViewModel.messages.add("El usuario no existe por favor ingrese un ID valido.");
            return "usuarios";
        }
    }

    @RequestMapping(value = "/actualizar/", method = RequestMethod.POST)
    public String actualizarPost(UsuarioViewModel usuarioViewModel) {
        if (usuarioViewModel.roles.size() == 0) {
            usuarioViewModel.roles = roleRepo.findAll();
        }
        Optional<Usuario> usrSearchedOptional = userRepo.findById(usuarioViewModel.getId());
        if (usrSearchedOptional.isPresent()) {
            Usuario usrSearched = usrSearchedOptional.get();
            Optional<Rol> rolSearchedOptional = roleRepo.findById(usuarioViewModel.getRole().getId());
            if(rolSearchedOptional.isPresent()){
                usrSearched.setPassword(usuarioViewModel.getPassword());
                usrSearched.setRole(rolSearchedOptional.get());
                usrSearched.setUsername(usuarioViewModel.getUsername());
                Usuario usrUpdated = userRepo.save(usrSearched);
                if(usrUpdated != null){
                    usuarioViewModel.messages.add("El usuario se actualizo correctamente.");
                    return "usuarios";
                }else{
                    usuarioViewModel.messages.add("Error actualizando el usuario, intente nuevamente.");
                    return "users/actualizar";
                }
            }else{
                usuarioViewModel.messages.add("Por favor ingrese un rol valido.");
                return "users/actualizar";
            }
        } else {
            usuarioViewModel.messages.add("El usuario no existe por favor ingrese un ID valido.");
            return "usuarios";
        }
    }

    @RequestMapping(value = "/eliminar/{id}")
    public String eliminar(UsuarioViewModel usuarioViewModel) {
        if (usuarioViewModel.roles.size() == 0) {
            usuarioViewModel.roles = roleRepo.findAll();
        }
        Optional<Usuario> usrSearchedOptional = userRepo.findById(usuarioViewModel.getId());
        if (usrSearchedOptional.isPresent()) {
            Usuario usrSearched = usrSearchedOptional.get();
            usuarioViewModel.setPassword(usrSearched.getPassword());
            usuarioViewModel.setUsername(usrSearched.getUsername());
            usuarioViewModel.setRole(usrSearched.getRole());
            return "users/eliminar";
        } else {
            usuarioViewModel.messages.add("El usuario no existe por favor ingrese un ID valido.");
            return "usuarios";
        }
    }

    @RequestMapping(value = "/eliminar/")
    public String eliminarPost(UsuarioViewModel usuarioViewModel) {
        if (usuarioViewModel.roles.size() == 0) {
            usuarioViewModel.roles = roleRepo.findAll();
        }
        Optional<Usuario> usrSearchedOptional = userRepo.findById(usuarioViewModel.getId());
        if (usrSearchedOptional.isPresent()) {
            Usuario usrSearched = usrSearchedOptional.get();
            userRepo.delete(usrSearched);
            usuarioViewModel.messages.add("El usuario fue eliminado correctamente.");
            return "usuarios";
        } else {
            usuarioViewModel.messages.add("El usuario no existe por favor ingrese un ID valido.");
            return "users/eliminar";
        }
    }

    @RequestMapping(value = "/buscar/")
    public String buscar(UsuarioViewModel usuarioViewModel) {
        if (usuarioViewModel.roles.size() == 0) {
            usuarioViewModel.roles = roleRepo.findAll();
        }
        usuarioViewModel.setSearchResults(
                userRepo.findAll());
        return "users/buscar";
    }

    @RequestMapping(value = "/buscar/{id}")
    public String buscarById(UsuarioViewModel usuarioViewModel) {
        if (usuarioViewModel.roles.size() == 0) {
            usuarioViewModel.roles = roleRepo.findAll();
        }
        Optional<Usuario> usrSearchedOptional = userRepo.findById(usuarioViewModel.getId());
        if (usrSearchedOptional.isPresent()) {
            Usuario usrSearched = usrSearchedOptional.get();
            List<Usuario> searched = new ArrayList<>();
            searched.add(usrSearched);
            usuarioViewModel.setSearchResults(searched);
            return "users/buscar";
        } else {
            usuarioViewModel.messages.add("El usuario no existe por favor ingrese un ID valido.");
            return "users/buscar";
        }
    }

    @RequestMapping(value = "/buscar/", method = RequestMethod.POST)
    public String buscarPost(UsuarioViewModel usuarioViewModel) {
        if (usuarioViewModel.roles.size() == 0) {
            usuarioViewModel.roles = roleRepo.findAll();
        }
        if (usuarioViewModel.getId() != null) {
            Optional<Usuario> usrSearchedOptional = userRepo.findById(usuarioViewModel.getId());
            if (usrSearchedOptional.isPresent()) {
                Usuario usrSearched = usrSearchedOptional.get();
                List<Usuario> searched = new ArrayList<>();
                searched.add(usrSearched);
                usuarioViewModel.setSearchResults(searched);
                return "users/buscar";
            } else {
                usuarioViewModel.messages.add("El usuario no existe por favor ingrese un ID valido.");
                return "users/buscar";
            }
        } else {
            usuarioViewModel.setSearchResults(
                    userRepo.findAll());
            return "users/buscar";
        }
    }
}
