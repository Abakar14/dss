package com.bytmasoft.persistance.repositories;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * 
 * 
 * @author Mahamat Abakar Date 10.12.2019
 */
@NoRepositoryBean
public interface DSSBaseRepository<T, ID> extends PagingAndSortingRepository<T, ID> {
	
	

}
