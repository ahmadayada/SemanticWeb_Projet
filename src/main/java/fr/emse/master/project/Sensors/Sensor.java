package fr.emse.master.project.Sensors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.jena.datatypes.xsd.XSDDatatype.XSDGenericType;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

public class Sensor {

    String name, time, HMDT, LUMI, SND, SNDF, SNDM, TEMP, id, location, type;
    String inputFileName = "./project-docs/20211116-daily-sensor-measures.csv";

    String ns1 = "http://schema.org/";
    String sosa = "http://www.w3.org/ns/sosa/";
    String xsd = "http://www.w3.org/2001/XMLSchema#";
    String emseUri = "https://territoire.emse.fr/kg/";
    String ontol = "http://www.ontologydesignpatterns.org/ont/dul/DUL.owl#";

    public void create(Model model) {
        model.setNsPrefix("sosa", sosa);
        model.setNsPrefix("salle", emseUri + "/emse/fayol/");
        model.setNsPrefix("obs", emseUri + "emse/fayol/observation/");
        model.setNsPrefix("dul", ontol);
        model.setNsPrefix("schema", "http://schema.org/");
        int obsNumber = 0;
        /* e4=ET4 et S431H=451H il faut modifier ça à la lecture */
        try (BufferedReader r = new BufferedReader(new FileReader(inputFileName))) {
            String line = r.readLine();
            int count = 0;

            while ((line = r.readLine()) != null) {
                if (line.isEmpty() || count == 0) {
                    continue;
                }
                count++;

                String[] splitLine = line.split(",");
                String stopId = splitLine[0].replace(" ", "_");
                name = splitLine[0];
                time = splitLine[1];
                HMDT = splitLine[2];
                LUMI = splitLine[3];
                SND = splitLine[4];
                SNDF = splitLine[5];
                SNDM = splitLine[6];
                TEMP = splitLine[7];
                id = splitLine[8];
                location = splitLine[9];
                type = splitLine[10];

                Resource root = model.createResource(emseUri + "emse/fayol/sensor-" + id.replace(" ", ""));
                root.addProperty(RDF.type, model.createResource(sosa + "Sensor"));
                if (!location.isEmpty()) {
                    // define sensor location
                    root.addProperty(model.createProperty(ontol + "hasLocation"), model.createResource(
                            emseUri + "emse/fayol/" + location));
                }
                if (!TEMP.isEmpty()) {
                    count += 1;
                    Resource observation = model.createResource(emseUri + "emse/fayol/observation/"
                            + "observation" + "-" + Integer.toString(obsNumber));

                    // define type "observation"
                    observation.addProperty(RDF.type,
                            model.createResource(sosa + "Observation"));
                    // define the sensor witch made the observation
                    observation.addProperty(model.createProperty(sosa + "madeBySensor"),
                            root);
                    // property observed
                    observation.addProperty(model.createProperty(sosa + "observedProperty"),
                            model.createResource(emseUri + "emse/fayol/"
                                    + location + "#temperature"));
                    // time "2017-06-06T12:36:12Z"^^xsd:dateTime
                    // Date observation
                    long milliseconds = Long.parseLong(time);

                    Date mydate = new Date(milliseconds / 1000000);
                    SimpleDateFormat sfd = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.FRANCE);
                    sfd.setTimeZone(TimeZone.getTimeZone("UTC"));
                    String date = sfd.format(mydate);
                    // String dateOftime = date.split("T")[0];
                    // String hour = date.split("T")[1].split(":")[0];
                    observation.addProperty(model.createProperty(sosa + "resultTime"),
                            date, XSDGenericType.XSDdateTime);
                    observation.addProperty(model.createProperty("http://schema.org/" + "value"), TEMP,
                            XSDGenericType.XSDfloat);

                }

            }

        } catch (Exception e) {
            System.err.println(" !! ERROR : " + e.toString());
        }

    }

    public void exportSensor(Model model) {
        try {
            OutputStream out = new FileOutputStream("sensor.ttl");

            // Converts the string into bytes

            // Writes data to the output stream
            // out.write(dataBytes);
            model.write(out, "TTL");
            // Closes the output stream
            out.close();

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

}
