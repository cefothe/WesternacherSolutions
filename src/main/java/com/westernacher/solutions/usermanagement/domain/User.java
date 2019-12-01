package com.westernacher.solutions.usermanagement.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
@Data
@Entity
public class User extends BaseEntity{
	@NotNull
	@Size(min = 3, max = 255 )
	private  String firstName;

	@NotNull
	@Size(min = 3, max = 255 )
	private  String lastName;

	@Email
	private  String emailAddress;

	private LocalDate dateOfBirth;

	/**
	 * This method is responsible to update the {@link User} with new information
	 * @param updated {@link User} user object that contains all information without databaseId
	 */
	public void update(User updated) {
		this.firstName = updated.firstName;
		this.lastName = updated.lastName;
		this.emailAddress = updated.emailAddress;
		this.dateOfBirth = updated.dateOfBirth;
	}
}
