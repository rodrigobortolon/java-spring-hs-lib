package br.com.rb.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.rb.entity.Category;

@Repository
@Transactional
public interface CategoryRepository extends CrudRepository<Category, Long> {
	
	List<Category> findByName(String name);

}
