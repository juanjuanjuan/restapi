package com.tam.restapi;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class CustomerController {

	private final CustomerRepository repository;

	//	private final CustomerModelAssembler assembler;

	CustomerController(CustomerRepository repository) {
		this.repository = repository;
		//		this.assembler = assembler;
	}


	@GetMapping("/customers")
	CollectionModel<EntityModel<Customer>> all() {

		List<EntityModel<Customer>> customers = repository.findAll().stream()
				.map(customer -> EntityModel.of(customer, //
						linkTo(methodOn(CustomerController.class).one(customer.getId())).withSelfRel(),
						linkTo(methodOn(CustomerController.class).all()).withRel("customers")))
				.collect(Collectors.toList());

		return CollectionModel.of(customers, linkTo(methodOn(CustomerController.class).all()).withSelfRel());
	}

	@PostMapping("/customers")
	Customer newCustomer(@RequestBody Customer newCustomer) {
		return repository.save(newCustomer);
	}

	// Single item

	@GetMapping("/customers/{id}")
	EntityModel<Customer> one(@PathVariable Long id) {

		Customer customer = repository.findById(id)
				.orElseThrow(() -> new CustomerNotFoundException(id));

		//		return assembler.toModel(customer);
		return EntityModel.of(customer, //
				linkTo(methodOn(CustomerController.class).one(customer.getId())).withSelfRel(),
				linkTo(methodOn(CustomerController.class).all()).withRel("customers"));
	}

	@PutMapping("/customers/{id}")
	Customer updateCustomer(@RequestBody Customer newCustomer, @PathVariable Long id) {

		return repository.findById(id)
				.map(Customer -> {
					Customer.setName(newCustomer.getName());
					Customer.setSurname(newCustomer.getSurname());
					return repository.save(Customer);
				})
				.orElseThrow(() -> new CustomerNotFoundException(id));
		//				.orElseGet(() -> {
		//					newCustomer.setId(id);
		//					return repository.save(newCustomer);
		//				});
	}

	@DeleteMapping("/customers/{id}")
	void deleteCustomer(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
