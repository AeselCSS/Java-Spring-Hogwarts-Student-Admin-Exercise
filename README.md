# Hogwarts Student Admin

This is a Java Spring Boot application for managing Hogwarts students.

### Prerequisites

- Java 17 or higher
- MySQL

### Setup

#### 1. Clone the repository:
   ```
   git clone https://github.com/AeselCSS/Java-Spring-Hogwarts-Student-Admin-Exercise.git
   ```
#### 2. Navigate to the project directory:
    
    cd Java-Spring-Hogwarts-Student-Admin-Exercise
    
#### 3. Update the `src/main/resources/application.properties` file with your MySQL credentials:
    
    spring.datasource.url=jdbc:mysql://localhost:3306/your_database
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    
#### 4. Build the project:

    You can build the project using one of the following methods:

Option 1 - Using Maven:
      
      mvn clean install

Option 2 - Using mvnw (MacOs & Linux - or Windows with Git Bash or WSL):
      
    ./mvnw clean install
Option 3 - Using mwvnw.cmd (Windows):
      
      mvnw.cmd clean install
      
   
#### 5. Run the application:
    
    java -jar target/hogwarts-student-admin-0.0.1-SNAPSHOT.jar
    
The application should now be running at `http://localhost:3000`.

### Documentation

For more detailed information about the application, please refer to the [documentation](https://aeselcss.github.io/Java-Spring-Hogwarts-Student-Admin-Exercise/).

### Build Status

[![Build and deploy JAR app to Azure Web App - Hogwarts-student-admin](https://github.com/AeselCSS/Java-Spring-Hogwarts-Student-Admin-Exercise/actions/workflows/main_hogwarts-student-admin.yml/badge.svg)](https://github.com/AeselCSS/Java-Spring-Hogwarts-Student-Admin-Exercise/actions/workflows/main_hogwarts-student-admin.yml)

### Deployment

The application is deployed to Azure Web App. You can access it [here](https://hogwarts-student-admin.azurewebsites.net/).