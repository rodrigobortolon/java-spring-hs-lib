package br.com.rb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rb.entity.Category;
import br.com.rb.entity.Product;
import br.com.rb.persistence.ProductRepository;
import br.com.rb.service.ProductService;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts() {
        return (List<Product>) productRepository.findAll();
    }
    
	@Override
	public List<Product> getAllProductsByCategory(Long id) {
		Category category = new Category();
		category.setId(id);
        return productRepository.findByCategory(category);
	}

    @Override
    public Product getProduct(Long id) {
    	return productRepository.findOne(id);
    }

}

