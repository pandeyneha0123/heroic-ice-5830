package com.masai.model;

import io.micrometer.common.lang.NonNull;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class Login {
	@NonNull
	@Email(regexp = "Email shoud be well defiend ....")
	private String email;
	@NonNull
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\\\S+$).{8, 20}$",message = "password shoud be weel defiend...")
	private String password;
}
