import java.util.HashMap;
import java.util.Map;

// Class to handle message sending and compression
class CommunicationSystem {

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
    public static void sendMessage(Person sender, Person receiver, String messageBody) {
        String compressedMessage = runLengthEncode(messageBody);
        String metadata = "Run-Length Encoded";
        Message message = new Message(sender.getName(), receiver.getName(), metadata, compressedMessage);
        System.out.println("Message sent from " + message.getSender() + " to " + message.getReceiver());
        System.out.println("Metadata: " + message.getMetadata());
        System.out.println("Compressed Message: " + message.getBody());
    }
}
