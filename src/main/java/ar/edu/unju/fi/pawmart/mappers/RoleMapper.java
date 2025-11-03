package ar.edu.unju.fi.pawmart.mappers;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ar.edu.unju.fi.pawmart.dtos.RoleDto;
import ar.edu.unju.fi.pawmart.entities.Role;

@Mapper(componentModel = "spring", uses = { UserMapper.class })
public interface RoleMapper {

    RoleDto toDto(Role role);

    @Mapping(target = "users", ignore = true)
    Role toEntity(RoleDto roleDto);

    List<RoleDto> toDtoList(List<Role> roles);

    List<Role> toEntityList(List<RoleDto> roleDtos);
}