package com.app.services;

import java.util.List;

import com.app.entites.Address;
import com.app.payloads.AddressDTO;
import com.app.payloads.CreateAddressDTO;

public interface AddressService {
	
	AddressDTO createAddress(CreateAddressDTO createAddressDTO);
	
	List<AddressDTO> getAddresses();
	
	AddressDTO getAddress(Long addressId);
	
	AddressDTO updateAddress(Long addressId, Address address);
	
	String deleteAddress(Long addressId);
}
