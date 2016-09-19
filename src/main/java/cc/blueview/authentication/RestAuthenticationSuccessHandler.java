package cc.blueview.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import cc.blueview.util.StringUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

public class RestAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private Logger logger = LoggerFactory.getLogger(RestAuthenticationSuccessHandler.class);
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication auth) throws IOException {
		handle(request, response, auth);

	}

	protected void handle(final HttpServletRequest request, final HttpServletResponse response,
			final Authentication auth) throws IOException {

		if (response.isCommitted()) {
			return;
		}

		HttpSession session = request.getSession(false);
		if (session != null) {
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
			CurrentUser user = (CurrentUser) auth.getPrincipal();
			user.setToken(session.getId());
			response.setStatus(HttpServletResponse.SC_OK);
			response.setHeader("Access-Control-Allow-Origin", "*");
			response.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE");
			response.setHeader("Access-Control-Max-Age", Long.toString(60 * 60));
			response.setHeader("Access-Control-Allow-Credentials", "false");
			response.setHeader("Access-Control-Allow-Headers",
					"Origin,Accept,X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,Authorization");

			response.getOutputStream()
					.write(("{\"__isWrapObject\":true,\"status\":0,\"__wrapObject\":" + StringUtil.getJson(user) + "}")
							.getBytes());
			response.flushBuffer();
		}

	}

	protected void clearAttributes(final HttpServletRequest request) {
		final HttpSession session = request.getSession(false);
		if (session == null) {
			return;
		}

	}

	public void setRedirectStrategy(final RedirectStrategy redirectStrategy) {
		this.redirectStrategy = redirectStrategy;
	}

	protected RedirectStrategy getRedirectStrategy() {
		return redirectStrategy;
	}

}