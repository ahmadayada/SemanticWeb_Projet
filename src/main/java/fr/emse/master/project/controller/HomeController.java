package fr.emse.master.project.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {

    @RequestMapping(value = { "/", "index", "home" })
    public String homePage(Model m) {
        m.addAttribute("pageName", "Home Page");
        System.err.println("going to homepage");
        return "home";
    }
}
