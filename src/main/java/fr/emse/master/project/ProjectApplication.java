package fr.emse.master.project;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;
import org.apache.jena.riot.RDFDataMgr;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectApplication {

	public static void listFiles(String path, List<File> filesList) throws InvocationTargetException {
		// List<String> filesList = new ArrayList<>();

		// path = "./Plateforme_Territoire/kg/";
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			// Reading RDF or TTL
			if (file.isFile() && file.getName().endsWith(".ttl")) {
				filesList.add(file);
				System.err.println(">" + file.getName());
			} else if (file.isDirectory()) {
				listFiles(file.getAbsolutePath(), filesList);
			}

			// create an empty model
		}
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ProjectApplication.class, args);
		System.err.println("Hellow there \n");

		String currentDir = System.getProperty("user.dir");
		System.out.println("Current dir using System:" + currentDir);

		List<File> filesList = new ArrayList<>();

		try {
			listFiles("./Plateforme_Territoire/kg/", filesList);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		System.out.println("files List size = " + filesList.size());
		System.out.println("\n" + filesList.get(0).getAbsolutePath() + "\n");
		Model model = ModelFactory.createDefaultModel();
		// model.read(filesList.get(0).getAbsolutePath());

		for (File file : filesList) {
			// System.out.println("file " + file);
			model.read(file.getAbsolutePath());
		}
		String datasetURL = "http://localhost:3030/ProjetDataSet";
		String sparqlEndpoint = datasetURL + "/sparql";
		String sparqlUpdate = datasetURL + "/update";
		String graphStore = datasetURL + "/data";
		RDFConnection conneg = RDFConnectionFactory.connect(sparqlEndpoint, sparqlUpdate, graphStore);
		conneg.load(model); // add the content of model to the triplestore
		conneg.update("INSERT DATA { <test> a <TestClass> }"); // add the triple to the triplestore
	}

}
