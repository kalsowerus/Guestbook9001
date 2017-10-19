package guestbook.controller;

import guestbook.dao.UserDao;
import guestbook.entity.User;
import guestbook.form.LoginForm;
import guestbook.form.RegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {
	@Resource
	UserDao userDao;

	@GetMapping("/login")
	public String getLogin(Model model) {
		model.addAttribute("loginForm", new LoginForm());
		return "login";
	}

	@PostMapping("/login")
	public String doLogin(HttpServletRequest request, Model model,
						  @Valid @ModelAttribute("loginForm") LoginForm loginForm, BindingResult result) {
		if(result.hasErrors()) {
			return "login";
		}

		String username = loginForm.getUsername();
		String password = loginForm.getPassword();

		if(getUserDao().authenticateUser(username, password)) {
			request.getSession(true).invalidate();
			HttpSession session = request.getSession(true);
			User user = getUserDao().getUser(username);
			session.setAttribute("user", user);
			return "redirect:/";
		} else {
			model.addAttribute("error", "Invalid username or password");
			return "login";
		}
	}

	@RequestMapping("/logout")
	public String doLogout(HttpServletRequest request) {
		request.getSession(true).removeAttribute("user");
		return "redirect:/";
	}

	@GetMapping("/register")
	public String getRegister(Model model) {
		model.addAttribute("registerForm", new RegisterForm());
		return "register";
	}

	@PostMapping("/register")
	public String doRegister(Model model, @Valid @ModelAttribute("registerForm") RegisterForm registerForm,
							 BindingResult result) {
		if(result.hasErrors()) {
			return "register";
		}

		String username = registerForm.getUsername();
		String password = registerForm.getPassword();
		String passwordRepeat = registerForm.getPasswordRepeat();

		if(getUserDao().getUser(username) != null) {
			model.addAttribute("error", "User exists");
		} else if(password == null || !password.equals(passwordRepeat)) {
			model.addAttribute("error", "Passwords don't match");
		} else {
			// create user
			return "redirect:/";
		}

		return "register";
	}

	protected UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
