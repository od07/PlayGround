package com.mytectra.springboot.playground.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Component;

@Component
public class ClientService implements ClientDetailsService {

	Map<String, ClientPojo> map = new HashMap<>();
	
	public ClientService() {
		//TODO from 
		ClientPojo clientPojo = new ClientPojo();
		clientPojo.setClientId("private_bms");
		clientPojo.setClientSecret("123498");
		clientPojo.setGrantTypes("password,refresh_token");
		clientPojo.setScopes("read,write");
		clientPojo.setAuthorities("USER");
		clientPojo.setResourceIds("oauth2-resource");
		clientPojo.setAccessTokenValiditySeconds(10000);
		
		map.put(clientPojo.getClientId(), clientPojo);
	}
	
	@Override
	public org.springframework.security.oauth2.provider.ClientDetails loadClientByClientId(String clientId)
			throws ClientRegistrationException {
		if(map.containsKey(clientId)) {
			return new Client(map.get(clientId));
		}
	
		return null;
	}

}
