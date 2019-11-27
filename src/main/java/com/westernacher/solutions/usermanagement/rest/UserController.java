package com.westernacher.solutions.usermanagement.rest;

import java.util.List;

import javax.validation.Valid;

import com.westernacher.solutions.usermanagement.dto.UserDTO;
import com.westernacher.solutions.usermanagement.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private final UserService userService;

	@PostMapping
	public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
		return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<UserDTO>> getAll(){
		return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
	}
}
