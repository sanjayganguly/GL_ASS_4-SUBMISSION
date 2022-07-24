package com.gl.employeesapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.employeesapi.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
