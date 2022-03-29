package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Empployee;
import com.example.service.EmployeeService;

/**
 * @author MTPC-327
 *
 */
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("api/v1")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@PostMapping("/employee")
	public Empployee createEmployee(@RequestBody Empployee employee) {
		return employeeService.createEmployee(employee);
	}

	@GetMapping("/employee")
	public List<Empployee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@DeleteMapping("/employee/{id}")
	public ResponseEntity<Map<String, Boolean>> getAllEmployees(@PathVariable Long id) {
		Boolean deleted = employeeService.delEmployee(id);
		Map<String, Boolean> returnMap = new HashMap<>();
		returnMap.put("deleted", deleted);
		return ResponseEntity.ok(returnMap);
	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<Empployee> getEmployeeById(@PathVariable Long id) {
		Empployee employee = null;
		employee = employeeService.getEmployeeById(id);
		return ResponseEntity.ok(employee);
	}
	
	@PutMapping("/employee/{id}")
	public ResponseEntity<Empployee> updateEmployee(@PathVariable Long id,@RequestBody Empployee employee) {
		employee = employeeService.getEmployeeById(id,employee);
		return ResponseEntity.ok(employee);
	}

}
