package guestbook.controller;

import guestbook.dao.EntryDao;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Date;

@Controller
public class EditController {
	private static Logger LOG = LoggerFactory.getLogger(EditController.class);

	@Resource
	private EntryDao entryDao;

	@GetMapping("/edit/{id}")
	public String getEdit(HttpServletRequest request, HttpServletResponse response, Model model, @PathVariable long id,
						  @ModelAttribute("guestbookForm") GuestbookForm guestbookForm)
			throws IOException {
		HttpSession session = request.getSession(true);
		Entry entry = getEntryDao().getEntry(id);

		if(entry == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		if(canEdit(session, entry)) {
			guestbookForm.setText(entry.getText());
			model.addAttribute("entry", entry);
			return "edit";
		}

		response.sendError(HttpServletResponse.SC_FORBIDDEN);
		return null;
	}

	@PostMapping("/edit/{id}")
	public String postEdit(HttpServletRequest request, HttpServletResponse response, @PathVariable long id, Model model,
						   @Valid @ModelAttribute("guestbookForm") GuestbookForm guestbookForm, BindingResult result) throws IOException {
		if(result.hasErrors()) {
			return getEdit(request, response, model, id, guestbookForm);
		}

		HttpSession session = request.getSession(true);
		Entry entry = getEntryDao().getEntry(id);

		if(entry == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		if(canEdit(session, entry)) {
			entry.setLastModifier(LoginUtil.getUser(session).getUsername());
			entry.setModificationTimestamp(new Date());
			entry.setText(guestbookForm.getText());
			getEntryDao().updateEntry(entry);
			LOG.info(String.format("User %s edited entry with id %s", LogUtil.getUserInfo(request), id));
			return "redirect:/";
		}

		response.sendError(HttpServletResponse.SC_FORBIDDEN);
		return null;
	}

	@GetMapping("/delete/{id}")
	public String getDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable long id,
							Model model) throws IOException {
		HttpSession session = request.getSession(true);
		Entry entry = getEntryDao().getEntry(id);

		if(entry == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		if(canEdit(session, entry)) {
			model.addAttribute("entry", entry);
			return "delete";
		}

		response.sendError(HttpServletResponse.SC_FORBIDDEN);
		return null;
	}

	@PostMapping("/delete/{id}")
	public String postDelete(HttpServletRequest request, HttpServletResponse response, @PathVariable long id) throws IOException {
		HttpSession session = request.getSession(true);
		Entry entry = getEntryDao().getEntry(id);

		if(entry == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		if(canEdit(session, entry)) {
			getEntryDao().deleteEntry(id);
			LOG.info(String.format("User %s deleted entry with id %s", LogUtil.getUserInfo(request), id));
			return "redirect:/";
		}

		response.sendError(HttpServletResponse.SC_FORBIDDEN);
		return null;
	}

	private boolean canEdit(HttpSession session, Entry entry) {
		if(LoginUtil.isLoggedIn(session)) {
			User user = LoginUtil.getUser(session);
			if(user.isAdmin() || entry.getCreator().equals(user)) {
				return true;
			}
		}
		return false;
	}

	public EntryDao getEntryDao() {
		return entryDao;
	}

	public void setEntryDao(EntryDao entryDao) {
		this.entryDao = entryDao;
	}
}
