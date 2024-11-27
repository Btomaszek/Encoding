import java.util.HashMap;
import java.util.Map;


public class Encoding {
    public static void main(String[] args) {
        // Create people
        Person alice = new Person("Alice");
        Person bob = new Person("Bob");
        Person charlie = new Person("Charlie");

        // Add connections
        alice.addConnection(bob);
        bob.addConnection(charlie);

        // Send a compressed message from Alice to Charlie
        CommunicationSystem.sendMessage(alice, charlie, "Good Morning World!!!");
    }
}