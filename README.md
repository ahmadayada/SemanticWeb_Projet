# Semantic Web Projet 2021

## RDF data management and processing

The Semantic Web project is a large and long practical exercise that consists in integrating all the pieces that have been seen during the first sessions into a consolidated Web application. To make sure you can advance sufficiently fast to cover everything, you are allowed to work by pair.

### Projet Info

    [Go The Project WebSite](https://www.emse.fr/~zimmermann/Teaching/SemWeb/Project/).

### Team Group

    Ibtissam
    Ahmad

## pre-install

```
    * Download Apache Jena frameWork from the the link:
        - [Download Apache Jena frameWork](https://dlcdn.apache.org/jena/binaries/apache-jena-fuseki-4.3.2.zip).
        - Unzip the *apache-jena-fuseki[].zip* __beside__ the semantic Web project folder
    * Download from the internet "Terretoire resources" file and copy it to project root folder.
    * Download Project Docs Sensor Zip and unzipped the projet
        [download links](https://seafile.emse.fr/d/710ced68c2894189a6f4/)
        example: $SemanticWeb/project>cd project-docs
    * install maven (Apache-Maven-3.8.3) to your computer machine and Java version: 11.0.13 (jdk-11.0.13).
```

## Run Project Using the Maven plugin

    In Jana jena-fuseki Folder
        $>cd ~/SemanticWeb/apache-jena-fuseki-4.3.2
        $~/SemanticWeb/apache-jena-fuseki-4.3.2> ./fuseki-server

    In root folder of project example : cd ~[path_to]/SemanticWeb/project/
    ```
        $>cd ~/SemanticWeb/project/
        $cd ~/SemanticWeb/project>mvn spring-boot:run
    ```

## Run Project Using Java (if you have also mvn)

    ```
        $> cd ~[path_to]/SemanticWeb/project/
        $> mvn install
        $> java -jar target/project-0.0.1-SNAPSHOT.jar
    ```

<br /><hr>
