package com.app.services.contracts;

import com.app.entities.UserAccessDetailEntity;
import com.app.entities.UserProfileEntity;

public interface AccountAccessService extends CRUDService<UserAccessDetailEntity, String>{
	UserAccessDetailEntity createAccountAccess(UserProfileEntity userProfile);
}
