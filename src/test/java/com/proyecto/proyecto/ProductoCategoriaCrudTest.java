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
public class ProductoCategoriaCrudTest {
    @Autowired
    ProductoRepository proRepo;

    @Autowired
    CategoriaRepository catRepo;

    @Test
    public void createCategoriesForProducts() {
        // Definiciones
        String productName = "pro_name";
        String productBrand = "pro_brand";
        Float productPrice = 10.0f;
        String catName1 = "cat_pro_1";
        String catDescription1 = "cat_pro_description_1";
        String catName2 = "cat_pro_2";
        String catDescription2 = "cat_pro_description_2";
        // Procesos
        Producto product = new Producto(productName, productBrand, productPrice);
        Producto productSaved = proRepo.save(product);
        Categoria cat1 = new Categoria(catName1, catDescription1);
        Categoria cat2 = new Categoria(catName2, catDescription2);
        Categoria cat1Saved = catRepo.save(cat1);
        Categoria cat2Saved = catRepo.save(cat2);
        productSaved.addCategory(cat1Saved);
        productSaved.addCategory(cat2Saved);
        // test
        proRepo.flush();
        Optional<Producto> productSearchedOptional = proRepo.findById(productSaved.getId());
        Producto productSearched = productSearchedOptional.get();
        assertTrue(productSearched.getCategories().size() == 2);
        // log
        System.out.println(productSearched.toString());
    }

    @Test
    public void createProductsForCategories() {
        // Definiciones
        String productName = "pro_name";
        String productBrand = "pro_brand";
        Float productPrice = 10.0f;
        String productName2 = "pro_name_2";
        String productBrand2 = "pro_brand_2";
        Float productPrice2 = 20.0f;
        String catName1 = "cat_pro_1";
        String catDescription1 = "cat_pro_description_1";
        // Procesos
        Producto product = new Producto(productName, productBrand, productPrice);
        Producto productSaved = proRepo.save(product);
        Producto product2 = new Producto(productName2, productBrand2, productPrice2);
        Producto productSaved2 = proRepo.save(product2);
        Categoria cat1 = new Categoria(catName1, catDescription1);
        Categoria cat1Saved = catRepo.save(cat1);
        cat1Saved.addProduct(productSaved);
        cat1Saved.addProduct(productSaved2);
        // test
        proRepo.flush();
        Optional<Categoria> categorySearchedOptional = catRepo.findById(cat1Saved.getId());
        Categoria categorySearched = categorySearchedOptional.get();
        assertTrue(categorySearched.getProducts().size() == 2);
        // log
        System.out.println(categorySearched.toString());
    }

    @Test
    public void deleteCategoriesForProducts() {

        // Definiciones
        String productName = "pro_name";
        String productBrand = "pro_brand";
        Float productPrice = 10.0f;
        String catName1 = "cat_pro_1";
        String catDescription1 = "cat_pro_description_1";
        String catName2 = "cat_pro_2";
        String catDescription2 = "cat_pro_description_2";
        // Procesos
        Producto product = new Producto(productName, productBrand, productPrice);
        Producto productSaved = proRepo.save(product);
        Categoria cat1 = new Categoria(catName1, catDescription1);
        Categoria cat2 = new Categoria(catName2, catDescription2);
        Categoria cat1Saved = catRepo.save(cat1);
        Categoria cat2Saved = catRepo.save(cat2);
        productSaved.addCategory(cat1Saved);
        productSaved.addCategory(cat2Saved);
        // test
        proRepo.flush();
        Optional<Producto> productSearchedOptional = proRepo.findById(productSaved.getId());
        Producto productSearched = productSearchedOptional.get();
        Integer categoriesCount = productSearched.getCategories().size();
        for (int i = 1; i <= categoriesCount; i++) {
            productSearched.removeCategory(
                    productSearched.getCategories().get(categoriesCount - i));
        }
        catRepo.flush();
        proRepo.flush();
        Optional<Producto> productSearchedAfterRemoveOptional = proRepo.findById(productSaved.getId());
        Producto productSearchedAfterRemove = productSearchedAfterRemoveOptional.get();
        assertTrue(productSearchedAfterRemove.getCategories().size() == 0);
        // log
        System.out.println(productSearched.toString());
    }

    @Test
    public void deleteProductsForCategories() {
        // Definiciones
        String productName = "pro_name";
        String productBrand = "pro_brand";
        Float productPrice = 10.0f;
        String productName2 = "pro_name_2";
        String productBrand2 = "pro_brand_2";
        Float productPrice2 = 20.0f;
        String catName1 = "cat_pro_1";
        String catDescription1 = "cat_pro_description_1";
        // Procesos
        Producto product = new Producto(productName, productBrand, productPrice);
        Producto productSaved = proRepo.save(product);
        Producto product2 = new Producto(productName2, productBrand2, productPrice2);
        Producto productSaved2 = proRepo.save(product2);
        Categoria cat1 = new Categoria(catName1, catDescription1);
        Categoria cat1Saved = catRepo.save(cat1);
        cat1Saved.addProduct(productSaved);
        cat1Saved.addProduct(productSaved2);
        // test
        proRepo.flush();
        Optional<Categoria> categoriaSearchedOptional = catRepo.findById(cat1Saved.getId());
        Categoria categoriaSearched = categoriaSearchedOptional.get();
        Integer productCount = categoriaSearched.getProducts().size();
        for (int i = 1; i <= productCount; i++) {
            categoriaSearched.removeProduct(
                    categoriaSearched.getProducts().get(productCount - i));
        }
        catRepo.flush();
        proRepo.flush();
        Optional<Categoria> categoriaSearchedAfterRemoveOptional = catRepo.findById(cat1Saved.getId());
        Categoria categoriaSearchedAfterRemove = categoriaSearchedAfterRemoveOptional.get();
        assertTrue(categoriaSearchedAfterRemove.getProducts().size() == 0);
        // log
        System.out.println(categoriaSearchedAfterRemove.toString());
    }

}
