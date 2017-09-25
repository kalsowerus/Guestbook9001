package guestbook.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ExceptionController {
    public static final Log LOG = LogFactory.getLog(ExceptionController.class);

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("error");
        int code = ((Integer) request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
        String message = HttpStatus.valueOf(code).getReasonPhrase();

        LOG.info(String.format("HTTP Error %s, %s", code, message));

        modelAndView.addObject("code", code);
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @RequestMapping("/throwException")
    public ModelAndView createError() {
        throw new RuntimeException("Test error");
    }
}
