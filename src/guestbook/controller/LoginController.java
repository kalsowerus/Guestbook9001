package guestbook.controller;

import guestbook.dao.UserDao;
import guestbook.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Resource
	UserDao userDao;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView getLogin() {
		return new ModelAndView("login");
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView doLogin(HttpServletRequest request, @RequestParam("username") String username,
								@RequestParam("password") String password) {
		if(userDao.authenticateUser(username, password)) {
			request.getSession(true).invalidate();
			HttpSession session = request.getSession(true);
			User user = userDao.getUser(username);
			session.setAttribute("user", user);
			return new ModelAndView("redirect:/");
		} else {
			ModelAndView modelAndView = new ModelAndView("login");
			modelAndView.addObject("failed", true);
			return modelAndView;
		}
	}

	@RequestMapping(value = "/logout")
	public ModelAndView doLogout(HttpServletRequest request) {
		request.getSession(true).removeAttribute("user");
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView getRegister() {
		return new ModelAndView("register");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView doRegister() {
		return new ModelAndView("redirect:/");
	}

	protected UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
}
