package academy.devdojo.springboot2.essentials.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import academy.devdojo.springboot2.essentials.domain.AnimeUser;
import academy.devdojo.springboot2.essentials.repository.UserRepository;
import academy.devdojo.springboot2.essentials.requests.AuthenticationRequest;
import academy.devdojo.springboot2.essentials.requests.LoginRequest;
import academy.devdojo.springboot2.essentials.requests.RegisterRequest;
import academy.devdojo.springboot2.essentials.service.TokenService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
		UsernamePasswordAuthenticationToken usernamePassword = 
				new UsernamePasswordAuthenticationToken(authenticationRequest.username(), authenticationRequest.password());
		Authentication auth = this.authenticationManager.authenticate(usernamePassword);
		String token = tokenService.generateToken((AnimeUser) auth.getPrincipal());
		return ResponseEntity.ok(new LoginRequest(token));
	}
	
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody @Valid RegisterRequest registerRequest) {
		if(this.userRepository.findByUsername(registerRequest.username()) != null) {
			return ResponseEntity.badRequest().build();
		}
		String encryptedPassword = new BCryptPasswordEncoder().encode(registerRequest.password());
		AnimeUser newUser = new AnimeUser(registerRequest.username(), encryptedPassword, registerRequest.role());
		this.userRepository.save(newUser);
		return ResponseEntity.ok().build();
	}
}
