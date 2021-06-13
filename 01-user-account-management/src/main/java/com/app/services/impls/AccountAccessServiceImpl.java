package com.app.services.impls;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.entities.UserAccessDetailEntity;
import com.app.entities.UserProfileEntity;
import com.app.repositories.AccountAccessRepository;
import com.app.services.contracts.AccountAccessService;

@Service
public class AccountAccessServiceImpl implements AccountAccessService{
	@Autowired
	private AccountAccessRepository accountAccessRepo;
	
	@Override
	public List<?> listAll() {
		List<UserAccessDetailEntity> accountAccessList = new ArrayList<>();
		
		accountAccessRepo.findAll().forEach(accountAccessList::add);
		
		return accountAccessList;
	}

	@Override
	public UserAccessDetailEntity getById(String id) {
		return accountAccessRepo.findById(id).get();
	}

	@Override
	public UserAccessDetailEntity saveOrUpdate(UserAccessDetailEntity domainObject) {
		return accountAccessRepo.save(domainObject);
	}

	@Override
	public void delete(UserAccessDetailEntity domainObject) {
		accountAccessRepo.delete(domainObject);
	}
	
	//This should be called after saving the userprofile entity to get the id!
	@Override
	public UserAccessDetailEntity createAccountAccess(UserProfileEntity userProfile) {
		UserAccessDetailEntity accessDetailEntity = new UserAccessDetailEntity();
		accessDetailEntity.setId(userProfile.getId());
		accessDetailEntity.setUserProfile(userProfile);
		
		accessDetailEntity = accountAccessRepo.save(accessDetailEntity);
		
		return accessDetailEntity;
	}
	
}
