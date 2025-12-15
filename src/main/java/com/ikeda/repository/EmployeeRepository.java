package com.ikeda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ikeda.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByEmail(String email);
}
