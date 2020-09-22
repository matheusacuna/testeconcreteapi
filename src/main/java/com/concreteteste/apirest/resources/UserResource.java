package com.concreteteste.apirest.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.concreteteste.apirest.entities.LoginResponse;
import com.concreteteste.apirest.entities.PerfilUserResponse;
import com.concreteteste.apirest.entities.User;
import com.concreteteste.apirest.entities.UserResponse;
import com.concreteteste.apirest.services.UserService;

@RestController()
@RequestMapping()
public class UserResource {
	
	@Autowired
	private UserService userServices;
	
	@PostMapping("/cadastro")
	public ResponseEntity<?> createUser(@RequestBody User userRequest) {

		UserResponse responseUser = userServices.createUser(userRequest);

        return ResponseEntity.ok().body(responseUser);
    }
	
	@PostMapping("/login")
	public ResponseEntity<UserResponse> loginUser(@RequestBody LoginResponse loginResponse){
		UserResponse userResponse = userServices.loginUser(loginResponse); 
		
		return ResponseEntity.ok().body(userResponse);
	}
	
	@PostMapping("/perfil")
	public ResponseEntity<UserResponse> perfilUser(@RequestBody PerfilUserResponse perfilUserResponse){
		
		UserResponse userResponse = userServices.perfilUser(perfilUserResponse);

        return ResponseEntity.ok().body(userResponse);
	}
	
}
