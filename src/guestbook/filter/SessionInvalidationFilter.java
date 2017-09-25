package guestbook.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

public class SessionInvalidationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(true);
        Long lastActiveObject = (Long) session.getAttribute("lastActive");
        long currentTime = System.currentTimeMillis();

        if(lastActiveObject != null) {
            long lastActive = lastActiveObject.longValue();
            if(currentTime - lastActive > (5 * 60 * 1000)/*5 minutes*/) {
                session.invalidate();
                session = request.getSession(true);
            }
        }

        session.setAttribute("lastActive", Long.valueOf(currentTime));

        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
