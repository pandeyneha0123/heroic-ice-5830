package com.masai.service;

import com.masai.exception.LoginException;
import com.masai.model.Login;

public interface LoginService {
	public String logInToAccount(Login loginDto) throws LoginException;
	public String logOutFromAccount(String key) throws LoginException;
}
