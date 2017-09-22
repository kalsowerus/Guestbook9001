package guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GuestbookController {
	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView guestbook() {
		return new ModelAndView("guestbook");
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public ModelAndView doPost() {
		return new ModelAndView("redirect:/");
	}
}
