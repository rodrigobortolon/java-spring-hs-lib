package br.com.rb.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.rb.Application;
import br.com.rb.entity.Product;
import br.com.rb.service.ProductService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProductServiceImplIntegrationTest {
	
    @Autowired
    private ProductService productService;
    
    @Test
    public void test_getAll() { 
           List<Product> products = productService.getAllProducts();
           assertNotNull(products);
           assertEquals(11, products.size());
    }
    
    @Test
    public void test_getProduct() { 
           Product product = productService.getProduct(1L);
           assertNotNull(product);
           assertEquals(Long.valueOf(1L), product.getId());
           assertEquals("Arroz", product.getName());
    }
    
    @Test
    public void test_getAllProductsByCategory() { 
           List<Product> products = productService.getAllProductsByCategory(1L);
           assertNotNull(products);
           assertEquals(2, products.size());
           assertEquals(Long.valueOf(1L), products.get(0).getId());
           assertEquals("Arroz", products.get(0).getName());
           assertEquals(Long.valueOf(2L), products.get(1).getId());
           assertEquals("Feijão", products.get(1).getName());
    }
}