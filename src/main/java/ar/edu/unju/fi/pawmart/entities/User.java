package ar.edu.unju.fi.pawmart.entities;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotBlank;
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
@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID uuid;
	
	@Version
    private Long version;
	
	@NotBlank
	@Column(name = "username", length = 255, nullable = false)
	private String username;
	
	@NotBlank
	@Column(name = "password", length = 255, nullable = false)
	private String password;
	
	@NotBlank
	@Column(name = "first_name", length = 50, nullable = false)
	private String firstName;
	
	@NotBlank
	@Column(name = "last_name", length = 50, nullable = false)
	private String lastName;
	
	@NotBlank
	@Column(name = "email", length = 50, nullable = false, unique = true)
	private String email;
	
	@Column(name = "birthday", nullable = false)
	private LocalDate birthday;
	
	@Builder.Default
	@Column(name = "active", nullable = false)
	private boolean active = true;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id", nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;
	
}
