package com.bytmasoft.persistance.repositories;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.bytmasoft.domain.models.EmailAddress;
import com.bytmasoft.domain.models.Student;


/**
 * 
 * 
 * @author Mahamat Abakar Date 10.12.2019
 */
@NoRepositoryBean
public interface DSSBaseRepository<T, ID> extends PagingAndSortingRepository<T, ID> {
	
	

}
