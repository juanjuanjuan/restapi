package com.tam.restapi;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class CustomerModelAssembler implements RepresentationModelAssembler<Customer, EntityModel<Customer>> {

	@Override
	public EntityModel<Customer> toModel(Customer employee) {

		return EntityModel.of(employee, //
				linkTo(methodOn(CustomerController.class).one(employee.getId())).withSelfRel(),
				linkTo(methodOn(CustomerController.class).all()).withRel("employees"));
	}
}