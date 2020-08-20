package br.com.rb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rb.entity.Category;
import br.com.rb.persistence.CategoryRepository;
import br.com.rb.service.CategoryService;
import br.com.rb.service.HibernateSearchService;

@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private HibernateSearchService hibernateSearchService;
	
    @Autowired
    private CategoryRepository categoryRepository;
    
    @Override
    public List<Category> getAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    @Override
    public Category getCategory(Long id) {
   		return categoryRepository.findOne(id);
    }

	@Override
	public Category getHighestOccurrenceByLetter(String letter) {
		return hibernateSearchService.getHighestOccurrenceByLetter(letter, Category.class);
	}

}
