package com.westernacher.solutions.usermanagement.rest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.westernacher.solutions.usermanagement.domain.User;
import com.westernacher.solutions.usermanagement.dto.UserDTO;
import com.westernacher.solutions.usermanagement.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
@SpringBootTest
public class UserControllerIT {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private MockMvc mvc;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void createNewUserAccount() throws Exception {
		//given
		var userAccountDTO = new UserDTO("Stefan", "Angelov", "cefothe@gmail.com", LocalDate.of(1994,1,30));

		//when
		mvc.perform(post("/api/v1/users")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userAccountDTO)))
				.andExpect(status().isCreated());
	}

	@Test
	public void getAllUserAccounts() throws Exception {
		//given
		var expectedUsers = Arrays.asList(
				new UserDTO("Stefan", "Angelov", "cefothe@gmail.com", LocalDate.of(1994,1,30)),
				new UserDTO("Georgie", "Petkov", "cefothe1@gmail.com", LocalDate.of(1994,1,30)));

		expectedUsers.forEach(userDTO -> userRepository.save(modelMapper.map(userDTO, User.class)));
		// when
		var response = mvc.perform(get("/api/v1/users"))
				.andExpect(status().isOk())
				.andReturn();

		//then
		var users = objectMapper.readValue(response.getResponse().getContentAsString(),
				new TypeReference<List<UserDTO>>() {});

		assertThat(users, hasSize(2));
		assertEquals(users, expectedUsers);
	}

	@Test
	public void updateUserById() throws Exception {
		//given
		var userInDatabase = new UserDTO("Georgie", "Petkov", "cefothe1@gmail.com", LocalDate.of(1994,1,30));
		userRepository.save(modelMapper.map(userInDatabase, User.class));

		var newUserInformation = new UserDTO("Stefan", "Angelov", "cefothe@gmail.com", LocalDate.of(1994,1,30));

		//when
		var response = mvc.perform(put("/api/v1/users/{userId}", 1)
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsBytes(newUserInformation)))
				.andExpect(status().isOk())
				.andReturn();

		//then
		var user =  objectMapper.readValue(response.getResponse().getContentAsString(), UserDTO.class);
		assertThat(user, equalTo(newUserInformation));
	}

	@Test
	public void getUserById() throws Exception {
		//given
		var userInDatabase = new UserDTO("Stefan", "Angelov", "cefothe@gmail.com", LocalDate.of(1994,1,30));
		var user = modelMapper.map(userInDatabase, User.class);
		userRepository.save(user);

		//when
		var response = mvc.perform(get("/api/v1/users/{userId}",1))
				.andExpect(status().isOk())
				.andReturn();

		//then
		var userResponse =  objectMapper.readValue(response.getResponse().getContentAsString(), UserDTO.class);
		assertThat(userResponse, equalTo(userInDatabase));
	}

	@Test
	public void getUserByIdThatNotExist() throws Exception {

		//when
		var response = mvc.perform(delete("/api/v1/users/{userId}",100))
				.andExpect(status().isNotFound())
				.andReturn();
	}

	@Test
	public void deleteUserById() throws Exception {
		//given
		var userInDatabase = new UserDTO("Stefan", "Angelov", "cefothe@gmail.com", LocalDate.of(1994,1,30));
		var user = modelMapper.map(userInDatabase, User.class);
		userRepository.save(user);

		//when
		var response = mvc.perform(delete("/api/v1/users/{userId}",1))
				.andExpect(status().isNoContent())
				.andReturn();

		//then
		assertFalse(userRepository.findById(1L).isPresent());

	}

}
