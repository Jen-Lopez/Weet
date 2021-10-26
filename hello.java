import java.util.Scanner;

import java.util.ArrayList;


public class hello {
    public static void main (String[] args) {
        Scanner obj = new Scanner(System.in);
        System.out.println("Welcome to Weet! Please enter your name");
        String username = obj.nextLine();
        System.out.println("Welcome, " + username + "!");
        System.out.println("Please enter any food allergies you may have one by one. In order to stop, enter 'X'.");
        String allergy = "";
        ArrayList<String> allergies = new ArrayList<String>();
        while (true) {
            if (("X").equals(allergy)) {
                break;
            }
            else {
                allergy = obj.nextLine();
                allergies.add(allergy);
            }
        }
        //obj.close();

        for (int i = 0; i < allergies.size(); i++) {
            System.out.println(allergies.get(i));
        }
    }
}


