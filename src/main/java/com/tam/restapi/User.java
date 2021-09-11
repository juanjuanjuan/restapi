package com.tam.restapi;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

	private @Id @GeneratedValue Long id;
	private String name;
	private String rol;

	User() {}

	User(String name, String rol) {
		this.name = name;
		this.rol = rol;
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public String getRol() {
		return this.rol;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + this.id + ", name='" + this.name + '\'' + ", rol='" + this.rol + '\'' + '}';
	}
}
