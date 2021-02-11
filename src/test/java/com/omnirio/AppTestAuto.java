package com.omnirio;

import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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
				.andExpect(jsonPath("$.User").isArray());
	}


	// get all users
	@Test
	public void userUpdateUser_Error() throws Exception {

		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.post("/omnirio/user")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"roleName\":\"BRANCH_MANAGER\",\n" +
								"    \"branch\":\"A\",\n" +
								"    \"dob\":\"12/12/1988\"\n" +
								"}")
						.accept(MediaType.APPLICATION_JSON)).andReturn();
		JSONObject object = new JSONObject(result.getResponse().getContentAsString());
		String bm_userID = object.getJSONObject("User").getString("userID");

		result = mvc.perform(
				MockMvcRequestBuilders.post("/omnirio/user")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"roleName\":\"CUSTOMER\",\n" +
								"    \"branch\":\"A\",\n" +
								"    \"dob\":\"12/12/1988\"\n" +
								"}")
						.accept(MediaType.APPLICATION_JSON)).andReturn();
		object = new JSONObject(result.getResponse().getContentAsString());
		String customer_user_ID = object.getJSONObject("User").getString("userID");


		mvc.perform(
				MockMvcRequestBuilders.put("/omnirio/user/" + customer_user_ID + "/user")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"userID\":\"" + bm_userID + "\",\n" +
								"    \"branch\":\"D\",\n" +
								"    \"roleName\":\"CUSTOMER\",\n" +
								"    \"userName\":\"FFFFFF\"\n" +
								"}")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest())
				.andExpect(content().string("{\"code\":402,\"details\":\"\",\"message\":\"ID_DOES_NOT_BELONG_TO_BRANCH_MANAGER\"}"));




	}

	// get all users
	@Test
	public void userUpdateUser_Success() throws Exception {

		MvcResult result = mvc.perform(
				MockMvcRequestBuilders.post("/omnirio/user")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"roleName\":\"BRANCH_MANAGER\",\n" +
								"    \"branch\":\"A\",\n" +
								"    \"dob\":\"12/12/1988\"\n" +
								"}")
						.accept(MediaType.APPLICATION_JSON)).andReturn();
		JSONObject object = new JSONObject(result.getResponse().getContentAsString());
		String bm_userID = object.getJSONObject("User").getString("userID");

		result = mvc.perform(
				MockMvcRequestBuilders.post("/omnirio/user")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"roleName\":\"CUSTOMER\",\n" +
								"    \"branch\":\"A\",\n" +
								"    \"dob\":\"12/12/1988\"\n" +
								"}")
						.accept(MediaType.APPLICATION_JSON)).andReturn();
		object = new JSONObject(result.getResponse().getContentAsString());
		String customer_user_ID = object.getJSONObject("User").getString("userID");


		mvc.perform(
				MockMvcRequestBuilders.put("/omnirio/user/" + bm_userID + "/user")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\n" +
								"    \"userID\":\"" + customer_user_ID + "\",\n" +
								"    \"branch\":\"D\",\n" +
								"    \"roleName\":\"CUSTOMER\",\n" +
								"    \"userName\":\"FFFFFF\"\n" +
								"}")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());




	}

}
