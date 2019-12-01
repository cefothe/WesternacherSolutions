package com.westernacher.solutions.usermanagement.dto;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.validation.annotation.Validated;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
public class UserDTO implements Serializable {
	@NotNull
	@Size(min = 5, max = 255 )
	private  String firstName;

	@NotNull
	@Size(min = 5, max = 255 )
	private  String lastName;

	@Email
	private  String emailAddress;

	private  LocalDate dateOfBirth;
}
