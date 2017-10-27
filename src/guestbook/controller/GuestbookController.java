package guestbook.controller;

import guestbook.dao.EntryDao;
import guestbook.dao.UserDao;
import guestbook.entity.Entry;
import guestbook.entity.User;
import guestbook.form.GuestbookForm;
import guestbook.util.LogUtil;
import guestbook.util.LoginUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static Logger LOG = LoggerFactory.getLogger(GuestbookController.class);

	@Resource
	private EntryDao entryDao;

	@GetMapping("/")
	public String getGuestbook(HttpServletRequest request, Model model, @ModelAttribute("guestbookForm") GuestbookForm guestbookForm) {
		String pageParameter = request.getParameter("page");
		int page;
		if(pageParameter == null || !pageParameter.matches("\\d")) {
			page = 1;
		} else {
			page = Integer.parseInt(pageParameter);
		}
        int pagesize = 2;
		model.addAttribute("entries", getEntryDao().getEntries(page, pagesize));
		model.addAttribute("pagecount", getEntryDao().getPageCount(pagesize));
		return "guestbook";
	}

	@PostMapping("/")
	public String postGuestbook(HttpServletRequest request, HttpServletResponse response, Model model,
								@Valid @ModelAttribute("guestbookForm") GuestbookForm guestbookForm, BindingResult result) throws IOException {
		if(result.hasErrors()) {
			return getGuestbook(request, model, guestbookForm);
		}

		HttpSession session = request.getSession(true);
		if(LoginUtil.isLoggedIn(session)) {
			Entry entry = new Entry((long) (Math.random() * 1000), guestbookForm.getText(),
					LoginUtil.getUser(session), new Date());
			getEntryDao().createEntry(entry);
			LOG.info(String.format("User %s created entry with id %s", LogUtil.getUserInfo(request), entry.getId()));
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
