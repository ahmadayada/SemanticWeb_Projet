package fr.emse.master.project.Sensors;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdfconnection.RDFConnection;
import org.apache.jena.rdfconnection.RDFConnectionFactory;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

public class Sensor {

    String name, time, HMDT, LUMI, SND, SNDF, SNDM, TEMP, id, location, type;
    String inputFileName = "./project-docs/20211116-daily-sensor-measures.csv";
    Model model = ModelFactory.createDefaultModel();

    public Sensor() {
        name = new String();
        time = new String();
        HMDT = new String();
        LUMI = new String();
        SND = new String();
        SNDF = new String();
        SNDM = new String();
        TEMP = new String();
        id = new String();
        location = new String();
        type = new String();
    }

    public void createModelFromCSV(String path) {
        String ns1 = "http://schema.org/";
        String sosa = "http://www.w3.org/ns/sosa/";
        String xsd = "http://www.w3.org/2001/XMLSchema#";
        try (BufferedReader r = new BufferedReader(new FileReader(inputFileName))) {
            String line = r.readLine();
            System.err.println("stopId | stopName | stoplan | stopLon");
            while ((line = r.readLine()) != null) {
                if (line.isEmpty())
                    continue;
                String sensorValue = "", sensorName = "";
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

                if (!HMDT.isEmpty()) {
                    sensorValue = HMDT;
                    sensorName = "HMDT";
                } else if (!LUMI.isEmpty()) {
                    sensorValue = LUMI;
                    sensorName = "LUMI";
                } else if (!SND.isEmpty()) {
                    sensorValue = SND;
                    sensorName = "SND";
                } else if (!SNDF.isEmpty()) {
                    sensorValue = SNDF;
                    sensorName = "SNDF";
                } else if (!SNDM.isEmpty()) {
                    sensorValue = SNDM;
                    sensorName = "SNDM";
                } else if (!TEMP.isEmpty()) {
                    sensorValue = TEMP;
                    sensorName = "TEMP";
                }

                /*
                 * String stopLon = splitLine[4];
                 * // System.err.println(stopId + " " + stopName + " " + stopLat + " " +
                 * stopLon);
                 * Resource root = model.createResource("http://www.example.com/" +
                 * stopId).addProperty(RDFS.label,
                 * model.createLiteral(stopName, "fr"));
                 * root.addProperty(model.createProperty(
                 * "http://www.w3.org/2003/01/geo/wgs84_pos#lat"), stopLat,
                 * XSDGenericType.XSDdecimal);
                 * root.addProperty(model.createProperty(
                 * "http://www.w3.org/2003/01/geo/wgs84_pos#long"), stopLon,
                 * XSDGenericType.XSDdecimal);
                 */
            }
            model.write(System.out);
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
