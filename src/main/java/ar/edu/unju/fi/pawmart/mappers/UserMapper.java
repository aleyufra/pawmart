package ar.edu.unju.fi.pawmart.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ar.edu.unju.fi.pawmart.dtos.UserDto;
import ar.edu.unju.fi.pawmart.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
	
	@Mapping(target = "roleName", source = "role.roleName")
	UserDto toDto(User user);
	
	@Mapping(target = "role.roleName", source = "roleName")
	@Mapping(target = "version", ignore = true)
	User toEntity(UserDto userDto);
	
	List<UserDto> toDtoList(List<User> users);
	
	List<User> toEntityList(List<UserDto> userDtos);
	
}
