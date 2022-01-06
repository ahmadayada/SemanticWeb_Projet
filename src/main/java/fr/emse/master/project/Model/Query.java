package fr.emse.master.project.Model;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;

public class Query {
    String query;

    public Query(String query) {
        this.query = query;
    }

    public void sendMyQuery() {
        String datasetURL = "http://localhost:3030/ProjetDataSet";
        String sparqlEndpoint = datasetURL + "/sparql";
        String sparqlQuery = datasetURL + "/query";
        String graphStore = datasetURL + "/data";/*
                                                  * try {
                                                  * // RDFConnection conneg =
                                                  * RDFConnectionFactory.connect(sparqlEndpoint,
                                                  * // sparqlQuery, graphstore);
                                                  * QueryExecution qExec =
                                                  * conneg.query("prefix bot: <https://w3id. org/bot#>" +
                                                  * "prefix dul: <http://www. ontologydesignpatterns .org/ont/dul/DUL .owl#>"
                                                  * +
                                                  * "prefix sosa: <http://www.w3. org/ns/sosa/>" +
                                                  * " SELECT DISTINCT ?s { ?s a bot : Space . ?se a sosa: Sensor; dul: hasLocation ?s. }"
                                                  * );
                                                  * ResultSet rs = qExec.execSelect();
                                                  * 
                                                  * } catch (Exception e) {
                                                  * 
                                                  * }
                                                  */
    }
}
