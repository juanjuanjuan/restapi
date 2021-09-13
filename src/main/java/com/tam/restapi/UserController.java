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
class UserController {
	private final UserRepository repository;

//	private final UserModelAssembler assembler;

//	UserController(UserRepository repository, UserModelAssembler assembler) {
//		this.repository = repository;
//		this.assembler = assembler;
//	}
	
	UserController(UserRepository repository) {
		this.repository = repository;
	}


	@GetMapping("/users")
	CollectionModel<EntityModel<User>> all() {

		List<EntityModel<User>> users = repository.findAll().stream()
				.map(user -> EntityModel.of(user, //
						linkTo(methodOn(UserController.class).one(user.getId())).withSelfRel(),
						linkTo(methodOn(UserController.class).all()).withRel("users")))
				.collect(Collectors.toList());

		return CollectionModel.of(users, linkTo(methodOn(UserController.class).all()).withSelfRel());
	}
	
	@GetMapping("/users/{id}")
	EntityModel<User> one(@PathVariable Long id) {

		User user = repository.findById(id)
				.orElseThrow(() -> new UserNotFoundException(id));

		return EntityModel.of(user, //
				linkTo(methodOn(UserController.class).one(user.getId())).withSelfRel(),
				linkTo(methodOn(UserController.class).all()).withRel("users"));
	}

	@PostMapping("/users")
	User newUser(@RequestBody User newUser) {
		return repository.save(newUser);
	}


	@PutMapping("/users/{id}")
	User updateUser(@RequestBody User newUser, @PathVariable Long id) {

		return repository.findById(id)
				.map(user -> {
					user.setName(newUser.getName());
					user.setRol(newUser.getRol());
					return repository.save(user);
				})
				.orElseThrow(() -> new UserNotFoundException(id));
	}

	@DeleteMapping("/users/{id}")
	void deleteUser(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
