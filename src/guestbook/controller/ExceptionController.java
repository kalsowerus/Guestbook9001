package guestbook.controller;

import guestbook.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@ControllerAdvice
public class ExceptionController {
    public static final Logger LOG = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(Throwable.class)
    public ModelAndView handleException(HttpServletRequest request, HttpServletResponse response, Throwable throwable) {
        ModelAndView modelAndView = new ModelAndView("error");
        int code = 500;
        String message = HttpStatus.valueOf(code).getReasonPhrase();
        modelAndView.addObject("code", code);
        modelAndView.addObject("message", message);

        LOG.info(String.format("Error on %s", LogUtil.getRequestInfo(request)), throwable);

        return modelAndView;
    }

    @RequestMapping("/error")
    public ModelAndView handleError(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView modelAndView = new ModelAndView("error");
        int code = response.getStatus();
        String message = HttpStatus.valueOf(code).getReasonPhrase();
        modelAndView.addObject("code", code);
        modelAndView.addObject("message", message);

        LOG.info(String.format("HTTP error: %s, %s on %s", code, message, LogUtil.getRequestInfo(request)));

        return modelAndView;
    }

    @RequestMapping("/throwException")
    public ModelAndView createError() {
        throw new RuntimeException("Test error");
    }
}
