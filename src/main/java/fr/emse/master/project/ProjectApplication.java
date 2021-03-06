package fr.emse.master.project;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.emse.master.project.Meteo.Meteo;
import fr.emse.master.project.Sensors.Sensor;

@SpringBootApplication
public class ProjectApplication {
	// Here Just a Method to get all .ttl file from path_Directory and their
	// sub_Directory in recursive
	public static void listFiles(String path, List<File> filesList) throws InvocationTargetException {
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			if (file.isFile() && file.getName().endsWith(".ttl")) {
				filesList.add(file);
			} else if (file.isDirectory()) {
				listFiles(file.getAbsolutePath(), filesList);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ProjectApplication.class, args);
		System.err.println("Hellow there \n");

		String currentDir = System.getProperty("user.dir");
		System.out.println("Current dir using System:" + currentDir);

		List<File> filesList = new ArrayList<>();
		Meteo meteo = new Meteo();
		System.err.println("Date Meteo Saint Etienne");
		System.err.println("day = " + meteo.jour + "/" + meteo.mois + "/" + meteo.annee + " : code " + meteo.code);
		System.err.println(meteo.createQuery());
		try {
			listFiles("./Plateforme_Territoire/kg/", filesList);
		} catch (InvocationTargetException e) {
			// e.printStackTrace();
			System.err.println("ERREUR IN LOADING PLATFORME TERRETOIRE Folder(s)");
		}
		System.err.println("Loading " + filesList.size() + "files to List is Successed !!");
		Model model = ModelFactory.createDefaultModel();
		Sensor sensorObs = new Sensor();
		sensorObs.create_ttl_from_sensorObs();

		for (File file : filesList) {
			// System.out.println("file " + file);
			model.read(file.getAbsolutePath());
		}
		System.err.println("Model TriplStore also loaded Successfully !!");
		System.err.println("\nAdding Meteo Reasult To triplestore");

		model.setNsPrefix("xsd", "http://www.w3.org/2001/XMLSchema#");
		model.setNsPrefix("meteo", "http://example.com/Meteo#");
		/*
		 * __________________________________________________________________________________________
		 */

		/*
		 * __________________________________________________________________________________________
		 */
		try {
			OutputStream out = new FileOutputStream("./outPutTTL/allPlateformeTerritoire.ttl");

			// Converts the string into bytes

			// Writes data to the output stream
			// out.write(dataBytes);
			model.write(out, "TTL");
			// Closes the output stream
			out.close();
		} catch (Exception e) {
			e.getStackTrace();
		}
		System.err.println("To See Results, please Open your Navigatore and type:\n\tlocalhost:3030/");
		// Avant Lancer l'application Spring soit sur que vous lancer le serveur
		String datasetURL = "http://localhost:3030/ProjetDataSet";
		String sparqlEndpoint = datasetURL + "/sparql";
		String sparqlUpdate = datasetURL + "/update";
		String graphStore = datasetURL + "/data";
		try {
			RDFConnection conneg = RDFConnectionFactory.connect(sparqlEndpoint, sparqlUpdate, graphStore);
			conneg.load(model); // add the content of model to the triplestore
			conneg.update("INSERT DATA { <test> a <TestClass> }"); // add the triple to the triplestore
			conneg.close();
		} catch (Exception e) {
			System.err.println("cannot connect to " + datasetURL);
			System.err.println("please verify your Apache Jena Fuskie Server is Started");
			System.err.println();
		}
	}

}
