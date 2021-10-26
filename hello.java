import java.util.Scanner;



public class hello {
    public static void main (String[] args) {
        Scanner obj = new Scanner(System.in);
        System.out.println("Welcome to Weet! Please enter your name");
        String username = obj.nextLine();
        System.out.println("Welcome, " + username + "!");
        obj.close();

    }
}


