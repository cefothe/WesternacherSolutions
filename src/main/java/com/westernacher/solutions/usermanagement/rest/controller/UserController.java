package com.westernacher.solutions.usermanagement.rest.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.westernacher.solutions.usermanagement.dto.FullUserDTO;
import com.westernacher.solutions.usermanagement.dto.UserDTO;
import com.westernacher.solutions.usermanagement.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	private final UserService userService;

	@ApiOperation(value = "Create user", notes = "This request method is used for creating new user")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "User is created successfully"),
			@ApiResponse(code = 400, message = "The request body is not correct"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PostMapping
	public ResponseEntity<FullUserDTO> createUser(@Valid @RequestBody UserDTO userDTO){
		return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get all users", notes = "This request method return all of our users")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Return all users"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping
	public ResponseEntity<List<FullUserDTO>> getAll(){
		return new ResponseEntity<>(userService.getAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "Get user by id", notes = "This request method return user by id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Return user"),
			@ApiResponse(code = 404, message = "User not found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUserById(@PathVariable("userId") Long userID){
		return new ResponseEntity<>(userService.getById(userID), HttpStatus.OK);
	}

	@ApiOperation(value = "Update user by id", notes = "This request method update user by id")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Return updated user"),
			@ApiResponse(code = 400, message = "The request body is not correct"),
			@ApiResponse(code = 404, message = "User not found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@PutMapping("/{userId}")
	public ResponseEntity<UserDTO> updateUserById(@PathVariable("userId") @NotNull Long userId, @Valid @RequestBody UserDTO userDTO){
		return new ResponseEntity<>(userService.updateUserByID(userId, userDTO), HttpStatus.OK);
	}

	@ApiOperation(value = "Delete user by id", notes = "This request method delete user by id")
	@ApiResponses(value = {
			@ApiResponse(code = 204, message = "Deleted used"),
			@ApiResponse(code = 404, message = "User not found"),
			@ApiResponse(code = 500, message = "Internal Server Error") })
	@DeleteMapping("/{userId}")
	public ResponseEntity<Void> deleteUserById(@PathVariable("userId") @NotNull Long userId){
		userService.deleteUserByID(userId);
		return ResponseEntity.noContent().build();
	}

}
