package com.bytmasoft.persistance.service.interfaces;


import com.bytmasoft.domain.models.BaseUser;

public interface ManagerService <Manager> extends BaseUserService<BaseUser>  {

	Manager findByUsernameOrEmail(String usernameOrEmail);
}
