# DroneProject
Drone Project

This project is build using springboot based on java 8. Furthermore, it uses MySQL 8 as its DB.

Before building the jar file, please configure application.yml file to point to your database with credentials. 
Furthermore, the current version does not fill up the database with dummy data. 

In order to run the sql queries to insert data, please rename "data.txt" and "schema.txt" files in the resource folder to "data.sql" and "schema.sql". 
Furthermore, change the property "spring.sql.init.mode" to "always" in application.yml. However, if the cannot be restarted without reverting those configurations.

in order to build the jar file use the following command in the project root directory.

mvn clean install

locate the jar file in the target directory and deploy it on a server or run in target directory "java -jar <enter jar name here>.jar"
the app will be deployed on port 8082 (change into the desired port in application.yml).

here are some sample rest urls.
	
 1) Get available drones- This GET endpoint will return all the drones which are in idle state, carries weight less than 500 and has a battery percentage more than or equal to 25.
  
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
	
	
  2) Register a Drone - This POST endpoint will register a drone with given input.
  
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
 
3) Add medicine to a drone - This POST endpoint will add medicine to an idle drone if criterias are met.
	please note that this url takes drone serial number as a path variable.

sample request url: http://localhost:8082/droneApi/addMedicine/12345
	
in the above url, 12345 is the serial number which is considered a unique column.

	
sample request body: {
	"name": "Panadol",
	"weight": 200,
	"code": "MED1",
	"imageUrl": "https://medlineplus.gov/images/Medicines_share.jpg"
}
	
please note that the image url only is stored in this demonstration.
	
	
sample response: {
	"medicineId": 3,
	"name": "Panadol",
	"weight": 200,
	"code": "MED1",
	"imageUrl": "https://medlineplus.gov/images/Medicines_share.jpg",
	"drone": {
		"droneId": 1,
		"serialNumber": "123456789",
		"model": "Lightweight",
		"weight": 200,
		"battery": 100,
		"state": "IDLE"
	}
}
	
4) Get medicine in a drone - This GET endpoint will return the medicine allocated to a drone.
	
	Please note that a seperate endpoint is needed to handle the clearing of the medicine in a drone. Alse that this url takes drone serial number as a path variable.


	
sample request url: http://localhost:8082/droneApi/getDroneMeds/123456789


sample response: [
	{
		"medicineId": 1,
		"name": "Panadol",
		"weight": 20,
		"code": "GGWP666",
		"imageUrl": "https://i-cf65.ch-static.com/content/dam/global/panadol/en_LK/760x820/300x300Panadol.png?auto=format",
		"drone": {
			"droneId": 1,
			"serialNumber": "123456789",
			"model": "Lightweight",
			"weight": 200,
			"battery": 100,
			"state": "IDLE"
		}
	},
	{
		"medicineId": 3,
		"name": "Panadol",
		"weight": 200,
		"code": "MED1",
		"imageUrl": "https://medlineplus.gov/images/Medicines_share.jpg",
		"drone": {
			"droneId": 1,
			"serialNumber": "123456789",
			"model": "Lightweight",
			"weight": 200,
			"battery": 100,
			"state": "IDLE"
		}
	}
]
	

5) Check battery of a given drone - This endpoint will return the battery level of a given drone serial number. note that the serial number is taken as a path variable.

	
sample request: http://localhost:8082/droneApi/getDroneBattery/123456789

sample response: {
	"battery": 100
}
  


	

The schedule task is run every one minute which prints all the battery levels of all the drones to a log file named "DroneTask.log".
The log file will be created in the directory where the jar file is. If tested using the IDE, it will be created in the root directory of project.

  

