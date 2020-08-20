package br.com.rb.service;

import java.util.List;

import br.com.rb.entity.Product;

public interface ProductService {
	public List<Product> getAllProducts();

	public List<Product> getAllProductsByCategory(Long id);

	public Product getProduct(Long id);

}
