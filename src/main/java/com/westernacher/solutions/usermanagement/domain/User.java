package com.westernacher.solutions.usermanagement.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User extends BaseEntity{
	@NotNull
	@Size(min = 5, max = 255 )
	private  String firstName;

	@NotNull
	@Size(min = 5, max = 255 )
	private  String lastName;

	@Email
	private  String emailAddress;

	private LocalDate dateOfBirth;
}
