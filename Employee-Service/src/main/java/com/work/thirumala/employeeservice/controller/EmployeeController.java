package com.work.thirumala.employeeservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.work.thirumala.employeeservice.response.EmployeeResponse;
import com.work.thirumala.employeeservice.service.EmployeeService;

@RestController
//@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	
	//@GetMapping("/employees/{id}")
	@GetMapping("empId/{id}")
	public ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable("id") int id){
		EmployeeResponse employeeResponse = employeeService.getEmployeeById(id);
		return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
	}
	
	//@GetMapping("/employees")
	@GetMapping("/")
	public ResponseEntity<List<EmployeeResponse>> getEmployeeList(){
		List<EmployeeResponse> allEmployees =employeeService.getAllEmployees();
		return ResponseEntity.status(HttpStatus.OK).body(allEmployees);
	}
	
	//@GetMapping("/employees/empName/{name}")
	@GetMapping("empName/{name}")
	public ResponseEntity<EmployeeResponse> getEmployeeDetailsByName(@PathVariable("name") String name){
	//public ResponseEntity<EmployeeResponse> getEmployeeDetailsByName(@RequestParam("name") String name){
		EmployeeResponse employeeResponse = employeeService.getEmployeeDetailsByName(name);
		return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
	}
	
	@GetMapping("empBG/{bloodGroup}")
	public ResponseEntity<List<EmployeeResponse>> getEmployeeDetailsByBloodGroup(@PathVariable("bloodGroup") String bloodGroup){
		List<EmployeeResponse> employeeResponse = employeeService.getEmployeeByBloodGroup(bloodGroup);
		return ResponseEntity.status(HttpStatus.OK).body(employeeResponse);
	}
	
	
	  @GetMapping("empState/{state}") 
	  public ResponseEntity<List<EmployeeResponse>> getEmployeeDetailsByState(@PathVariable("state") String state){
		  List<EmployeeResponse> employeeList = employeeService.getEmployeeByState(state); 
		  return ResponseEntity.status(HttpStatus.OK).body(employeeList); 
	  }
	 
}
