# DroneProject
Drone Project

This project is build using springboot based on java 8. Furthermore, it uses MySQL 8 as its DB.

Before building the jar file, please configure application.yml file to point to your database. 
Furthermore, the current version does not fill up the database with dummy data. 

In order to run the sql queries to insert data, please rename "data.txt" and "schema.txt" files in the resource folder to "data.sql" and "schema.sql". 
Furthermore, change the property "spring.sql.init.mode" to "always" in application.yml. However, if the cannot be restarted without reverting those configurations.

in order to build the jar file use the following command in the project root directory.

mvn clean install

locate the jar file in the target directory and deploy it on a server or run in target directory "java -jar <enter jar name here>.jar"
the app will be deployed on port 8082 (change into the desired port in application.yml).

here are some sample rest urls.


