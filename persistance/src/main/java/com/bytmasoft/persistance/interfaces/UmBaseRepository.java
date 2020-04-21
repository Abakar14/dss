package com.bytmasoft.persistance.interfaces;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * 
 * 
 * @author Mahamat Abakar Date 10.12.2019
 */
@NoRepositoryBean
public interface UmBaseRepository<T, ID> extends PagingAndSortingRepository<T, ID> {
	
	

}
