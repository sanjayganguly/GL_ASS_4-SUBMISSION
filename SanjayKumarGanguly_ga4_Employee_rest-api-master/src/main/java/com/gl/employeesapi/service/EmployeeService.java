package com.gl.employeesapi.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gl.employeesapi.model.Employee;
import com.gl.employeesapi.repository.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {

	private final EmployeeRepository employeeRepository;
	
	@Transactional
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@Transactional
	public List<Employee> findAllEmployees() {
		return employeeRepository.findAll();
	}
	
	@Transactional
	public List<Employee> findAllEmployeesByOrder(String order) {
		List<Employee> empList = null;
		System.out.println(order +  "  order " + order.equals("asc") + "  " + order.equals("desc") + "  " + (order == "asc") + "  " + (order == "desc"));
		if(order.equals("asc")) {
			empList = employeeRepository.findAllWithCustomOrderBy(Sort.by(Sort.Direction.ASC, "firstName"));
		}
		else if(order.equals("desc")) {
			empList = employeeRepository.findAllWithCustomOrderBy(Sort.by(Sort.Direction.DESC, "firstName"));
		}
		
		return empList;
	}
	
	@Transactional
	public Employee findById(long id) {
		return employeeRepository.findById(id).orElseThrow(()->new IllegalArgumentException("No employee found."));
	}
	
	@Transactional
	public List<Employee> findByFirstName(String firstName) {
		return employeeRepository.findByFirstName(firstName);
	}

	@Transactional
	public void deleteEmployee(long id) {
		employeeRepository.deleteById(id);
	}
	
	@Transactional
	public Employee updateEmployee(Employee employee, long employeeId) {
		Employee employeeToUpdate = employeeRepository.getReferenceById(employeeId);
		employeeToUpdate.setFirstName(employee.getFirstName());
		employeeToUpdate.setLastName(employee.getLastName());
		employeeToUpdate.setEmail(employee.getEmail());
		return employeeRepository.save(employee);
	}
}
