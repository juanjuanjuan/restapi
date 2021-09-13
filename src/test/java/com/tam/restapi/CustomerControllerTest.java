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
public class CustomerControllerTest {

	@Autowired
	private CustomerController customerController;

	@Test
	public void contextLoads() throws Exception {
		assertThat(customerController).isNotNull();
	}

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getAllCustomers() throws Exception {
		this.mockMvc.perform(get("/customers"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("customerList")));
	}

	@Test
	public void getOneCustomer() throws Exception {
		this.mockMvc.perform(get("/customers/1"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Bilbo")));
	}

	@Test
	public void addCustomer() throws Exception {
		this.mockMvc.perform(post("/customers")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\": \"Samwise\", \"surname\": \"Gamgee\"}")
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Samwise")));
	}
	
	@Test
	public void updateCustomer() throws Exception {
		this.mockMvc.perform(put("/customers/2")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"name\": \"Gandalf\", \"surname\": \"White\"}")
				)
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Gandalf")));
	}
}