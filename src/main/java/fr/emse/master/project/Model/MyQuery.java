package fr.emse.master.project.Model;

import com.github.andrewoma.dexx.collection.ArrayList;
import com.github.andrewoma.dexx.collection.List;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyQuery {
    String subject, predicate, object;

    public MyQuery() {
        this.subject = "";
        this.predicate = "";
        this.object = "";
    }

    public MyQuery(String subject, String predicate, String object) {
        this.subject = subject;
        this.predicate = predicate;
        this.object = predicate;
    }

    public List<String> sendMyShowAllQuery() {
        String datasetURL = "http://localhost:3030/ProjetDataSet";

        // ResultSet rs = null;
        List<String> qListX = new ArrayList<>();
        // List<String> qListX = new ArrayList<>();

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
                System.err.println(res.next().get("x").toString());

                // qListX.append(res.next().get("x").toString());

            }
            for (int i = 0; i < qListX.size(); i++) {
                System.err.println(qListX.get(i).toString());

            }
            return qListX;
        } catch (Exception e) {
            System.err.println("!! ERROR : EXECUTING QUERY \n" + e.getMessage() + "\n");
            return null;
        }

    }
}
