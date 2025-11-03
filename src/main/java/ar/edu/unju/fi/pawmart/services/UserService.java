package ar.edu.unju.fi.pawmart.services;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ar.edu.unju.fi.pawmart.dtos.UserDto;
import ar.edu.unju.fi.pawmart.entities.Role;
import ar.edu.unju.fi.pawmart.entities.User;
import ar.edu.unju.fi.pawmart.enums.RoleName;
import ar.edu.unju.fi.pawmart.mappers.UserMapper;
import ar.edu.unju.fi.pawmart.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {

	private final UserRepository userRepository;
	private final RoleService roleService;
	private final PasswordEncoder encoder;
	private final UserMapper userMapper;

	public UserService(
			UserRepository repository,
			RoleService roleService,
			PasswordEncoder encoder,
			UserMapper mapper) {
		this.userRepository = repository;
		this.roleService = roleService;
		this.encoder = encoder;
		this.userMapper = mapper;
	}

	// CREATE
	@Transactional
	public UserDto create(UserDto userDto) {
		log.debug("Creating new user: {}", userDto.toString());
		log.info("Creating new user");
		User user = userMapper.toEntity(userDto);

		RoleName roleNameEnum = user.getRole().getRoleName();
		Role role = roleService.findEntityByRoleName(roleNameEnum);

		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole(role);
		user.setActive(true);

		user = userRepository.save(user);

		log.info("User crated successfully. UUID={}", user.getUuid());
		return userMapper.toDto(user);
	}

	// READ - Get all
	public List<UserDto> findAll() {
		log.debug("Getting all users...");
		List<User> users = userRepository.findAll();

		log.info("Total users: {}", users.size());
		return userMapper.toDtoList(users);
	}

	// READ - Get by ID
	public UserDto findById(UUID uuid) {
		log.info("Getting user by UUID={}", uuid);
		User user = findEntityById(uuid);
		
		log.info("User found successfully");
		return userMapper.toDto(user);
	}

	// UPDATE
	@Transactional
	public UserDto update(UUID uuid, UserDto updatedUser) {
		log.info("Updating user UUID={}", uuid);
		User user = findEntityById(uuid);

		user.setFirstName(updatedUser.getFirstName());
		user.setLastName(updatedUser.getLastName());
		user.setUsername(updatedUser.getUsername());
		user.setEmail(updatedUser.getEmail());
		user.setBirthday(updatedUser.getBirthday());
		user.setActive(updatedUser.isActive());

		if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) {
			user.setPassword(encoder.encode(updatedUser.getPassword()));
		}

		user = userRepository.save(user);

		log.info("User updated successfully. UUID={}", uuid);
		return userMapper.toDto(user);
	}

	// DELETE
	@Transactional
	public void delete(UUID uuid) {
		log.info("Deleting user with UUID={}", uuid);
		userRepository.deleteById(uuid);
		log.info("User deleted successfully");
	}

	// READ - Get Entity by ID
	public User findEntityById(UUID uuid) {
		User user = userRepository.findById(uuid).orElseThrow(() -> { 
			log.error("User not found. UUID={}", uuid);
			return new RuntimeException("User not found");
		});

		return user;
	}

}
