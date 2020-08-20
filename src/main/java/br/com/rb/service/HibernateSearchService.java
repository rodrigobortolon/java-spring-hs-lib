package br.com.rb.service;


import java.util.List;

import javax.persistence.EntityManager;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.rb.entity.Category;

@Service
public class HibernateSearchService {

    @Autowired
    private final EntityManager entityManager;

    @Autowired
    public HibernateSearchService(EntityManager entityManager) {
        super();
        this.entityManager = entityManager;
    }

    public void initializeHibernateSearch() throws Exception {
        try {
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
        	throw new Exception("Error initializing Hibernate Search service", e);
        }
    }

    @Transactional
    public Category getHighestOccurrenceByLetter(String searchTerm, Class<?> clazz) {

	    FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
	    
	    QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(clazz).get();
	    
	    Query query = queryBuilder.keyword().onField("name").matching(searchTerm).createQuery();

	    FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(query, clazz);
	  
	    List<?> categories = null;
    	categories = jpaQuery.getResultList();
    	if (!categories.isEmpty()) {
    		 // results are ordered by relevance so the first item will be the one with highest occurrence
    		return (Category) categories.get(0);
    	}
    	return null;
    }
}
