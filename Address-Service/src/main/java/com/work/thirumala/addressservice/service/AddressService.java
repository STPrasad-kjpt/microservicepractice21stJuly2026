package com.work.thirumala.addressservice.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.work.thirumala.addressservice.entity.Address;
import com.work.thirumala.addressservice.repo.AddressRepository;
import com.work.thirumala.addressservice.response.AddressResponse;

@Service
public class AddressService {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	public AddressResponse findAddressByEmployeeId(int employeeId) {
		Address address = addressRepository.findAddressByEmployeeId(employeeId);
		AddressResponse addressResponse = modelMapper.map(address, AddressResponse.class);
		return addressResponse;
	}
}
