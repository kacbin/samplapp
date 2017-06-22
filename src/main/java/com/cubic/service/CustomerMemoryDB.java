package com.cubic.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.cubic.exception.ValidationException;
import com.cubic.vo.CustomerVO;

public class CustomerMemoryDB {

	private final static CustomerMemoryDB instance = new CustomerMemoryDB();

	private Map<Long, CustomerVO> dbMap = new HashMap<Long, CustomerVO>();

	private CustomerMemoryDB() {

	}
//singleton
	public static CustomerMemoryDB get() {
		return instance;
	}

	public CustomerVO addCustomer(final CustomerVO vo) {
		
		validateCustomer(vo);
		
		vo.setPk(System.currentTimeMillis());
		dbMap.put(vo.getPk(), vo);
		return vo;

	}

	public CustomerVO updateCustomer(final CustomerVO vo) {
		validateCustomer(vo);
		if (dbMap.containsKey(vo.getPk())) {
			dbMap.put(vo.getPk(), vo);
		} else {
			throw new IllegalArgumentException("Record not found in database");
		}
		return vo;

	}
	
	public CustomerVO getCustomer(final Long pk){
		return dbMap.get(pk);
		
	}
	public List<CustomerVO> getCustomers(){
		return new ArrayList<CustomerVO>(dbMap.values());
		
	}
	
	public void removeCustomer(final Long pk){
		dbMap.remove(pk);
		
	}
	
	public List<CustomerVO> searchCustomers(final String firstName, final String lastName){

	List<CustomerVO> results = new ArrayList<CustomerVO>();
		for(CustomerVO cust:dbMap.values()){
			if(cust.getFirstName().equalsIgnoreCase(firstName) || cust.getLastName().equalsIgnoreCase(lastName)){
				results.add(cust);
		}
	}
	return results;
}
	private void validateCustomer(final CustomerVO vo){
		if(StringUtils.isEmpty(vo.getFirstName())|| StringUtils.isEmpty(vo.getLastName())){
			throw new ValidationException("Both FirstName and LastName are required !!");
		}
	}
}

