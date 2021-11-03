package com.example.Weet;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WeetApplication implements CommandLineRunner {

	@Autowired
	private UserRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(WeetApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		repository.deleteAll();
		boolean running = true;
		String input;
		String username;
		String password;
		// save a couple of customers
		repository.save(new User("Alice", "Smith", "alicesmith", "pass"));
		repository.save(new User("Bob", "Smith", "bob", "pswd1234"));

		while(running) {
			System.out.println("\nHi, welcome to the login. Type exit at any time to quit the program.");
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter username: ");
			username = scanner.nextLine();
			if(username.toLowerCase().equals("exit"))
				running = false;

			System.out.print("Enter password: ");
			password = scanner.nextLine();
			if(password.toLowerCase().equals("exit"))
				running = false;

			if(repository.findByusername(username).get(0).checkPassword(password))
				System.out.println("Login Success!");
			else
				System.out.println("Wrong username or password");

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
