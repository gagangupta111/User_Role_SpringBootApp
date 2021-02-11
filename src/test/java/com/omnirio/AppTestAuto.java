package com.omnirio;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class AppTestAuto {

	@Autowired
	private MockMvc mvc;

	// Create User
	@Test
	public void createUser1() throws Exception {

		mvc.perform(
			MockMvcRequestBuilders.post("/omnirio/user")
			.contentType(MediaType.APPLICATION_JSON)
			.content("{\n" +
					"    \"branch\":\"A\",\n" +
					"    \"dob\":\"12/12/1988\"\n" +
					"}")
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.User.dob").value("12/12/1988"));

	}

	@Test
	public void createUser2() throws Exception {

		mvc.perform(
				MockMvcRequestBuilders.post("/omnirio/user")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{}")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.User", hasKey("userID")));
	}

	// get all users
	@Test
	public void getUsers() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/omnirio/user").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.User").isArray())
				.andExpect(jsonPath("$.User", hasSize(2)));
	}

}
