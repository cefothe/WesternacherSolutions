package com.westernacher.solutions.usermanagement.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class UserAccountDTO {
	private final String firstName;
	private final String lastName;
	private final String emailAddress;
	private final LocalDate dateOfBirth;
}
