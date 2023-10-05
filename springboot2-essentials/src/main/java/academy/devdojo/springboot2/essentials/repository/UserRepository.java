package academy.devdojo.springboot2.essentials.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import academy.devdojo.springboot2.essentials.domain.AnimeUser;

public interface UserRepository extends JpaRepository<AnimeUser, String>{
	UserDetails findByUsername(String username);
}
