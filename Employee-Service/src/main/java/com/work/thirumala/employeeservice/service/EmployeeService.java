package com.work.thirumala.employeeservice.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.work.thirumala.employeeservice.entity.Employee;
import com.work.thirumala.employeeservice.repo.EmployeeRepository;
import com.work.thirumala.employeeservice.response.AddressResponse;
import com.work.thirumala.employeeservice.response.EmployeeResponse;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${addressservice.context-path}")
	private String addressContextPath;

	public EmployeeResponse getEmployeeById(int id) {
		Employee employee = employeeRepository.findById(id).get();

		/*
		 * EmployeeResponse employeeResponse = new EmployeeResponse();
		 * employeeResponse.setId(employee.getId());
		 * employeeResponse.setName(employee.getName());
		 * employeeResponse.setEmail(employee.getEmail());
		 * employeeResponse.setBloodGroup(employee.getBloodGroup());
		 */

		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
		// AddressResponse addressResponse =
		// restTemplate.getForObject("http://ADDRESS-SERVICE"+addressContextPath+"/address/"+id,
		// AddressResponse.class);
		AddressResponse addressResponse = restTemplate
				// .getForObject("http://address-service"+addressContextPath+"/address/{id}",
				// AddressResponse.class, id);
				//.getForObject("http://ADDRESS-SERVICE" + addressContextPath + "/address/{id}", AddressResponse.class,
				//		id);
			.getForObject("http://ADDRESS-SERVICE" + addressContextPath + "/{id}", AddressResponse.class,
				id);
		employeeResponse.setAddressResponse(addressResponse);

		return employeeResponse;
	}

	public List<EmployeeResponse> getAllEmployees() {
		List<Employee> employeeList = employeeRepository.findAll();
		List<EmployeeResponse> employeeResponseList = new ArrayList<>();

		for (Employee employee : employeeList) {
			EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
			AddressResponse addressResponse = restTemplate.getForObject(
					//"http://ADDRESS-SERVICE" + addressContextPath + "/address/{id}", AddressResponse.class,
					"http://ADDRESS-SERVICE" + addressContextPath + "/{id}", AddressResponse.class,
					employee.getId());
			employeeResponse.setAddressResponse(addressResponse);
			employeeResponseList.add(employeeResponse);
		}
		return employeeResponseList;
	}

	public EmployeeResponse getEmployeeDetailsByName(String name) {
		Employee employee = employeeRepository.findByName(name);

		EmployeeResponse employeeResponse = modelMapper.map(employee, EmployeeResponse.class);
		AddressResponse addressResponse = restTemplate.getForObject(
				"http://ADDRESS-SERVICE" + addressContextPath + "/{id}", AddressResponse.class,
				employee.getId());
		employeeResponse.setAddressResponse(addressResponse);

		return employeeResponse;
	}

	public List<EmployeeResponse> getEmployeeByBloodGroup(String bloodGroup) {
		List<Employee> employees = employeeRepository.findByBloodGroup(bloodGroup);
		List<EmployeeResponse> allEmployees = getAllEmployees();
		for(Employee employ : employees) {
		EmployeeResponse employeeResponse = modelMapper.map(employ, EmployeeResponse.class);
		AddressResponse addressResponse = restTemplate.getForObject(
				"http://ADDRESS-SERVICE" + addressContextPath + "/{id}", AddressResponse.class,
				employ.getId());
		employeeResponse.setAddressResponse(addressResponse);
		allEmployees.add(employeeResponse);
		}
		return allEmployees;
	}

	/*
	 * public EmployeeResponse getEmployeeByState(String state) { Employee employee
	 * = employeeRepository.findByState(state);
	 * 
	 * EmployeeResponse employeeResponse = modelMapper.map(employee,
	 * EmployeeResponse.class); AddressResponse addressResponse = restTemplate
	 * .getForObject("http://ADDRESS-SERVICE"+addressContextPath+"/address/{id}",
	 * AddressResponse.class, employee.getId());
	 * employeeResponse.setAddressResponse(addressResponse);
	 * 
	 * return employeeResponse; }
	 */
	
	public List<EmployeeResponse> getEmployeeByState(String state) {
		List<EmployeeResponse> result = new ArrayList<>();
		List<EmployeeResponse> allEmployees = getAllEmployees();
		for(EmployeeResponse empResp : allEmployees) {
			if(empResp.getAddressResponse().getState().equalsIgnoreCase(state)) {
				result.add(empResp);
			}
		}
		return result;
	}
}
