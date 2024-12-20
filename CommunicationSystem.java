import java.util.HashMap;
import java.util.Map;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;


// Class to handle message sending and compression
public class CommunicationSystem {
    
    private static Person lastSender;
    private static Person lastReceiver;


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

    // Method to choose a sender and receiver from a list of people for a compressed message
    public static void chooseAndSendMessage(List<Person> people, String messageBody) throws IOException {
        Random random = new Random();
        Person sender = people.get(random.nextInt(people.size()));
        Person receiver;
        do {
            receiver = people.get(random.nextInt(people.size()));
        } while (sender == receiver);

        // Update last sender and receiver.
        lastSender = sender;
        lastReceiver = receiver;
        
        sendMessage(sender, receiver, messageBody);
        
    }

    // Helper method to get the last sender and receiver.
    public static Person[] getLastSenderReceiver() {
        return new Person[] {lastSender, lastReceiver};
    }

    // RSA Encryption
    public static byte[] encrypt(String message, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(message.getBytes());
    }

    // RSA Decryption
    public static String decrypt(byte[] encryptedMessage, PrivateKey privateKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(encryptedMessage));
    }

    public static void sendEncryptedMessage(Person sender, Person receiver, String messageBody) throws Exception {
        byte[] encryptedMessage = encrypt(messageBody, receiver.getPublicKey());
        String base64EncryptedMessage = Base64.getEncoder().encodeToString(encryptedMessage); // Encode in Base64
        String metadata = "RSA Encrypted";
        Message message = new Message(sender.getName(), receiver.getName(), metadata, base64EncryptedMessage);
        System.out.println("Message sent from " + message.getSender() + " to " + message.getReceiver());
        System.out.println("Metadata: " + message.getMetadata());
        System.out.println("Encrypted Message: " + message.getBody());
    }

        

}
