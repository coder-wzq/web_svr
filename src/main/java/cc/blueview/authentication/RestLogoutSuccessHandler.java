package cc.blueview.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RestLogoutSuccessHandler implements LogoutSuccessHandler {
	@Autowired
	private StringRedisTemplate template;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		String authToken = request.getHeader("x-auth-token");

		if (authToken == null) {
			authToken = request.getParameter("token");
		}

		if (authToken != null) {

			String key = "spring:session:sessions:" + authToken;
			template.delete(key);
		}

		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().flush();
	}
}