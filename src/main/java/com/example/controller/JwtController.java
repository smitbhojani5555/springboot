package com.example.controller;

import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.helper.JwtUtil;
import com.example.model.JwtRequest;
import com.example.model.JwtResponse;
import com.example.service.CustomUserAuthenitcateDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;

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

		String refresh_token = this.jwtUtil.generateRefreshToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token, refresh_token));
	}

	@RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
	public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String requestHeaderToken = request.getHeader("Authorization");
		String username = null;
		String refreshToken = null;
		String token = null;

		if (requestHeaderToken != null && requestHeaderToken.startsWith("Bearer ")) {
			refreshToken = requestHeaderToken.substring(7);
			Algorithm algorithm = Algorithm.HMAC256("SmitSecret".getBytes());
			JWTVerifier jwtVerifier = JWT.require(algorithm).build();
			DecodedJWT decodedJWT = jwtVerifier.verify(refreshToken);

			username = decodedJWT.getSubject();

			if (username != null) {
				UserDetails userDetails = this.customUserAuthenitcateDetailService.loadUserByUsername(username);
				token = this.jwtUtil.generateToken(userDetails);

				Map<String, String> tokens = new HashMap<>();
				tokens.put("token", token);
				tokens.put("refreshToken", refreshToken);
				response.setContentType(MimeTypeUtils.APPLICATION_JSON_VALUE);
				new ObjectMapper().writeValue(response.getOutputStream(), tokens);
			} else {
				System.out.println("Refresh Token is not validated..");
			}
		}

	}
}
