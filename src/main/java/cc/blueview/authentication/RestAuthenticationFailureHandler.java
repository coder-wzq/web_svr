package cc.blueview.authentication;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Bad
		// credentials");

		Map<String, Object> res = new HashMap<String, Object>();
		res.put("__isWrapObject", true);
		res.put("status", -1);
		if (exception instanceof UsernameNotFoundException) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404
			res.put("__wrapObject", exception.getMessage());
		}
		else if (exception instanceof BadCredentialsException) {
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401
			res.put("__wrapObject", "密码错误");
		} else {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN); // 403
			res.put("__wrapObject", "禁止访问");
		}
		Gson gson = new Gson();
        response.setContentType("application/json");
		response.getWriter().println(gson.toJson(res));

		response.flushBuffer();

	}
}