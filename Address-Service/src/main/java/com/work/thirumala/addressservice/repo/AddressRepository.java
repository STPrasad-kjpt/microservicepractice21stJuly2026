package com.work.thirumala.addressservice.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.work.thirumala.addressservice.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

	@Query(nativeQuery = true, value = "select ea.id, ea.line1, ea.line2, ea.state, ea.zip from microservices.address ea join microservices.employee e on e.id = ea.employee_id where ea.employee_id=:employeeId")
	Address findAddressByEmployeeId(@Param("employeeId") int employeeId);
}
