package com.concreteteste.apirest.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.concreteteste.apirest.authentication.Authentications;
import com.concreteteste.apirest.entities.LoginResponse;
import com.concreteteste.apirest.entities.PerfilUserResponse;
import com.concreteteste.apirest.entities.Phone;
import com.concreteteste.apirest.entities.User;
import com.concreteteste.apirest.entities.UserResponse;
import com.concreteteste.apirest.exceptions.AcessoNaoAutorizado;
import com.concreteteste.apirest.exceptions.EmailJaCadastrado;
import com.concreteteste.apirest.exceptions.SessaoInvalida;
import com.concreteteste.apirest.exceptions.UsuarioSenhaInvalidos;
import com.concreteteste.apirest.respositories.PhoneRepository;
import com.concreteteste.apirest.respositories.UserRepository;



@Service
public class UserService  {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PhoneRepository phoneRepository;
	
	@Autowired
	private Authentications authenticationsPass;
	
	//Método usado para criar usuario no banco de dados
	public UserResponse createUser(User userRequest) throws EmailJaCadastrado {
		
		User user;
		UserResponse userResponse = new UserResponse();
		
		user = userRepository.findUserByEmail(userRequest.getEmail()).orElse(null);
		
		if (user != null) {
            throw new EmailJaCadastrado(HttpStatus.UNAUTHORIZED);
        }
		
		 userRequest = this.authenticationsPass.getPasswordEncrypted(userRequest);
		 userRequest.setCreated(LocalDate.now());
		 userRequest.setModified(LocalDateTime.now());
		 userRequest.setLastLogin(LocalDateTime.now());
	 	 userRequest.generateToken();

		 user = userRepository.save(userRequest);
		 
		 if (userRequest.getPhones() != null && !userRequest.getPhones().isEmpty()) {
				for (Phone phone : userRequest.getPhones()) {
						phone.setUser(user);
				}
				phoneRepository.saveAll(userRequest.getPhones());
			 }

		 BeanUtils.copyProperties(user, userResponse );
		 return userResponse;
	}
	
	//Método usado para fazer operações de login na API
	public UserResponse loginUser(LoginResponse loginRequest) throws UsuarioSenhaInvalidos {
		Optional<User> userOptional = userRepository.findUserByEmail(loginRequest.getEmail());
		User user = userOptional.orElseThrow(()-> new UsuarioSenhaInvalidos(HttpStatus.UNAUTHORIZED));
		
			if (!this.authenticationsPass.passwordAuthentication(loginRequest.getPassword(), user.getPassword())) { 
				throw new UsuarioSenhaInvalidos(HttpStatus.UNAUTHORIZED);
			}
			
			user.setLastLogin(LocalDateTime.now());
			user.generateToken();
			
			userRepository.save(user);
			
			UserResponse userResponse = new UserResponse();
			BeanUtils.copyProperties(user, userResponse);

			return userResponse;
				
	}
	
	//Método responsavel por acessar o perfil de usuario caso suas cresdenciais(email e senha) estejam corretos
	public UserResponse perfilUser(PerfilUserResponse perfilUserResponse) throws AcessoNaoAutorizado {
		
		Optional<User> userToken = userRepository.findByToken(UUID.fromString(perfilUserResponse.getToken()));
		userToken.orElseThrow(()-> new AcessoNaoAutorizado(HttpStatus.UNAUTHORIZED));
		
		Optional<User> userId = userRepository.findById(UUID.fromString(perfilUserResponse.getIdUser()));
			
			if(userId.isPresent()) {
				UUID token = userId.get().getToken();
				UUID tokenPerfil = UUID.fromString(perfilUserResponse.getToken());
					
			if(!tokenPerfil.equals(token)) {
				throw new AcessoNaoAutorizado(HttpStatus.UNAUTHORIZED);
			} else if (LocalDateTime.now().minusMinutes(30).compareTo(userId.get().getLastLogin()) > 0){
				throw new SessaoInvalida(HttpStatus.UNAUTHORIZED);
			}
			
			}else{
				throw new AcessoNaoAutorizado(HttpStatus.UNAUTHORIZED);
			}
			
			UserResponse userResponse = new UserResponse();
			BeanUtils.copyProperties(userId.get(), userResponse);

			return userResponse;	
	}
}
	
	

