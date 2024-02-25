package kea.exercise.hogwartsstudentadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The StudentAdmin class is the entry point of the application.
 * It is annotated with @SpringBootApplication, which is a convenience annotation that adds all of the following:
 * - @Configuration: Tags the class as a source of bean definitions for the application context.
 * - @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
 * - @ComponentScan: Tells Spring to look for other components, configurations, and services in the 'kea.exercise.hogwartsstudentadmin' package.
 *
 * The main method uses SpringApplication.run() to launch the application.
 *
 * @see org.springframework.boot.SpringApplication
 * @see org.springframework.boot.autoconfigure.SpringBootApplication
 */
@SpringBootApplication
public class StudentAdmin {

    /**
     * The main method is the entry point of the application.
     * It delegates to Spring Boot's SpringApplication class by calling run.
     * SpringApplication bootstraps the application, starting Spring, which, in turn, starts the auto-configured Tomcat web server.
     * The run method returns an ApplicationContext where all the beans are registered and initialized.
     *
     * @param args an array of strings passed to the main method, which Spring Boot uses to create an ApplicationArguments instance, which is then used to add beans.
     */
    public static void main(String[] args) {
        SpringApplication.run(StudentAdmin.class, args);
    }

}