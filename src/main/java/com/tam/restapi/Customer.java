package com.tam.restapi;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Customer {

	private @Id @GeneratedValue Long id;
	private String name;
	private String surname;
//	private IMG photo;

	Customer() {}

	Customer(String name, String surname) {

		this.name = name;
		this.surname = surname;
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

//	@Override
//	public boolean equals(Object o) {
//
//		if (this == o)
//			return true;
//		if (!(o instanceof Customer))
//			return false;
//		Customer customer = (Customer) o;
//		return Objects.equals(this.id, customer.id) && Objects.equals(this.name, customer.name)
//				&& Objects.equals(this.surname, customer.surname);
//	}

//	@Override
//	public int hashCode() {
//		return Objects.hash(this.id, this.name, this.surname);
//	}

	@Override
	public String toString() {
		return "Customer{" + "id=" + this.id + ", name='" + this.name + '\'' + ", surname='" + this.surname + '\'' + '}';
	}
}
