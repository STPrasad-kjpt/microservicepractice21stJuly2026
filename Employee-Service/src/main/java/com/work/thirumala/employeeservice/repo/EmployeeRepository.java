package com.work.thirumala.employeeservice.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.work.thirumala.employeeservice.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	Employee findByName(String name);
	List<Employee> findByBloodGroup(String bloodGroup);
	//Employee findByState(String state);
}
