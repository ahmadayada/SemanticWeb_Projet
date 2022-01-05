package fr.emse.master.project;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.RDFDataMgr;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication {

	public static void main(String[] args) throws InvocationTargetException, IOException {
		SpringApplication.run(ProjectApplication.class, args);
		System.err.println("Hellow there \n");
		String currentPath = new java.io.File(".").getCanonicalPath();
		System.out.println("Current dir:" + currentPath);

		String currentDir = System.getProperty("user.dir");
		System.out.println("Current dir using System:" + currentDir);

		// System.out.println("Working Directory = " + System.getProperty("user.dir"));
		// Reading RDF
		// create an empty model
		Model model = ModelFactory.createDefaultModel();

	}

}
