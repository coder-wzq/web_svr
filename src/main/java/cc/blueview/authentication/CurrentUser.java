package cc.blueview.authentication;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

import cc.blueview.domain.User;

public class CurrentUser extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 129227983901798429L;
	private String loginName;
	private String token;
	private User user;

	public CurrentUser(User user, List<GrantedAuthority> authorities) {
		super(user.getUserName(), user.getPassword(), true, true, true, true, authorities);
		this.user = user;
		this.loginName = user.getUserName();
	}

	// public CurrentUser(String username, String password, boolean enabled,
	// boolean accountNonExpired,
	// boolean credentialsNonExpired, boolean accountNonLocked,
	// Collection<? extends GrantedAuthority> authorities) {
	// super(username, password, true, true, true, true, authorities);
	// this.user = user;
	// this.loginName = user.getUserName();
	// }

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}