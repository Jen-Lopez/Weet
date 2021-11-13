package io.weet.demo;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.weet.demo.models.User;
import io.weet.demo.services.UserRepository;

// @SpringBootApplication
public class WeetCommandLine implements CommandLineRunner {

	@Autowired
	private UserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(WeetCommandLine.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll();
		boolean running = true;
		String input;
		String username;
		String password;
		String NewFirstName;
		String NewLastName;
		String NewUsername = "";
		String NewPassword;
		boolean goodUser = false;
		boolean loggedIn = false;
		// save some users
		repository.save(new User("Alice", "Smith", "alicesmith", "pass"));
		repository.save(new User("Bob", "Smith", "bob", "pswd1234"));
		// main running loop
		while(running) {
			Scanner scanner = new Scanner(System.in);
			goodUser = false;
			loggedIn = false;
			System.out.println("\nHi, type sign up, login, or exit.");
			input = scanner.nextLine();

			if(input.toLowerCase().equals("exit"))
				running = false;
			else if(input.equalsIgnoreCase("sign up")) {
				System.out.println("Please enter your first name.");
				NewFirstName = scanner.nextLine();
				System.out.println("Please enter your last name.");
				NewLastName = scanner.nextLine();
				while (!goodUser) {
					System.out.println("Please enter a username.");
					NewUsername = scanner.nextLine();
					if (repository.findByusername(NewUsername).size() > 0) {
						System.out.println("Sorry, username already taken. Please take another one.");
					}
					else
						goodUser = true;
				}
				System.out.println("Please enter a password.");
				NewPassword = scanner.nextLine();
				repository.save(new User(NewFirstName, NewLastName, NewUsername, NewPassword));
			}
			else if(input.toLowerCase().equals("login")) {
				while(!loggedIn) {
					System.out.println("\nPlease type your username and password. Type exit to quit the program.");

					System.out.print("Enter username: ");
					username = scanner.nextLine();
					if(username.toLowerCase().equals("exit")) {
						running = false;
						break;
					}

					System.out.print("Enter password: ");
					password = scanner.nextLine();

					if(repository.findByusername(username).get(0).checkPassword(password)) {
						System.out.println("Login Success!");
						loggedIn = true;
					}
					else
						System.out.println("Wrong username or password");
				}

			}
			else
				System.out.println("Please enter a valid input.");


		}

		/* // Example code
		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (User customer : repository.findAll()) {
			System.out.println(customer);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByFirstName('Alice'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByFirstName("Alice"));

		System.out.println("Customers found with findByLastName('Smith'):");
		System.out.println("--------------------------------");
		for (User customer : repository.findByLastName("Smith")) {
			System.out.println(customer);
		}
		*/
	}
}
