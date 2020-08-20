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
import br.com.rb.entity.Category;
import br.com.rb.service.CategoryService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CategoryServiceImplIntegrationTest {
	
    @Autowired
    private CategoryService categoryService;
    
    @Test
    public void test_getAll() { 
           List<Category> categories = categoryService.getAllCategories();
           assertNotNull(categories);
           assertEquals(4, categories.size());
    }
    
    @Test
    public void test_getCategory() { 
           Category category = categoryService.getCategory(1L);
           assertNotNull(category);
           assertEquals(Long.valueOf(1L), category.getId());
           assertEquals("Alimentos", category.getName());
    }
    
    @Test
    public void test_getHighestOccurrenceByLetter() { 
           Category category = categoryService.getHighestOccurrenceByLetter("o");
           assertNotNull(category);
           assertEquals(Long.valueOf(2L), category.getId());
           assertEquals("Eletrodomésticos", category.getName());
    }
}