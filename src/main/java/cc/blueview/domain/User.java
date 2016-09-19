package cc.blueview.domain;

import java.io.Serializable;

public class User implements Serializable{
	

	/**
	 * wzq
	 */
	private static final long serialVersionUID = 912119398882699759L;

	private int userId;

	private String userName;

	private String password;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
