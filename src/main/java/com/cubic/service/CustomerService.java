package com.cubic.service;

import java.util.List;

import com.cubic.vo.CustomerVO;

public interface CustomerService {
	CustomerVO create(final CustomerVO vo);

	CustomerVO update(final CustomerVO vo);

	CustomerVO find(final Long id);

	void remove(final Long id);

	List<CustomerVO> getCustomers();

	List<CustomerVO> search(final String firstName, final String lastName);

}
