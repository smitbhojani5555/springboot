package com.example.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.entities.EmployeeEntity;
import com.example.model.Empployee;
import com.example.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeRepository emplRepository;

	@Override
	public Empployee createEmployee(Empployee employee) {
		EmployeeEntity employeeEntity = new EmployeeEntity();
		BeanUtils.copyProperties(employee, employeeEntity);
		emplRepository.save(employeeEntity);
		return employee;
	}

	@Override
	public List<Empployee> getAllEmployees() {
		List<EmployeeEntity> employees = emplRepository.findAll();
		List<Empployee> emps = employees.stream()
				.map(emp -> new Empployee(emp.getId(), emp.getFirstName(), emp.getLastName(), emp.getEmail()))
				.collect(Collectors.toList());
		return emps;

	}

	@Override
	public Boolean delEmployee(Long id) {
		emplRepository.deleteById(id);
		return true;
	}

	@Override
	public Empployee getEmployeeById(Long id) {
		EmployeeEntity employeeEntity = emplRepository.findById(id).get();
		Empployee empployee = new Empployee();
		BeanUtils.copyProperties(employeeEntity, empployee);
		return empployee;
	}

	@Override
	public Empployee getEmployeeById(Long id, Empployee employee) {
		EmployeeEntity employeeEntity = emplRepository.findById(id).get();
		
		employeeEntity.setEmail(employee.getEmail());
		employeeEntity.setFirstName(employee.getFirstName());
		employeeEntity.setLastName(employee.getLastName());
		emplRepository.save(employeeEntity);
		return employee;
	}

}
