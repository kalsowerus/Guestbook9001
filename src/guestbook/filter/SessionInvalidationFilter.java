package guestbook.filter;

import guestbook.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionInvalidationFilter implements Filter {
    public static final String LAST_ACTIVE_SESSION_ATTRIBUTE = "lastActive";
    public static final int SESSION_TIMEOUT = 5 * 60 * 1000; // 5 minutes

    public static final Logger LOG = LoggerFactory.getLogger(SessionInvalidationFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(true);
        Long lastActiveObject = (Long) session.getAttribute(LAST_ACTIVE_SESSION_ATTRIBUTE);
        long currentTime = System.currentTimeMillis();

        if(lastActiveObject != null) {
            long lastActive = lastActiveObject.longValue();
            if(currentTime - lastActive > SESSION_TIMEOUT) {
                LOG.info(String.format("Invalidated session of %s due to timeout", LogUtil.getUserInfo(request)));
                session.invalidate();
                session = request.getSession(true);
            }
        }

        session.setAttribute(LAST_ACTIVE_SESSION_ATTRIBUTE, Long.valueOf(currentTime));

        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {}
}
