package com.erenturhan.service;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.erenturhan.dto.LoginRequest;
import com.erenturhan.dto.LoginResponse;
import com.erenturhan.dto.RegisterRequest;
import com.erenturhan.entity.User;
import com.erenturhan.repository.UserRepository;

@Service
public class AuthenticationService {
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	public LoginResponse register(RegisterRequest request) {
		
		User user = new User();
		user.setUsername(request.getUsername());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setRole(request.getRole() != null ? request.getRole() : "USER");
		
		userRepository.save(user);
		
		String jwtToken = jwtService.generateToken(user.getUsername(), user.getRole());
		
		return new LoginResponse(jwtToken, user.getUsername(), user.getRole());
		
	}
	public LoginResponse login(LoginRequest request) {
		User user = userRepository.findByUsername(request.getUsername())
				.orElseThrow(() -> new RuntimeException("Kullanıcı bulunamadı"));
		
		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Şifre hatalı");
			
		}
		String jwtToken = jwtService.generateToken(user.getUsername(), user.getRole());
		
		return new LoginResponse(jwtToken, user.getUsername(), user.getRole());
		
	}
	


}
