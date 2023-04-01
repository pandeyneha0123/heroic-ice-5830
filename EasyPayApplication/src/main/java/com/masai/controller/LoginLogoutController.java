package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.LoginException;
import com.masai.model.Login;
import com.masai.service.LoginService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/epay")
public class LoginLogoutController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUserHandler(@Valid @RequestBody Login login) throws LoginException{
		
		 String result = loginService.logInToAccount(login);
		
		 return new ResponseEntity<String>(result, HttpStatus.ACCEPTED);
		 
	}
	
	@DeleteMapping("/logout")
	public ResponseEntity<String> logoutHandler (@RequestParam String key) throws LoginException{
		
		 String result = loginService.logOutFromAccount(key);
		
		 return new ResponseEntity<String>(result, HttpStatus.ACCEPTED);
		 
	}
	
	
	
}
