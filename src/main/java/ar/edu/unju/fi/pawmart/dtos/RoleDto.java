package ar.edu.unju.fi.pawmart.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RoleDto {

	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("role_name")
	private String roleName;
	
	@JsonProperty("users")
	private List<UserDto> users;
	
}
