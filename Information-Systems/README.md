# DS Airlines Booking System

The DS Airlines Booking System is a web-based application designed to meet the evolving needs of the airline industry. This system supports two main user roles: administrators and regular users, each with its own set of features and permissions. The project demonstrates the use of advanced technologies such as Flask, MongoDB, and Docker to deliver a flexible and efficient flight booking service.

## Features

### User Features:

- **Account Creation:** Users can create personal accounts with customized preferences.
- **Flight Search & Filters:** Search for flights based on criteria like destination and date.
- **Booking & Cancellation:** Book and cancel tickets with real-time feedback and confirmation.
- **Seat Class Selection:** Choose between economy, middle, and business class, with varying prices and benefits.
- **Email Ticket Delivery:** Receive ticket information via email.
- **Manage Bookings:** View and manage personal reservations and travel history.
- **Update Profile:** Modify personal account details and preferences.
- **Account Deactivation:** Option to deactivate accounts and delete personal data.
- **Service Ratings:** Submit service reviews and earn reward points for each booking.
- **Newsletter Signup:** Subscribe to the airline's newsletter.
- **Contact Form:** Send messages to the airline through the contact form.

### Admin Features:

- **Flight Management:** Add and delete scheduled flights, with detailed control over flight details such as departure/arrival times and seat prices.
- **User Management:** Manage registered users and administrators.
- **View Reservations:** Access a comprehensive view of all user reservations.
- **Admin Management:** Add additional administrators.
- **Message & Review Management:** Manage communication messages and user reviews.
- **Send Newsletters:** Send newsletters to subscribed users

## Security & Access Control

- The system allows all users to search for flights, but only registered users can make bookings, ensuring personalized service and enhanced security.
- All logged-in users can log out of their accounts using the designated logout button.

## Technologies Used

- **Python:** For backend development due to its simplicity and power.
- **Flask:** A lightweight micro web framework in Python, facilitating quick and easy backend development.
- **JavaScript:** Used for creating dynamic frontend elements, enhancing user interaction and responsiveness.
- **HTML, CSS & Bootstrap:** To structure and style the web pages, ensuring a modern and responsive design.
- **MongoDB:** A NoSQL database used to store various data efficiently.
- **Docker:** To streamline the deployment and execution of the application across different environments.

## How to Run the Application

To run this web service locally, Docker and Docker Compose must be installed on your machine.   

### For Windows Systems
###### (Docker Compose is included in Docker Desktop for Windows)

You can download and install Docker from here:[Install Docker Desktop on Windows](https://docs.docker.com/desktop/install/windows-install/) .

### For Linux Systems

Execute the following commands in your terminal:

1. `sudo apt-get update`
2. `sudo apt install -y apt-transport-https ca-certificates curl
software-properties-common`
3. `curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key
add - `
4. `sudo add-apt-repository -y "deb [arch=amd64]
https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"`
5. `sudo apt-get update`
6. `sudo apt install docker-ce`
7. ``` sudo curl -L https://github.com/docker/compose/releases/download/v2.5.0/docker-compose-`uname-s`-`uname -m` -o /usr/local/bin/docker-compose ```
8. ` sudo chmod +x /usr/local/bin/docker-compose`

## Application Requirements

- **Flask:** Runs on port `5000`.
- **MongoDB:** Runs on port `27017`.
- Ensure no other services are running on these ports before starting the containers.

The MongoDB database is named `DS_Airlines`. 

## Running the Application

* Download the .zip file from this repository.
* Extract the contents to your machine.
* Open a terminal at the project path: `Information-Systems/DS-Airlines-Booking-System`.
* Run the following command to start the containers:` (sudo) docker-compose up -d ` .
* Τo stop the containers, run: ` (sudo) docker-compose down ` .
* Open your browser and navigate to : http://localhost:5000/ .

## MongoDB

Navigate to the project path: `Information-Systems/DS-Airlines-Booking-System` .

To interact with MongoDB via the shell, use the following commands:

* Start the MongoDB shell : `docker exec -it mongodb mongo` .
* Switch to the `DS_Airlines` database : `use DS_Airlines` .
* Show collections : ` show collections ` .
* List all users : ` db.users.find({}) ` .
* List all flights created by the admin  : ` db.flights.find({}) ` .
* List all bookings created by users: ` db.reservations.find({}) ` .
* List all pending refund requests  : ` db.refund.find({}) ` .

For a prettier display, add : `.pretty() ` .

#### The database includes preloaded data such as users, flights, reviews, bookings, messages, newsletter subscribers, and cancellations. ####

## Flask

- To interact with Flask, execute the following command from the project directory via the terminal or PowerShell :  ` docker exec -it flask /bin/bash ` .

## Containerize the Web Service

To build the image for this project, use the following commands:

* Build the image  : ` docker build -t ds_airlines .` .
* List the available images  : ` docker images ` .
* To remove an image : ` docker rmi (imageid) ` .

## Data

All data for the application is stored in a local  `data ` folder , which is automatically created when running  : `docker-compose up -d `. This ensures that your data remains intact even if the database container is removed or replaced. 

## Contributors

This project was developed as a team effort during the course "Information Systems" at the University of Piraeus, Department of Digital Systems.
