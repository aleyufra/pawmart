package ar.edu.unju.fi.pawmart.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

	@JsonProperty(value = "uuid")
	private String uuid;
	
	@JsonProperty(value = "first_name")
	@Size(min = 3, max = 50, message = "The first name must be between 3 and 50 characters")
	private String firstName;
	
	@JsonProperty(value = "last_name")
	@Size(min = 3, max = 50, message = "The last name must be between 3 and 50 characters")
	private String lastName;
	
	@Size(min = 4, max = 50, message = "The username must be between 4 and 50 characters")
	@JsonProperty(value = "username")
	private String username;
	
	@ToString.Exclude
	@JsonProperty(value = "password", access = Access.WRITE_ONLY)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@NotBlank(message = "The password must not be blank")
	@Size(min = 4, max = 20, message = "The password must be between 4 and 20 characters")
	private String password;
	
	@JsonProperty(value = "email")
	@Email(message = "Invalid email format")
	@Size(min = 8, max = 50, message = "The email must be between 8 and 50 characters")
	private String email;
	
	@JsonProperty(value = "birthday")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Past(message = "Birthday must be in the past")
	private LocalDate birthday;
	
	@JsonProperty(value = "active", access = Access.READ_ONLY)
	private boolean active;
	
	@JsonProperty(value = "role_name")
	private String roleName;
	
}
