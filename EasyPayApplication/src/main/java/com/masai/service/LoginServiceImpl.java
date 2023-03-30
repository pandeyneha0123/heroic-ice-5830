package com.masai.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.LoginException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.Login;
import com.masai.repository.CustomerRepository;
import com.masai.repository.SessionRepository;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private CustomerRepository customerReposotory;

	@Autowired
	private SessionRepository currentSessionResRepository;
	
	private String getRandomUUID() {

	    UUID randomUUID = UUID.randomUUID();

	    return randomUUID.toString().replaceAll("_", "");

	 }
	
	@Override
	public String logInToAccount(Login loginDto) throws LoginException {
		
		//check if customer present in database 
		Customer existingCustomer = customerReposotory.findByEmail(loginDto.getEmail());
		
		if(existingCustomer == null) {
			throw new LoginException("Plase enter a valid userId...");
		}
		
		//check if customer already in session 
		Optional<CurrentUserSession> currentUserSession = currentSessionResRepository.findById(existingCustomer.getCId());
		
		if(currentUserSession.isPresent())
			throw new LoginException("User already logged in with this userID");
		
		//checking password
		if(existingCustomer.getPassword().equals(loginDto.getPassword())) {
			
			CurrentUserSession cs = new CurrentUserSession();
			
			cs.setUserId(existingCustomer.getCId());
			cs.setLocalDateTime(LocalDateTime.now());
			cs.setUuid(getRandomUUID());

			CurrentUserSession cSession = currentSessionResRepository.save(cs);
			
			return cSession.toString();

		} else {
			throw new LoginException("Please enter a valid password...");
		}
	}

	@Override
	public String logOutFromAccount(String key) throws LoginException {
		
		//check if customer is logged in  
		CurrentUserSession currentUserSession = currentSessionResRepository.findByUuid(key);
		
		if(currentUserSession == null) {
			throw new LoginException("User not logged in with this userId");
		}
		
		//delete from session 
		currentSessionResRepository.delete(currentUserSession);
		
		return "Logged out !";
	}

}
