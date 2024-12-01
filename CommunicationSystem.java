import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.util.List;
import java.util.Random;


// Class to handle message sending and compression
public class CommunicationSystem {

    // Method to compress a message using run-length encoding
    public static String runLengthEncode(String message) {
        StringBuilder encoded = new StringBuilder();
        int count = 1;
        for (int i = 1; i < message.length(); i++) {
            if (message.charAt(i) == message.charAt(i - 1)) {
                count++;
            } else {
                encoded.append(message.charAt(i - 1)).append(count);
                count = 1;
            }
        }
        encoded.append(message.charAt(message.length() - 1)).append(count);
        return encoded.toString();
    }

    // Method to send a compressed message from one person to another
    public static void sendMessage(Person sender, Person receiver, String messageBody) throws IOException{
        String compressedMessage = runLengthEncode(messageBody);
        String metadata = "Run-Length Encoded";
        Message message = new Message(sender.getName(), receiver.getName(), metadata, compressedMessage);
        System.out.println("Message sent from " + message.getSender() + " to " + message.getReceiver());
        System.out.println("Metadata: " + message.getMetadata());
        System.out.println("Compressed Message: " + message.getBody());
    }
    
    // Method to choose a sender and receiver from a list of people
    public static void chooseAndSendMessage(List<Person> people, String messageBody) throws IOException {
        Random random = new Random();
        Person sender = people.get(random.nextInt(people.size()));
        Person receiver;
        do {
            receiver = people.get(random.nextInt(people.size()));
        } 
            while (sender == receiver);

        sendMessage(sender, receiver, messageBody);
    }
}
