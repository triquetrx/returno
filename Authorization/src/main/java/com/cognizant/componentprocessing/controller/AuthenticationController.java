package com.cognizant.componentprocessing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.componentprocessing.dto.UserDto;
import com.cognizant.componentprocessing.dto.ValidatingDTO;
import com.cognizant.componentprocessing.model.AuthenticationRequest;
import com.cognizant.componentprocessing.model.AuthenticationResponse;
import com.cognizant.componentprocessing.service.JwtUserDetailsService;
import com.cognizant.componentprocessing.service.RegisterUserService;
import com.cognizant.componentprocessing.util.JwtTokenUtil;

@RestController
@CrossOrigin
public class AuthenticationController {
	
	@Autowired
	AuthenticationManager authentication;

	@Autowired
	JwtTokenUtil jwt;

	@Autowired
	JwtUserDetailsService userDetails;
	
	@Autowired
	RegisterUserService registerService;
	
	private ValidatingDTO dto = new ValidatingDTO();
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthentication(@RequestBody AuthenticationRequest request) throws Exception {
		authenticate(request.getUsername(), request.getPassword());
		final UserDetails userRequest = userDetails.loadUserByUsername(request.getUsername());
		final String token = jwt.generateToken(userRequest);
		try {			
			return ResponseEntity.ok(new AuthenticationResponse(token,true));
		} catch(Exception e) {
			return (ResponseEntity<?>) ResponseEntity.status(400);
		}
	}
	
	
	@GetMapping("/validate")
	public ResponseEntity<?> validatingToken(@RequestHeader(name="Authorization") String token) {
		
		String tokenDup = token.substring(7);
		try {
			UserDetails user = userDetails.loadUserByUsername(jwt.getUsernameFromToken(tokenDup));
			if(jwt.validateToken(tokenDup, user)) {				
				dto.setValidStatus(true);
				return new ResponseEntity<>(dto,HttpStatus.OK);
			}			
			dto.setValidStatus(false);
			System.out.println("I was the one");
			return new ResponseEntity<>(dto,HttpStatus.FORBIDDEN);
		} catch(Exception e) {
			dto.setValidStatus(false);
			System.out.println("I was the one");
			return new ResponseEntity<>(dto,HttpStatus.FORBIDDEN);
		}
		
	}

	private void authenticate(String username,String password) throws Exception {
		try {			
			authentication
			.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch(DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	
	@CrossOrigin
	@PostMapping("/register")
	public ResponseEntity<?> addNewUser(@RequestBody UserDto user){
		
		registerService.newUser(user.getUsername(), user.getPassword());
		return ResponseEntity.ok("Created new user, Try signing in");
	}
}
