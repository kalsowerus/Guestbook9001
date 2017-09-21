package guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GuestbookController {
	@RequestMapping("")
	public ModelAndView guestbook() {
		return new ModelAndView("guestbook");
	}
}
