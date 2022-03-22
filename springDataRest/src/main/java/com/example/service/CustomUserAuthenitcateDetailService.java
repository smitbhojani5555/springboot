package com.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.repository.UserRepository;

@Service
public class CustomUserAuthenitcateDetailService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		List<com.example.entity.User> users = userRepository.findAll();
		String password = "";

		for (com.example.entity.User user : users) {
			if (username.equals(user.getUsername())) {
				password = user.getPassword();
			}

		}
		if (password != null) {
			return new User(username, password, new ArrayList<>());

		} else {
			throw new UsernameNotFoundException("User Not Found");
		}
	}

}
