package guestbook.filter;

import guestbook.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.UUID;

public class CsrfFilter implements Filter {
	private static final Logger LOG = LoggerFactory.getLogger(CsrfFilter.class);

	public static final String CSRF_TOKEN_ATTRIBUTE_NAME = "csrfToken";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpSession session = request.getSession(true);

		prepareSession(session);

		if(request.getMethod().equals("POST")) {
			String csrfToken = (String) session.getAttribute(CSRF_TOKEN_ATTRIBUTE_NAME);
			if(!csrfToken.equals(request.getParameter(CSRF_TOKEN_ATTRIBUTE_NAME))) {
				LOG.warn(String.format("Invalid csrf token on %s", LogUtil.getRequestInfo(request)));
				HttpServletResponse response = (HttpServletResponse) servletResponse;
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
				return;
			}
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	@Override
	public void destroy() {}

	private void prepareSession(HttpSession session) {
		if(session.getAttribute(CSRF_TOKEN_ATTRIBUTE_NAME) == null) {
			session.setAttribute(CSRF_TOKEN_ATTRIBUTE_NAME, UUID.randomUUID().toString());
		}
	}
}
