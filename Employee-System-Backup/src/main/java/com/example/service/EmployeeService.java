package com.example.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.model.Empployee;

public interface EmployeeService {

	List<Empployee> getAllEmployees();

	Empployee createEmployee(Empployee employee);

	Boolean delEmployee(Long id);

	Empployee getEmployeeById(Long id);

	Empployee getEmployeeById(Long id, Empployee employee);

}
