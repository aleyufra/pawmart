package ar.edu.unju.fi.pawmart.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.edu.unju.fi.pawmart.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{

}
