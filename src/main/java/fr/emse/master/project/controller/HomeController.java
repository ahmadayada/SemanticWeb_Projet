package fr.emse.master.project.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.jena.base.Sys;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
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

    @RequestMapping(value = { "show_desc", "all" })
    public String ShowAllDesc(Model m) {
        m.addAttribute("pageName", "All Description");
        String datasetURL = "http://localhost:3030/ProjetDataSet";

        // ResultSet rs = null;
        List<String> qListX = new ArrayList<>();
        List<String> qListComment = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();

        try {
            String sparqlQu = "prefix schema:  <http://schema.org/> \n" +
                    "prefix rdfs:  <http://www.w3.org/2000/01/rdf-schema#> \n" +
                    "prefix rdf:   <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
                    "SELECT ?x ?comment ?type \n" +
                    "WHERE { \n" +
                    " ?x rdfs:comment ?comment . \n" +
                    " ?x rdf:type ?type \n} ORDER BY ?x \n ";

            Query q = QueryFactory.create(sparqlQu);
            QueryExecution qe = QueryExecutionFactory.sparqlService(datasetURL, q);
            ResultSet res = qe.execSelect();

            while (res.hasNext()) {
                // System.err.println(res.next().get("x").toString());

                qListX.add(res.next().get("x").toString());
            }

            Query y = QueryFactory.create(sparqlQu);
            QueryExecution qe2 = QueryExecutionFactory.sparqlService(datasetURL, y);
            ResultSet res2 = qe2.execSelect();
            while (res2.hasNext()) {
                // System.err.println(res.next().get("x").toString());

                qListComment.add(res2.next().get("comment").toString());
            }

            for (int i = 0; i < qListX.size(); i++) {
                map.put(qListX.get(i).toString(), qListComment.get(i).toString());
            }
        } catch (Exception e) {
            System.err.println("!! ERROR : EXECUTING QUERY \n" + e.getMessage() + "\n");
            return null;
        }
        m.addAttribute("ResultSet", map);
        System.err.println("going School_desc" + qListX.size() + "comment size : " + qListComment.size());
        return "School_desc";
    }
}
