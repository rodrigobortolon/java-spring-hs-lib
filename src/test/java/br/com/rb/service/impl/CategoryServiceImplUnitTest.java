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
import br.com.rb.persistence.CategoryRepository;
import br.com.rb.service.CategoryService;
import br.com.rb.service.HibernateSearchService;

@RunWith(SpringRunner.class)
public class CategoryServiceImplUnitTest {

	@TestConfiguration
	static class CategoryServiceImplTestContextConfiguration {

		@Bean
		public CategoryService CategoryService() {
			return new CategoryServiceImpl();
		}
	}

	@Autowired
	private CategoryService categoryService;

	@MockBean
	private CategoryRepository categoryRepository;

	@MockBean
	private HibernateSearchService hibernateSearchService;

	@Before
	public void setUp() {
		Category category1 = new Category();
		category1.setId(1L);
		category1.setName("Alimentos");
		Category category2 = new Category();
		category2.setId(2L);
		category2.setName("Eletrônicos");
		Category category3 = new Category();
		category3.setId(3L);
		category3.setName("Eletrodomésticos");
		List<Category> categories = new ArrayList<Category>();
		categories.add(category1);
		categories.add(category2);
		categories.add(category3);

		when(categoryRepository.findAll()).thenReturn(categories);
		when(categoryRepository.findOne(1L)).thenReturn(category1);
		when(hibernateSearchService.getHighestOccurrenceByLetter("o", Category.class))
			.thenReturn(category3);
		
	}

	@Test
	public void test_getAllCategoriesTest() {
		List<Category> categories = categoryService.getAllCategories();
		assertEquals("Alimentos", categories.get(0).getName());
		assertEquals("Eletrônicos", categories.get(1).getName());
		assertEquals("Eletrodomésticos", categories.get(2).getName());
	}
	
	@Test
	public void test_getAllCategoriesTestNullArray() {
		when(categoryRepository.findAll()).thenReturn(null);
		List<Category> categories = categoryService.getAllCategories();
		assertNull(categories);
	}

	@Test
	public void test_getAllCategoriesTestEmptyArray() {
		when(categoryRepository.findAll()).thenReturn(new ArrayList<Category>());
		List<Category> categories = categoryService.getAllCategories();
		assertTrue(categories.size() == 0);
	}
	
	@Test
	public void test_getCategoryTest() {
		Category category = categoryService.getCategory(1L);
		assertEquals("Alimentos", category.getName());
	}

	@Test
	public void test_getHighestOccurrenceByLetterTest() {
		Category category = categoryService.getHighestOccurrenceByLetter("o");
		assertEquals("Eletrodomésticos", category.getName());
	}

}
