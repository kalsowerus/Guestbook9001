package guestbook.controller;

import guestbook.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@ControllerAdvice
public class ExceptionController {
    public static final Logger LOG = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(Throwable.class)
    public String handleException(HttpServletRequest request, Throwable throwable, Model model) {
        int code = 500;
        String message = HttpStatus.valueOf(code).getReasonPhrase();
        model.addAttribute("code", code);
        model.addAttribute("message", message);

        LOG.error(String.format("Error on %s", LogUtil.getRequestInfo(request)), throwable);

        return "error";
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, HttpServletResponse response, Model model) {
        int code = response.getStatus();
        String message = HttpStatus.valueOf(code).getReasonPhrase();
        model.addAttribute("code", code);
        model.addAttribute("message", message);

        LOG.info(String.format("HTTP error: %s, %s caused by %s", code, message, LogUtil.getUserInfo(request)));

        return "error";
    }

    @RequestMapping("/throwException")
    public String createError() {
        throw new RuntimeException("Test error");
    }
}
