package br.com.rb.service;

import java.util.List;

import br.com.rb.entity.Category;

public interface CategoryService {

	public List<Category> getAllCategories();

	public Category getCategory(Long id);
	
	public Category getHighestOccurrenceByLetter(String letter);

}
