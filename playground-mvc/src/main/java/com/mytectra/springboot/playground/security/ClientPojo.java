package com.mytectra.springboot.playground.security;

import java.io.Serializable;

/**
 * @author zhenhui on 16/9/14.
 */
public class ClientPojo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String clientId;

    private String clientSecret;

    private boolean secretRequired;

    private String grantTypes;

    private String authorities;

    private String scopes;

    private boolean scoped;

    private String resourceIds;

    private String redirectUri;

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the clientSecret
	 */
	public String getClientSecret() {
		return clientSecret;
	}

	/**
	 * @param clientSecret the clientSecret to set
	 */
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	/**
	 * @return the secretRequired
	 */
	public boolean isSecretRequired() {
		return secretRequired;
	}

	/**
	 * @param secretRequired the secretRequired to set
	 */
	public void setSecretRequired(boolean secretRequired) {
		this.secretRequired = secretRequired;
	}

	/**
	 * @return the grantTypes
	 */
	public String getGrantTypes() {
		return grantTypes;
	}

	/**
	 * @param grantTypes the grantTypes to set
	 */
	public void setGrantTypes(String grantTypes) {
		this.grantTypes = grantTypes;
	}

	/**
	 * @return the authorities
	 */
	public String getAuthorities() {
		return authorities;
	}

	/**
	 * @param authorities the authorities to set
	 */
	public void setAuthorities(String authorities) {
		this.authorities = authorities;
	}

	/**
	 * @return the scopes
	 */
	public String getScopes() {
		return scopes;
	}

	/**
	 * @param scopes the scopes to set
	 */
	public void setScopes(String scopes) {
		this.scopes = scopes;
	}

	/**
	 * @return the scoped
	 */
	public boolean isScoped() {
		return scoped;
	}

	/**
	 * @param scoped the scoped to set
	 */
	public void setScoped(boolean scoped) {
		this.scoped = scoped;
	}

	/**
	 * @return the resourceIds
	 */
	public String getResourceIds() {
		return resourceIds;
	}

	/**
	 * @param resourceIds the resourceIds to set
	 */
	public void setResourceIds(String resourceIds) {
		this.resourceIds = resourceIds;
	}

	/**
	 * @return the redirectUri
	 */
	public String getRedirectUri() {
		return redirectUri;
	}

	/**
	 * @param redirectUri the redirectUri to set
	 */
	public void setRedirectUri(String redirectUri) {
		this.redirectUri = redirectUri;
	}

	/**
	 * @return the accessTokenValiditySeconds
	 */
	public Integer getAccessTokenValiditySeconds() {
		return accessTokenValiditySeconds;
	}

	/**
	 * @param accessTokenValiditySeconds the accessTokenValiditySeconds to set
	 */
	public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
		this.accessTokenValiditySeconds = accessTokenValiditySeconds;
	}

	/**
	 * @return the refreshTokenValiditySeconds
	 */
	public Integer getRefreshTokenValiditySeconds() {
		return refreshTokenValiditySeconds;
	}

	/**
	 * @param refreshTokenValiditySeconds the refreshTokenValiditySeconds to set
	 */
	public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
		this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    

}