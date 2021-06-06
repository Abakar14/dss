package com.bytmasoft.common.interfaces;

import java.io.Serializable;
import java.util.List;

public interface IOperations<T extends Serializable> {

	/**
	 * 
	 * @param id
	 * @return one element
	 */
	T findOne(final Long id);

	/**
	 * 
	 * @param status
	 * @return a list of T
	 */
	List<T> findByStatus(String status);

	/**
	 * 
	 * @return a list of all active element
	 */
	List<T> findAllResources();

	/**
	 * 
	 * @return a list of all active element
	 */
	List<T> findAllInActive();

	/**
	 * 
	 * @return a list of all elements that remarked for delete
	 */
	List<T> findAllRemarkedForDelete();

	/**
	 * 
	 * @return all passive elements if not then return empty list
	 */
	List<T> findAllActive();

	/**
	 * 
	 * @param page
	 * @param size
	 * @param sortBy
	 * @param sortOrder
	 * @return list of all paginated and sorted element if not found return empty
	 *         list
	 */
	List<T> findAllPaginatedAndSorted(final int page, final int size, final String sortBy, final String sortOrder);

	/**
	 * 
	 * @param resource
	 * @return
	 */
	T create(final T resource);

	/**
	 * 
	 * @param resource
	 */
	void update(final T resource);

	/**
	 * 
	 * @param id for that element to delete
	 */
	void deleteById(final Long id);

	/**
	 * delete all elements
	 */
	void deleteAll();

	void deleteAllInActiveResources();

	/**
	 * 
	 * @return number of elements
	 */
	long count();

	/**
	 * 
	 * @return number of elements
	 */
	int countActiveResources();

	/**
	 * 
	 * @return number of elements
	 */
	int countInActiveResources();

	/**
	 * 
	 * @param id
	 */
	void activateById(final long id);

	/**
	 * 
	 * @param id
	 */
	void deactivateById(final long id);

	/**
	 * 
	 */
	void activateAll();

	/**
	 * 
	 */
	void deactivateAll();

	/**
	 * 
	 * @param user_id
	 */
	void remarkForDelete(Long id);

	void remarkByStatusForDelete(String status);

}
