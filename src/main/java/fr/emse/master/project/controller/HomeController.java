package fr.emse.master.project.controller;

import com.github.jsonldjava.core.RDFDataset.Quad;
import org.apache.jena.query.ResultSet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import fr.emse.master.project.Model.Query;

import org.springframework.ui.Model;

@Controller
public class HomeController {

    @RequestMapping(value = { "/", "index", "home" })
    public String homePage(Model m) {
        m.addAttribute("pageName", "Home Page");
        System.err.println("going to homepage");
        return "home";
    }

    @RequestMapping(value = { "show_desc", "all" })
    public String ShowAllDesc(Model m) {
        m.addAttribute("pageName", "All Description");
        Query query = new Query("");
        ResultSet rs = query.sendMyShowAllQuery();
        m.addAttribute("ResultSet", rs);
        System.err.println("going School_desc");
        return "School_desc";
    }
}
