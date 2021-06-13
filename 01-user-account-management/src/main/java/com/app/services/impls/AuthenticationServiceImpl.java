package com.app.services.impls;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.jboss.logging.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.entities.RoleEntity;
import com.app.entities.UserAccessDetailEntity;
import com.app.entities.UserProfileEntity;
import com.app.models.UserProfile;
import com.app.models.ui.requests.LoginRequestModel;
import com.app.models.ui.response.LoginResponseModel;
import com.app.repositories.UserAccountRepository;
import com.app.services.contracts.AccountAccessService;
import com.app.services.contracts.AuthenticationService;
import com.app.services.contracts.RoleService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{
	
	private final Logger logger = Logger.getLogger(getClass());
	
	//services!
	@Autowired
	private AccountAccessService accountAccessService;
	@Autowired
	private RoleService roleService;
	
	//repositories
	@Autowired
	private UserAccountRepository userProfileRepo;
	
	//utilities
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	
	@Override
	public UserProfileEntity findByUsername(String username){
		UserProfileEntity entity = userProfileRepo.findByUsername(username);
		return entity;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserProfileEntity userProfile = findByUsername(username);
		
		if(Objects.isNull(userProfile)) throw new UsernameNotFoundException(username);
		
		return new User(userProfile.getUsername(), userProfile.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
	}
	@Override
	public LoginResponseModel login(LoginRequestModel loginRequest) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String register(UserProfile userProfile){//requires to return the user id!
		logger.info("0x- Inside the register service!");
		ModelMapper modelMapper = getModelMapper();
		
		//make the property mapping to the entity table!
		UserProfileEntity userProfileEntity = modelMapper.map(userProfile, UserProfileEntity.class);
		userProfileEntity.setEncryptedPassword(bcryptPasswordEncoder.encode(userProfile.getPassword()));
		userProfileEntity.setRoles(null);
		
		//logger.info("user profile entity:"+userProfileEntity);
		logger.info("creating the user account!");
		//save the entity table!
		final UserProfileEntity userAccount = userProfileRepo.save(userProfileEntity);
		
		
		logger.info("creating and getting the roles for this user!");
		//create the roles first!, the roles may not even exists!
		List<RoleEntity> roles = roleService.createRoles(userProfile.getRoles());
		logger.info(" ---------------------------> roles:"+roles);
		
		//making the account to role and, role to account mapping! 
		logger.info("adding the roles to this user!"+roles);
		userAccount.addRoles(roles);
		
		//create the account access informations!
		logger.info("Creating the account access details");
		UserAccessDetailEntity accountAccessDetail = accountAccessService.createAccountAccess(userAccount);
		userAccount.setAccessDetail(accountAccessDetail);

		logger.info("saving the account access information and roles information!");
		//saving the account access information and roles information!
		userProfileRepo.save(userAccount);
		
		return userAccount.getId();
	}
	
	@Override
	public boolean checkUserNameforExistence(String username) {
		return Objects.nonNull(userProfileRepo.findByUsername(username));
	}
}
