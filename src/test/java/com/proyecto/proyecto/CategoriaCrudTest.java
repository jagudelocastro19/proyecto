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
public class CategoriaCrudTest {
    @Autowired
    CategoriaRepository catRepo;

    @Test
    public void createCategoria() {
        // Definiciones
        String categoriaName = "categoria_name";
        String categoriaDescription = "categoria_description";
        // Procesos
        Categoria categoria = new Categoria(categoriaName, categoriaDescription);
        Categoria categoriaSaved = catRepo.save(categoria);
        // Pruebas
        assertNotNull(categoriaSaved.getId());
        assertTrue(categoriaSaved.getId() > 0);
        // Logs
        System.out.println(categoriaSaved.toString());
    }

    @Test
    public void updateCategoria() {
        // Definiciones
        String categoriaName = "categoria_name";
        String categoriaDescription = "categoria_description";
        String newCategoriaName = "new_categoria_name";
        String newCategoriaDescription = "new_categoria_description";
        // Procesos
        // Crear
        Categoria categoria = new Categoria(categoriaName, categoriaDescription);
        Categoria categoriaSaved = catRepo.save(categoria);
        // Buscar ese registro
        Optional<Categoria> categoriaSearchedOptional = catRepo.findById(categoriaSaved.getId());
        Categoria categoriaSearched = categoriaSearchedOptional.get();
        // actualizar registro
        categoriaSearched.setDescripcion(newCategoriaDescription);
        categoriaSearched.setNombre(newCategoriaName);
        Categoria categoriaUpdated = catRepo.save(categoriaSearched);
        // Pruebas
        assertNotNull(categoriaUpdated.getId());
        assertTrue(categoriaUpdated.getId() > 0);
        assertTrue(categoriaUpdated.getNombre() == newCategoriaName);
        assertTrue(categoriaUpdated.getDescripcion() == newCategoriaDescription);
        // Logs
        System.out.println(categoriaUpdated.toString());
    }

    @Test
    public void deleteCategoria() {
        // Definiciones
        String categoriaName = "categoria_name";
        String categoriaDescription = "categoria_description";
        // Procesos
        // Crear
        Categoria categoria = new Categoria(categoriaName, categoriaDescription);
        Categoria categoriaSaved = catRepo.save(categoria);
        // Buscar ese registro
        Optional<Categoria> categoriaSearchedOptional = catRepo.findById(categoriaSaved.getId());
        Categoria categoriaSearched = categoriaSearchedOptional.get();
        // eliminarlo registro
        Integer categoriaId = categoriaSaved.getId();
        catRepo.deleteById(categoriaSaved.getId());
        // Volver a buscarlo
        Optional<Categoria> categoriaDeletedOptional = catRepo.findById(categoriaId);
        // Pruebas
        assertFalse(categoriaDeletedOptional.isPresent());
        // Logs
        System.out.println(categoriaSaved.toString());
        System.out.println(categoriaSearched.toString());
    }

    @Test
    public void findAllCategorias() {
        // Definiciones
        String categoriaName1 = "categoria_name1";
        String categoriaDescription1 = "categoria_description1";
        String categoriaName2 = "categoria_name2";
        String categoriaDescription2 = "categoria_description2";
        String categoriaName3 = "categoria_name3";
        String categoriaDescription3 = "categoria_description3";
        // Procesos
        // Crear 3
        Categoria categoria1 = new Categoria(categoriaName1, categoriaDescription1);
        Categoria categoria2 = new Categoria(categoriaName2, categoriaDescription2);
        Categoria categoria3 = new Categoria(categoriaName3, categoriaDescription3);
        List<Categoria> listToSaved = new ArrayList<>();
        listToSaved.add(categoria1);
        listToSaved.add(categoria2);
        listToSaved.add(categoria3);
        catRepo.saveAll(listToSaved);
        // Buscar todos los registros
        List<Categoria> listSearched = catRepo.findAll();
        // Pruebas
        assertTrue(listSearched.size() > 0);
        // Logs
        listSearched.forEach(
                categoria -> {
                    System.out.println(categoria.toString());
                });
    }

    @Test
    public void findOneCategoria() {
        // Definiciones
        String categoriaName = "categoria_name";
        String categoriaDescription = "categoria_description";
        // Procesos
        // Crear
        Categoria categoria = new Categoria(categoriaName, categoriaDescription);
        Categoria categoriaSaved = catRepo.save(categoria);
        // buscar
        Optional<Categoria> categoriaSearchedOptional = catRepo.findById(categoriaSaved.getId());
        // Pruebas
        assertTrue(categoriaSearchedOptional.isPresent());
        // Logs
        System.out.println(categoriaSearchedOptional.get().toString());
    }
}
