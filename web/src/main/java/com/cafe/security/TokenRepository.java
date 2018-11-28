package com.cafe.security;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

@Repository
public class TokenRepository implements ITokenRepository {


	private Map<String, String> tokenMap;

	private TokenRepository() {
		tokenMap = new HashMap<>();
	}


	public String getLogin(String token) {
		return tokenMap.get(token);
	}

	public void putToken(String token, String login) {
		tokenMap.put(token, login);
	}

	public void removeToken(String token) {
		tokenMap.remove(token);
	}

}
