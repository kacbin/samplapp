package com.cubic.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.cubic.service.QueryConstant;

@Entity
@Table(name = "CUSTOMER_NEW")
@NamedQueries({
@NamedQuery(name=QueryConstant.CUSTOMER_SEARCH, query = "select c from CustomerEntity c where UPPER(c.firstName) like UPPER(?) OR UPPER(c.lastName) like UPPER(?)" ),
@NamedQuery(name=QueryConstant.CUSTOMER_ALL, query = "select c from CustomerEntity c")
})
public class CustomerEntity {

	@Id
	@SequenceGenerator(name = "custSeq", sequenceName = "CUSTOMER_SEQ_1", allocationSize = 1)
	@GeneratedValue(generator = "custSeq")
	@Column(name = "CUSTOMER_ID")
	
	private Long pk;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Override
	public String toString() {
		return "CustomerEntity [pk=" + pk + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

	

}
