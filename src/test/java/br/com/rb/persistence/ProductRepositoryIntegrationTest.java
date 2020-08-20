package br.com.rb.persistence;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.rb.entity.Category;
import br.com.rb.entity.Product;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(
  locations = "classpath:application-integrationtest.properties")
public class ProductRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void whenFindByName_thenReturnTheListOfProducts() {
	    // given
		Category category = new Category();
		category.setId(1L);
		category.setName("Alimentos");
	    Product product = new Product();
	    product.setName("Arroz");
	    product.setCategory(category);
	    
	    entityManager.persist(product);
	    entityManager.flush();
	 
	    // when
	    List<Product> products = productRepository.findByName(product.getName());
	 
	    // then
	    Assert.assertEquals(product.getName(), products.get(0).getName());
	    Assert.assertEquals(product.getCategory().getName(), products.get(0).getCategory().getName());
	}
	
	@Test
	public void whenFindByCategory_thenReturnTheListOfProducts() {
	    // given
		Category category = new Category();
		category.setId(1L);
		category.setName("Alimentos");
		Product product1 = new Product();
	    product1.setName("Arroz");
	    product1.setCategory(category);
	    Product product2 = new Product();
	    product2.setName("Feijão");
	    product2.setCategory(category);

	    entityManager.persist(product1);
	    entityManager.flush();
	 
	    // when
	    List<Product> products1 = productRepository.findByCategory(category);
	 
	    // then
	    Assert.assertEquals(product1.getName(), products1.get(0).getName());
	    Assert.assertEquals(product1.getCategory().getName(), products1.get(0).getCategory().getName());
	    
	    entityManager.persist(product2);
	    entityManager.flush();
	 
	    // when
	    List<Product> products2 = productRepository.findByCategory(category);

	    // then
	    Assert.assertEquals(product2.getName(), products2.get(1).getName());
	    Assert.assertEquals(product2.getCategory().getName(), products2.get(1).getCategory().getName());
	}

}