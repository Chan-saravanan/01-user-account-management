package com.app.controllers;

import java.util.logging.Logger;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.AddressEntity;
import com.app.entities.RoleEntity;
import com.app.entities.UserAccessDetailEntity;
import com.app.entities.UserProfileEntity;
import com.app.models.Address;
import com.app.models.UserProfile;
import com.app.repositories.AccountAccessRepository;
import com.app.repositories.AddressRepository;
import com.app.repositories.RoleRepository;
import com.app.repositories.UserAccountRepository;

@RestController
@RequestMapping("/account")
public class UserProfileController {
	private final Logger logger = Logger.getLogger(getClass().getName());

	
	@PostMapping("/update/address/{userId}")
	protected ResponseEntity<String> updateAddress(@PathVariable String userId, @RequestBody Address addressModel){
		logger.info("userId:"+userId);
		
		return new ResponseEntity<>("Success!", HttpStatus.OK);
	}
}
