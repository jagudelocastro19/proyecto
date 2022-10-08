package com.proyecto.proyecto.controllers;

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
import com.proyecto.proyecto.service.StorageService;

@CrossOrigin
@Controller
@RequestMapping("/files")
public class ArchivosController {

    private final StorageService storageService;

    @Autowired
    public ArchivosController(StorageService storageService) {
        this.storageService = storageService;
    }

    @RequestMapping(value = "/")
    public String index(ArchivosViewModel archivosViewModel) {
        return "archivos";
    }

    @RequestMapping(value = "/crear", method = RequestMethod.GET)
    public String crear(ArchivosViewModel archivosViewModel) {
        return "archivos/crear";
    }

    @PostMapping("/crear")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
            ArchivosViewModel archivosViewModel) {

        storageService.store(file);
        archivosViewModel.messages.add("Archivo Creado Correctamente.");
        return "archivos";
    }

    // @RequestMapping(value = "/crear", method = RequestMethod.POST)
    // public String crearPost(ArchivosViewModel archivosViewModel) {
    // return "archivos/crear";
    // }
}
