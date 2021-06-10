package com.app.controllers;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.AddressEntity;
import com.app.entities.RoleEntity;
import com.app.entities.UserAccessDetailEntity;
import com.app.entities.UserProfileEntity;
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
	private AddressRepository addreessRepo;
	
	
	@PostMapping(path= {"/create","/register"})
	public ResponseEntity<String> createUser(){
		logger.info("------------->Creating the role, student!");
		//1. check whether this role exists!
		//2. If not exists, then create the STUDENT role!
		RoleEntity role = new RoleEntity();
		role.setRole("STUDENT");
		//save the role to the database!
		role = roleRepo.save(role);
		logger.info("------------->Role student, "+role);
		
		
		//creating the userProfile account!
		logger.info("------------->Creating the account, UserProfile!");
		UserProfileEntity userProfile = new UserProfileEntity();
		userProfile.setUsername("CHANJAY001");
		userProfile.setFirstName("Chanjay");
		userProfile.setLastName("Saravanan");
		userProfile.setActive(true);
		userProfile.setEncryptedPassword("ThisIsEncryptedPassword");
		userProfile = userProfileRepo.save(userProfile);		
		String userId = userProfile.getId();
		logger.info("Created the user :"+userId);
		
		
		//create the userAccessDetail information!		
		logger.info("------------->Saving UserAccessDetail info!");
		UserAccessDetailEntity userAccessDetail = new UserAccessDetailEntity();
		userAccessDetail.setId(userId);
		userAccessDetail.setAuthorization_token("authorization_token-aksjcnacjnajcnajcncnjcs");
		userAccessDetail.setRefresh_token("refresh_token-aklscacmakcmalcmlcmaccasc");
		userAccessDetail.setAuthCount(0);
		userAccessDetail.setUserProfile(userProfile);
		userProfile.setAccessDetail(userAccessDetail);
		logger.info("setting user access detail info!");
		userAccessDetail = accountAccessRepo.save(userAccessDetail);
		logger.info("Saved the user Access detail!");
		userProfile = userProfileRepo.save(userProfile);
		
		userProfile.addRole(role);
		userProfile = userProfileRepo.save(userProfile);
		logger.info("Providing the response!");
		return new ResponseEntity<>(userProfile.getId(), HttpStatus.CREATED);
	}
	
	@PostMapping("/update/address/{userId}")
	public ResponseEntity<UserProfileEntity> updateAddress(@PathVariable String userId){
		//find the userprofile object!
		AddressEntity address = new AddressEntity();
		address.setAddressLine1("This is address line1");
		address.setAddressLine2("This is address line2");
		address.setCity("Chennai");
		address.setState("Tamil Nadu");
		address.setContactPhone("9791114359");
		
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
