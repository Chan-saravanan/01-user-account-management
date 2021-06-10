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
import com.app.models.AddressModel;
import com.app.repositories.AccountAccessRepository;
import com.app.repositories.AddressRepository;
import com.app.repositories.RoleRepository;
import com.app.repositories.UserAccountRepository;

@RestController
@RequestMapping("/account")
public class UserProfileController {
	private final Logger logger = Logger.getLogger(getClass().getName());
	@Autowired
	private UserAccountRepository userProfileRepo;
	@Autowired
	private AccountAccessRepository accountAccessRepo;
	@Autowired
	private RoleRepository roleRepo;
	@Autowired
	private AddressRepository addressRepo;
	
	
	@PostMapping(path= {"/create","/register"})
	public ResponseEntity<String> createUser(){
		//Getting or creating the student role!
		RoleEntity role = new RoleEntity();
		role.setRole("STUDENT");
		role = roleRepo.save(role);
		
		//creating the userProfile account!
		UserProfileEntity userProfile = new UserProfileEntity();
		userProfile.setUsername("CHANJAY001");
		userProfile.setFirstName("Chanjay");
		userProfile.setLastName("Saravanan");
		userProfile.setActive(true);
		userProfile.setEncryptedPassword("ThisIsEncryptedPassword");
		userProfile = userProfileRepo.save(userProfile);		
		String userId = userProfile.getId();
		logger.info("Created the user :"+userId);
		
		UserAccessDetailEntity userAccessDetail = new UserAccessDetailEntity();
		userAccessDetail.setId(userId);
		userAccessDetail.setAuthorization_token("authorization_token-aksjcnacjnajcnajcncnjcs");
		userAccessDetail.setRefresh_token("refresh_token-aklscacmakcmalcmlcmaccasc");
		userAccessDetail.setAuthCount(0);
		userAccessDetail.setUserProfile(userProfile);
		userProfile.setAccessDetail(userAccessDetail);
		userAccessDetail = accountAccessRepo.save(userAccessDetail);
		userProfile = userProfileRepo.save(userProfile);
		
		userProfile.addRole(role);
		userProfile = userProfileRepo.save(userProfile);
		return new ResponseEntity<>(userProfile.getId(), HttpStatus.CREATED);
	}
	
	@PostMapping("/update/address/{userId}")
	public ResponseEntity<String> updateAddress(@PathVariable String userId, @RequestBody AddressModel addressModel){
		//find the userprofile object!
		logger.info("userId:"+userId);
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		
		UserProfileEntity userProfile = userProfileRepo.findById(userId).get();
		
		AddressEntity entity = modelMapper.map(addressModel, AddressEntity.class);
		entity.setUserProfile(userProfile);
		entity = addressRepo.save(entity);
		
		userProfile.setAddress(entity);
		userProfileRepo.save(userProfile);
		
		return new ResponseEntity<>("Succes", HttpStatus.OK);
	}
}
