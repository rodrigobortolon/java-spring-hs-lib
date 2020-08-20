package br.com.rb.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.rb.entity.Category;
import br.com.rb.entity.Product;
import br.com.rb.persistence.ProductRepository;
import br.com.rb.service.ProductService;

@RunWith(SpringRunner.class)
public class ProductServiceImplTest {

	@TestConfiguration
	static class ProductServiceImplTestContextConfiguration {

		@Bean
		public ProductService ProductService() {
			return new ProductServiceImpl();
		}
	}

	@Autowired
	private ProductService productService;

	@MockBean
	private ProductRepository productRepository;

	@Before
	public void setUp() {
		Category category = new Category();
		category.setId(1L);
		
		Product product1 = new Product();
		product1.setId(1L);
		product1.setName("Arroz");
		product1.setCategory(category);
		Product product2 = new Product();
		product2.setId(2L);
		product2.setName("Feijão");
		product1.setCategory(category);

		List<Product> products = new ArrayList<Product>();
		products.add(product1);
		products.add(product2);

		when(productRepository.findAll()).thenReturn(products);
		when(productRepository.findOne(1L)).thenReturn(product1);
		when(productRepository.findByCategory(category)).thenReturn(products);
		
	}

	@Test
	public void test_getAllProductsTest() {
		List<Product> products = productService.getAllProducts();
		assertEquals("Arroz", products.get(0).getName());
		assertEquals("Feijão", products.get(1).getName());
	}
	
	@Test
	public void test_getAllProductsTestNullArray() {
		when(productRepository.findAll()).thenReturn(null);
		List<Product> products = productService.getAllProducts();
		assertNull(products);
	}

	@Test
	public void test_getAllProductsTestEmptyArray() {
		when(productRepository.findAll()).thenReturn(new ArrayList<Product>());
		List<Product> products = productService.getAllProducts();
		assertTrue(products.size() == 0);
	}
	
	@Test
	public void test_getProductTest() {
		Product Product = productService.getProduct(1L);
		assertEquals("Arroz", Product.getName());
	}
	
	@Test
	public void test_getAllProductsByCategory() {
		List<Product> products = productService.getAllProductsByCategory(1L);
		assertEquals("Arroz", products.get(0).getName());
		assertEquals("Feijão", products.get(1).getName());
	}

}
