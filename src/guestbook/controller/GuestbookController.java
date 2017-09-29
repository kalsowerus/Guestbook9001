package guestbook.controller;

import guestbook.util.LoginUtil;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Controller
public class GuestbookController {
	private static final String CSRF_TOKEN_ATTRIBUTE_NAME = "csrfToken";

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView guestbook(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("guestbook");
		HttpSession session = request.getSession(true);
		if(LoginUtil.isLoggedIn(session)) {
			String csrfToken = UUID.randomUUID().toString();
			session.setAttribute(CSRF_TOKEN_ATTRIBUTE_NAME, csrfToken);
			modelAndView.addObject(CSRF_TOKEN_ATTRIBUTE_NAME, csrfToken);
		}
		return modelAndView;
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ModelAndView doPost(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("redirect:/");
		HttpSession session = request.getSession(true);
		if(LoginUtil.isLoggedIn(session)) {
			String csrfSessionToken = (String) session.getAttribute(CSRF_TOKEN_ATTRIBUTE_NAME);
			String csrfRequestToken = (String) request.getAttribute(CSRF_TOKEN_ATTRIBUTE_NAME);
			if(csrfSessionToken != null && csrfSessionToken.equals(csrfRequestToken)) {
				LogFactory.getLog(GuestbookController.class).info("Anti-CSRF successful");
				// save post
			}
		}
		return modelAndView;
	}
}
