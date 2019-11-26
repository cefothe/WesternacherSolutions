package com.westernacher.solutions.usermanagement.rest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.westernacher.solutions.usermanagement.dto.UserAccountDTO;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerIT {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mvc;

	@Test
	public void createNewUserAccount() throws Exception {
		//given
		var userAccountDTO = new UserAccountDTO("Stefan", "Angelov", "cefothe@gmail.com", LocalDate.of(1994,1,30));

		//when
		mvc.perform(post("/api/v1/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(userAccountDTO)))
				.andExpect(status().isCreated());
	}

	@Test
	public void getAllUserAccounts() throws Exception {
		//given
		var expectedUsers = Arrays.asList(
				new UserAccountDTO("Stefan", "Angelov", "cefothe@gmail.com", LocalDate.of(1994,1,30)),
				new UserAccountDTO("Ivan", "Petkov", "cefothe1@gmail.com", LocalDate.of(1994,1,30)));

		// when
		var response = mvc.perform(get("/api/v1/users"))
				.andExpect(status().isOk())
				.andReturn();

		//then
		var users = objectMapper.readValue(response.getResponse().getContentAsString(),
				new TypeReference<List<UserAccountDTO>>() {});

		assertThat(users, hasSize(2));
		assertThat(users, containsInAnyOrder(expectedUsers));
	}
}
