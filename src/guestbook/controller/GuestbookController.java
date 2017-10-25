package guestbook.controller;

import guestbook.dao.EntryDao;
import guestbook.entity.Entry;
import guestbook.entity.User;
import guestbook.form.GuestbookForm;
import guestbook.util.LoginUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;

@Controller
public class GuestbookController {
	@Resource
	private EntryDao entryDao;

	@GetMapping("/")
	public String getGuestbook(Model model, @ModelAttribute("guestbookForm") GuestbookForm guestbookForm) {
		model.addAttribute("entries", getEntryDao().getEntries(0));
		return "guestbook";
	}

	@PostMapping("/")
	public String postGuestbook(HttpServletRequest request, HttpServletResponse response, Model model,
								@Valid @ModelAttribute("guestbookForm") GuestbookForm guestbookForm, BindingResult result) throws IOException {
		if(result.hasErrors()) {
			return getGuestbook(model, guestbookForm);
		}

		HttpSession session = request.getSession(true);
		if(LoginUtil.isLoggedIn(session)) {
			Entry entry = new Entry((long) (Math.random() * 1000), guestbookForm.getText(),
					LoginUtil.getUser(session), new Date());
			getEntryDao().createEntry(entry);
			return "redirect:/";
		}
		return "redirect:/login";
	}

	public EntryDao getEntryDao() {
		return entryDao;
	}

	public void setEntryDao(EntryDao entryDao) {
		this.entryDao = entryDao;
	}
}
