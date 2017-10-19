package guestbook.filter;

import guestbook.entity.User;
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

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpSession session = request.getSession(true);

		prepareSession(session);
		String csrfToken = (String) session.getAttribute("csrfToken");

		if(request.getMethod().equals("POST")) {
			if(!csrfToken.equals(request.getParameter("csrfToken"))) {
				LOG.info(String.format("Invalid csrf token on %s", LogUtil.getRequestInfo(request)));
				response.sendError(HttpServletResponse.SC_FORBIDDEN);
			}
		}

		filterChain.doFilter(servletRequest, servletResponse);

		request.setAttribute("csrfToken", csrfToken);
	}

	@Override
	public void destroy() {}

	private void prepareSession(HttpSession session) {
		if(session.getAttribute("csrfToken") == null) {
			session.setAttribute("csrfToken", UUID.randomUUID().toString());
		}
	}
}
