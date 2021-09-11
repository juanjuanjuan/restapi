package com.tam.restapi;

class CustomerNotFoundException extends RuntimeException {

	CustomerNotFoundException(Long id) {
		super("Could not find customer " + id);
	}
}