package com.proyecto.proyecto.ems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Configuration;

import com.proyecto.proyecto.*;

@AutoConfiguration
@Configuration
public class UserEntityManager {
    
    @Autowired
    private UsuarioRepository usrRepo;

    public Usuario createUser(Usuario usr) {
        try {
            Usuario result = usrRepo.save(usr);
            if (result.getId() > 0) {
                return result;
            }
            return null;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }

    }

}
