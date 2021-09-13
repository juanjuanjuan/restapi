package com.tam.restapi;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private UserController userController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(userController).isNotNull();
	}

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAllUsers() throws Exception {
		this.mockMvc.perform(get("/users"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("userList")));
	}

	@Test
	public void getOneUser() throws Exception {
		this.mockMvc.perform(get("/users/4"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Admin")));
	}

	@Test
	public void addUser() throws Exception {
		this.mockMvc.perform(post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\": \"Three\", \"rol\": \"Operator\"}")
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Operator")));
	}

	@Test
	public void updateUser() throws Exception {
		this.mockMvc.perform(put("/users/3")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\": \"One\", \"rol\": \"Temporal\"}")
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Temporal")));
	}
}