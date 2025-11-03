package ar.edu.unju.fi.pawmart.services;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.edu.unju.fi.pawmart.dtos.RoleDto;
import ar.edu.unju.fi.pawmart.entities.Role;
import ar.edu.unju.fi.pawmart.enums.RoleName;
import ar.edu.unju.fi.pawmart.mappers.RoleMapper;
import ar.edu.unju.fi.pawmart.repositories.RoleRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class RoleService {

	private final RoleRepository roleRepository;
	private final RoleMapper mapper;

	public RoleService(
			RoleRepository repository,
			RoleMapper roleMapper) {
		this.roleRepository = repository;
		this.mapper = roleMapper;
	}

	// CREATE
	public RoleDto create(final RoleDto roleDto) {
		log.debug("Creating new Role: roleDto={}", roleDto.toString());
		log.info("Creating new Role");
		Role role = mapper.toEntity(roleDto);
		roleRepository.save(role);

		log.info("Role created successfully");
		log.debug("role={}", role.toString());
		return mapper.toDto(role);
	}

	// READ - Get all
	public List<RoleDto> findAll() {
		log.debug("Getting all roles...");
		List<Role> roles = roleRepository.findAll();

		log.info("Roles found");
		return mapper.toDtoList(roles);
	}

	// READ - Get by ID
	public RoleDto findById(final Long id) {
		Role role = roleRepository.findById(id).orElseThrow(() -> {
			log.error("Role not found. ID={}", id);
			return new RuntimeException("Role not found");
		});

		log.debug("Role found. role={}", role.toString());
		return mapper.toDto(role);
	}

	// UPDATE
	public RoleDto update(final Long id, RoleDto updatedRole) {
		log.info("Updating role. ID={}}", id);
		Role role = findEntityById(id);

		role.setRoleName(RoleName.valueOf("ADMIN"));

		role = roleRepository.save(role);

		log.info("Role updated");
		return mapper.toDto(role);
	}

	// DELETE
	public void delete(final Long id) {
		log.info("Deleting role with ID ''", id);
		roleRepository.findById(id);
		log.info("Role deleted");
	}

	// READ - Get Entity by ID
	public Role findEntityById(final Long id) {
		Role role = roleRepository.findById(id).orElseThrow(() -> {
			log.error("Role not found. ID={}", id);
			return new RuntimeException("Role not found");
		});

		return role;
	}
	
	// READ - Get Entity by RoleName
	public Role findEntityByRoleName(final RoleName roleName) {
		Role role = roleRepository.findByRoleName(roleName).orElseThrow(() -> {
			log.error("Role not found. roleName={}", roleName.toString());
			return new RuntimeException("Role not found");
		});

		log.info("Role found successfully");
		return role;
	}

}
