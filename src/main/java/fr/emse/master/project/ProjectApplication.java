package fr.emse.master.project;

import java.io.InputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.RDFDataMgr;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
		System.err.println("Hellow there \n");
		// System.out.println("Working Directory = " + System.getProperty("user.dir"));
		// Reading RDF
		// create an empty model
		Model model = ModelFactory.createDefaultModel();
		// use the RDFDataMgr to find the input file
		String inputFileName = "./Plateforme_Territoire/ontology.rdf";
		InputStream in = RDFDataMgr.open(inputFileName);

		
	}

}
