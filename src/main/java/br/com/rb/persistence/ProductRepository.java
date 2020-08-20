package br.com.rb.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.rb.entity.Category;
import br.com.rb.entity.Product;

@Repository
@Transactional
public interface ProductRepository extends CrudRepository<Product, Long> {
	
	List<Product> findByName(String name);

	List<Product> findByCategory(Category category);
}
