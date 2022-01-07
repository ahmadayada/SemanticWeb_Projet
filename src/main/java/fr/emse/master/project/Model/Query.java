package fr.emse.master.project.Model;

import org.apache.jena.base.Sys;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;

public class Query {
    String query;

    public Query(String query) {
        this.query = query;
    }

    public ResultSet sendMyShowAllQuery() {
        String datasetURL = "http://localhost:3030/ProjetDataSet";
        String sparqlEndpoint = datasetURL + "/sparql";
        String sparqlQuery = datasetURL + "/update";
        String graphStore = datasetURL + "/data";
        ResultSet rs = null;
        try {
            RDFConnection conneg = RDFConnectionFactory.connect(sparqlEndpoint,
                    sparqlQuery, graphStore);
            QueryExecution qExec = conneg.query("prefix schema:  <http://schema.org/>" +
                    "prefix rdfs:  <http://www.w3.org/2000/01/rdf-schema#>" +
                    "prefix rdf:   <http://www.w3.org/1999/02/22-rdf-syntax-ns#>" +
                    "SELECT ?x ?comment ?type" +
                    "WHERE {" +
                    " ?x rdfs:comment ?comment ." +
                    " ?x rdf:type ?type } ORDER BY ?x");
            rs = qExec.execSelect();

            System.err.println("EXECUTING QUERY");
            conneg.close();

            return rs;
        } catch (Exception e) {
            System.err.println("!! ERROR : EXECUTING QUERY \n" + e.getMessage() + "\n");
            return null;
        }

    }
}
