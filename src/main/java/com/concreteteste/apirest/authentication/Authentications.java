package com.concreteteste.apirest.authentication;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

import com.concreteteste.apirest.entities.User;

@Component
public class Authentications {
	
	public User getPasswordEncrypted(User userRequest){
	    //Random Sal
	    String salt = BCrypt.gensalt();
	    //Cria a senha hash utilizando o sal gerado
        String passwordHash = BCrypt.hashpw(userRequest.getPassword(), salt);
        //Atualiza a senha do usuário
        userRequest.setPassword(passwordHash);
        	return userRequest;
    }
	
	public boolean passwordAuthentication(String passwordResponse, String userPassword){
        // Usa o BCrypt e verifica se a senha está correta
        boolean isAuthenticationSuccessful = BCrypt.checkpw(passwordResponse, userPassword);
        	return isAuthenticationSuccessful;
    }
}

