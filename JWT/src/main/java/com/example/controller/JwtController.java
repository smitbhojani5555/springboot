package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.helper.JwtUtil;
import com.example.model.JwtRequest;
import com.example.model.JwtResponse;
import com.example.service.CustomUserAuthenitcateDetailService;

@RestController
public class JwtController {
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	CustomUserAuthenitcateDetailService customUserAuthenitcateDetailService;

	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<?> getJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
		System.out.println(jwtRequest);
		try {
			this.authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}

		UserDetails userDetails = this.customUserAuthenitcateDetailService.loadUserByUsername(jwtRequest.getUsername());

		String token = this.jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}
}
