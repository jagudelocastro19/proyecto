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
public class ProductoCrudTest {
    @Autowired
    ProductoRepository proRepo;

    @Test
    public void createProducto() {
        // Definiciones
        String productName = "pro_name";
        String productBrand = "pro_brand";
        Float productPrice = 10.0f;
        // Procesos
        Producto product = new Producto(productName, productBrand, productPrice);
        Producto productSaved = proRepo.save(product);
        // Pruebas
        assertNotNull(productSaved.getId());
        assertTrue(productSaved.getId() > 0);
        // Logs
        System.out.println(productSaved.toString());
    }

    @Test
    public void findByIdProducto() {
        // Definiciones
        String productName = "pro_name";
        String productBrand = "pro_brand";
        Float productPrice = 10.0f;
        // Procesos
        Producto product = new Producto(productName, productBrand, productPrice);
        Producto productSaved = proRepo.save(product);
        // buscar
        Optional<Producto> productSearched = proRepo.findById(productSaved.getId());
        // Pruebas
        assertNotNull(productSearched.get().getId());
        assertTrue(productSearched.get().getId() > 0);
        // Logs
        System.out.println(productSearched.toString());
    }

    @Test
    public void updateProducto() {
        // Definiciones
        String productName = "pro_name";
        String productBrand = "pro_brand";
        Float productPrice = 10.0f;
        String newProductName = "new_pro_name";
        String newProductBrand = "new_pro_brand";
        Float newProductPrice = 99.0f;
        // Procesos
        // crear
        Producto product = new Producto(productName, productBrand, productPrice);
        Producto productSaved = proRepo.save(product);
        // buscar
        Optional<Producto> productSearchedOptional = proRepo.findById(productSaved.getId());
        Producto productSearched = productSearchedOptional.get();
        // actualizar
        productSearched.setMarca(newProductBrand);
        productSearched.setNombre(newProductName);
        productSearched.setPrecio(newProductPrice);
        Producto productUpdated = proRepo.save(productSearched);
        // Pruebas
        assertTrue(productUpdated.getMarca() == newProductBrand);
        assertTrue(productUpdated.getPrecio() == newProductPrice);
        assertTrue(productUpdated.getNombre() == newProductName);
        // Logs
        System.out.println(productUpdated.toString());
    }

    @Test
    public void deleteProducto() {
        // Definiciones
        String productName = "pro_name";
        String productBrand = "pro_brand";
        Float productPrice = 10.0f;
        // Procesos
        Producto product = new Producto(productName, productBrand, productPrice);
        Producto productSaved = proRepo.save(product);
        Integer productId = productSaved.getId();
        Optional<Producto> productSearchedOptional = proRepo.findById(productId);
        proRepo.deleteById(productSearchedOptional.get().getId());
        // Volver a buscar
        Optional<Producto> productSearchedAfterDelete = proRepo.findById(productId);
        // Pruebas
        assertFalse(productSearchedAfterDelete.isPresent());
        // Logs
        System.out.println(product.toString());
    }

}
