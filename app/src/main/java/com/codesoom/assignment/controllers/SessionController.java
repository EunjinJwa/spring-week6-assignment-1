package com.codesoom.assignment.controllers;

import com.codesoom.assignment.application.AuthorizationService;
import com.codesoom.assignment.dto.LoginData;
import com.codesoom.assignment.dto.LoginSuccessData;
import java.util.HashMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/session")
@RestController
@CrossOrigin
public class SessionController {

	private final AuthorizationService authorizationService;

	public SessionController(AuthorizationService authorizationService) {
		this.authorizationService = authorizationService;
	}

	@PostMapping
	public LoginSuccessData login(@RequestBody @Valid LoginData login) {
		return authorizationService.login(login);
	}

}
