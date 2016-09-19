package cc.blueview.authentication;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import cc.blueview.service.UserService;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserService userService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		String errorMsg = "";

		// user db valid
		username = username.trim();
		CurrentUser user = (CurrentUser)userService.getUser(username, username, username, null);

		if (user == null) {
			errorMsg = "there is no user in database";
			throw new UsernameNotFoundException(errorMsg);
		}

		if (!DigestUtils.md5Hex(password).equals(user.getPassword())) {
			errorMsg = "Wrong password.";
			throw new UsernameNotFoundException(errorMsg);
		}

		return new UsernamePasswordAuthenticationToken(user, password, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}