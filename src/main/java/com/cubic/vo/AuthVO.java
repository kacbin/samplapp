package com.cubic.vo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AuthVO {

	private String authType;
	private String authKey;

	public AuthVO() {
	}

	public AuthVO(String authType, String authKey) {
		super();
		this.authType = authType;
		this.authKey = authKey;
	}

	public String getAuthType() {
		return authType;
	}

	public void setAuthType(String authType) {
		this.authType = authType;
	}

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

}
