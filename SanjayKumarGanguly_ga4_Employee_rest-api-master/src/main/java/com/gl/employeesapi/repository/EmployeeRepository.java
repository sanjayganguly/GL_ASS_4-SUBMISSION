package com.gl.employeesapi.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gl.employeesapi.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	  List<Employee> findByFirstName(String firstName);
	  List<Employee> findAllWithCustomOrderBy(Sort sort);
}
