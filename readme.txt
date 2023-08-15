Instructions to run the application:
Prerequisites:
•	Docker is installed and is running.
•	AWS account, Access key and secret key for AWS account
•	An AWS RDS instance
•	An EC2 instance running MongoDB
•	An EC2 instance running Kafka server


Steps:
•	Navigate to BookMyConsultation directory.
•	Modify the config.env file with appropriate values. 
•	Run the following command:
	sudo docker compose up -d
•	BMC application is ready to take API requests!
•	BMC is configured with below user credentials and roles to get started:
	Username – Leonard, password – password, Role - USER
	Username – Sheldon, password – password123, Role – USER
	Username – Rajesh, password – password12, Role – ADMIN
•	BMC API gateway is configured to run on port number 9191, users can use this port for all the endpoints.
•	To get the JWT token for above users, send an API request to /login endpoint with username and password fields in the request body


