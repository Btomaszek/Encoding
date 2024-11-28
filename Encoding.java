import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Encoding {
    public static void main(String[] args) {
        // Create people
        List<Person> people = new ArrayList<>();
        people.add(new Person("Alice"));
        people.add(new Person("Bob"));
        people.add(new Person("Charlie"));
        people.add(new Person("David"));
        people.add(new Person("Eve"));

        // Add connections (optional)
        people.get(0).addConnection(people.get(1));
        people.get(1).addConnection(people.get(2));
        people.get(2).addConnection(people.get(3));
        people.get(3).addConnection(people.get(4));

        // Allow user to input a personal message
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your personal message: ");
        String personalMessage = scanner.nextLine();

        // Send a compressed message from a randomly chosen sender to receiver
        CommunicationSystem.chooseAndSendMessage(people, personalMessage);
    }
}
