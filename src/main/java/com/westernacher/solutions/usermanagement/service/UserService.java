package com.westernacher.solutions.usermanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import com.westernacher.solutions.usermanagement.domain.User;
import com.westernacher.solutions.usermanagement.dto.UserDTO;
import com.westernacher.solutions.usermanagement.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

/**
 * This service is responsible to handle all CRUD operation related to {@link User}
 */
@RequiredArgsConstructor
@Service
public class UserService {

	private final ModelMapper modelMapper;

	private final UserRepository userRepository;

	public UserDTO createUser(UserDTO userDTO) {
		User user = modelMapper.map(userDTO, User.class);
		user = userRepository.save(user);
		return modelMapper.map(user, UserDTO.class);
	}

	public List<UserDTO> getAll() {
		return userRepository.findAll()
				.stream()
				.map(user -> modelMapper.map(user, UserDTO.class))
				.collect(Collectors.toList());
	}
}
