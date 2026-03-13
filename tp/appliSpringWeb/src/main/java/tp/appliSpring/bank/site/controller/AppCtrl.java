package tp.appliSpring.bank.site.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/site/app")
public class AppCtrl {
    @RequestMapping("/toWelcome")
    String toWelcome(Model model) {
        model.addAttribute("message", "bienvenu(e)");
        model.addAttribute("title","welcome");
        return "welcome";
    }

    // Login form
    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    //version avec spring_security + spring_mvc
    @RequestMapping("/logout")
    public String clientLogout(Model model,
                               HttpServletRequest request, HttpServletResponse response,
                               SessionStatus sessionStatus) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
            //httpSession.invalidate() is indirectly called
        }
        sessionStatus.setComplete();
        model.addAttribute("message", "session terminée");
        model.addAttribute("title","welcome");
        return "welcome";
    }
}