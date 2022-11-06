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
 1) Get available drones- This GET method url will return all the drones which are in idle state, carries weight less than 500 and has a battery percentage more than or equal to 25.
  
  sample request url:http://localhost:8082/droneApi/getAvailableDrones
  Sample response: 
  [
	{
		"droneId": 1,
		"serialNumber": "123456789",
		"model": "Lightweight",
		"weight": 0,
		"battery": 100,
		"state": "IDLE"
	},
	{
		"droneId": 2,
		"serialNumber": "987654321",
		"model": "Cruiserweight",
		"weight": 0,
		"battery": 100,
		"state": "IDLE"
	}
  ]
	
	
  2) Register a Drone - This POST method url will register a drone with given input.
  
  sample request url: http://localhost:8082/droneApi/registerDrone
  sample request body: 
  {
	"serialNumber":"1234",
	"model":"Lightweight",
	"weight":0,
	"battery":100,
	"state":"IDLE"
  }
  sample response: 
  {
	"droneId": 3,
	"serialNumber": "1234",
	"model": "Lightweight",
	"weight": 0,
	"battery": 100,
	"state": "IDLE"
  }
   
  
  

