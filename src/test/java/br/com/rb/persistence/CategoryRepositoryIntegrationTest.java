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

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(
  locations = "classpath:application-integrationtest.properties")
public class CategoryRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private CategoryRepository categoryRepository;

	@Test
	public void whenFindByName_thenReturnCategory() {
	    // given
	    Category category = new Category();
	    category.setName("Alimentos");
	    entityManager.persist(category);
	    entityManager.flush();
	 
	    // when
	    List<Category> categories = categoryRepository.findByName(category.getName());
	 
	    // then
	    Assert.assertEquals(category.getName(), categories.get(0).getName());
	}

}