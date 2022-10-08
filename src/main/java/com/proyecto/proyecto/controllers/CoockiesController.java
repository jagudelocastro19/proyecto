package com.proyecto.proyecto.controllers;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyecto.proyecto.CategoriaRepository;
import com.proyecto.proyecto.RolRepository;
import com.proyecto.proyecto.bean.ArchivosViewModel;
import com.proyecto.proyecto.bean.CoockieViewModel;
import com.proyecto.proyecto.service.StorageService;

@CrossOrigin
@Controller
@RequestMapping("/coockie")
public class CoockiesController {

    @RequestMapping(value = "/")
    public String index(CoockieViewModel coockieViewModel, HttpSession session) {
        Enumeration<String> keys = session.getAttributeNames();
        CoockieViewModel coockie;
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            coockie = new CoockieViewModel(key, (String) session.getAttribute(key));
            coockieViewModel.coockies.add(coockie);
        }
        return "coockies/crear";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String indexPost(CoockieViewModel coockieViewModel, HttpSession session) {
        session.setAttribute(coockieViewModel.getKey(), coockieViewModel.getValue());
        Enumeration<String> keys = session.getAttributeNames();
        CoockieViewModel coockie;
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            coockie = new CoockieViewModel(key, (String) session.getAttribute(key));
            coockieViewModel.coockies.add(coockie);
        }
        return "coockies/crear";
    }
}
