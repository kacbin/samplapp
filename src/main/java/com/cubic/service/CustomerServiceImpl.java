package com.cubic.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cubic.entity.CustomerEntity;
import com.cubic.exception.ValidationException;
import com.cubic.vo.CustomerVO;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private CustomerMapper mapper;

	public CustomerVO create(CustomerVO vo) {
		validateCustomer(vo);
		CustomerEntity entity = null;
		if (vo.getPk() == null) {
			entity = mapper.mapToCustomerEntity(vo);
		} else {

			entity = mapper.mapToCustomerEntity(entity, vo);
		}
		em.persist(entity);
		vo.setPk(entity.getPk());
		return vo;
	}
	// public List<CustomerVO> search(String name) {
	//
	//// if(StringUtils.isEmpty(name) || name.trim().length() < 4) {
	//// throw new InvalidSearchCriteriaException("Search should have atleast 3
	// characters");
	//// }
	//
	// List<CustomerEntity> entities;
	//
	// String queryParam = name.trim()+"%";
	// TypedQuery<CustomerEntity> query =
	// em.createNamedQuery("CustomerEntity.Search", CustomerEntity.class);
	// query.setParameter(1, queryParam);
	// query.setParameter(2, queryParam);
	// entities = query.getResultList();
	//
	// return mapper.mapToCustomerVOList(entities);
	// }

	public CustomerVO update(final CustomerVO vo) {
		validateCustomer(vo);
		CustomerEntity entity = em.find(CustomerEntity.class, vo.getPk());
		entity = mapper.mapToCustomerEntity(entity, vo);
		em.persist(entity);
		return vo;
	}

	public CustomerVO find(Long id) {
		CustomerEntity entity = em.find(CustomerEntity.class, id);
		return mapper.mapToCustomerVO(entity);
	}

	public void remove(Long pk) {
		CustomerEntity entity = em.find(CustomerEntity.class, pk);
		em.remove(entity);
	}

	public List<CustomerVO> getCustomers() {
		TypedQuery<CustomerEntity> query = em.createNamedQuery(QueryConstant.CUSTOMER_ALL, CustomerEntity.class);

		List<CustomerEntity> entityList = query.getResultList();
		return mapper.mapToCustomerVOList(entityList);
	}

	public List<CustomerVO> search(final String firstName, final String lastName) {
		String fName = StringUtils.isEmpty(firstName) ? "%" : firstName.trim() + "%";
		String lName = StringUtils.isEmpty(lastName) ? "%" : lastName.trim() + "%";

		TypedQuery<CustomerEntity> query = em.createNamedQuery(QueryConstant.CUSTOMER_SEARCH, CustomerEntity.class);
		query.setParameter(1, fName);
		query.setParameter(2, lName);
		List<CustomerEntity> entityList = query.getResultList();
		return mapper.mapToCustomerVOList(entityList);
	}
	private void validateCustomer(final CustomerVO vo){
		if(StringUtils.isEmpty(vo.getFirstName())|| StringUtils.isEmpty(vo.getLastName())){
			throw new ValidationException("Both FirstName and LastName are required !!");
		}
	}

}
