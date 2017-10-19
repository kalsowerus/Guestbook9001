package guestbook.controller;

import guestbook.form.GuestbookForm;
import guestbook.util.LoginUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.UUID;

@Controller
public class GuestbookController {
	private static final String CSRF_TOKEN_ATTRIBUTE_NAME = "csrfToken";

	@GetMapping("/")
	public String guestbook(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		if(LoginUtil.isLoggedIn(session)) {
			model.addAttribute("guestbookForm", new GuestbookForm());
			String csrfToken;
			if((csrfToken = (String) session.getAttribute(CSRF_TOKEN_ATTRIBUTE_NAME)) == null) {
				csrfToken = UUID.randomUUID().toString();
				session.setAttribute(CSRF_TOKEN_ATTRIBUTE_NAME, csrfToken);
			}
			model.addAttribute(CSRF_TOKEN_ATTRIBUTE_NAME, csrfToken);
		}

		return "guestbook";
	}

	@PostMapping("/")
	public String doPost(HttpServletRequest request, HttpServletResponse response,
						 @Valid @ModelAttribute("guestbookForm") GuestbookForm guestbookForm, BindingResult result) throws IOException {
		if(result.hasErrors()) {
			return "guestbook";
		}

		HttpSession session = request.getSession(true);
		if(LoginUtil.isLoggedIn(session)) {
			String csrfSessionToken = (String) session.getAttribute(CSRF_TOKEN_ATTRIBUTE_NAME);
			String csrfRequestToken = request.getParameter(CSRF_TOKEN_ATTRIBUTE_NAME);
			if(csrfSessionToken != null && csrfSessionToken.equals(csrfRequestToken)) {
				// save post
				return "redirect:/";
			}
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return null;
		}
		return "redirect:/login";
	}
}
