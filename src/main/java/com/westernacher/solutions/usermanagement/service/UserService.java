package com.westernacher.solutions.usermanagement.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.persistence.EntityNotFoundException;

import com.westernacher.solutions.usermanagement.domain.User;
import com.westernacher.solutions.usermanagement.dto.FullUserDTO;
import com.westernacher.solutions.usermanagement.dto.UserDTO;
import com.westernacher.solutions.usermanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

/**
 * This service is responsible to handle all CRUD operation related to {@link User}
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {

	private final ModelMapper modelMapper;

	private final UserRepository userRepository;

	/**
	 * Create new {@link User} in our application
	 * @param userDTO {@link UserDTO} information needed for creation of new {@link User}
	 * @return {@link User} that is saved in our database converted to {@link UserDTO}
	 */
	public FullUserDTO createUser(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		user = userRepository.save(user);
		log.info("Created user with id {}", user.getId());
		return modelMapper.map(user, FullUserDTO.class);
	}

	/**
	 * Provide all {@link User}'s in all application
	 * @return {@link List<FullUserDTO>} of our {@link User}'s converted to {@link FullUserDTO}'s
	 */
	public List<FullUserDTO> getAll() {
		log.info("Fetch all users");
		return StreamSupport.stream(userRepository.findAll().spliterator() , false)
				.map(user -> modelMapper.map(user, FullUserDTO.class))
				.collect(Collectors.toList());
	}

	/**
	 * Get {@link User} by its ID
	 * @param userID id of the {@link User}
	 * @return {@link FullUserDTO} corresponding {@link User}
	 */
	public FullUserDTO getById(Long userID) {
		log.info("Get user by id: {}", userID);
		var user = userRepository.findById(userID)
				.orElseThrow(()->new EntityNotFoundException(String.format("User with id %s is not found", userID)));
		return modelMapper.map(user, FullUserDTO.class);
	}

	/**
	 * Update all information for {@link User} by its id
	 * @param userId Id of the {@link User}
	 * @param updatedUser {@link FullUserDTO} new information that will be persisted into database
	 * @return Updated {@link User}
	 */
	public FullUserDTO updateUserByID(Long userId, UserDTO updatedUser) {
		log.info("Start updating user with id: {}", userId);
		var user = userRepository.findById(userId)
				.orElseThrow(()->new EntityNotFoundException(String.format("User with id %s is not found", userId)));
		user.update(modelMapper.map(updatedUser, User.class));
		log.info("Updated user with id: {}", userId);
		return modelMapper.map(userRepository.save(user), FullUserDTO.class);
	}

	/**
	 * Delete user by ID
	 * @param userId Id of the user that we want to delete
	 */
	public void deleteUserByID(Long userId) {
		log.info("Start deleting user with id {}", userId);
		var user = userRepository.findById(userId)
				.orElseThrow(()->new EntityNotFoundException(String.format("User with id %s is not found", userId)));
		userRepository.delete(user);
		log.info("Deleted user with id {}", userId);
	}
}
