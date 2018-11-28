package com.cafe.security;

public interface ITokenRepository {
	
	public String getLogin(String token);

	public void putToken(String token, String login);

	public void removeToken(String token);

}
