import java.io.FileWriter;
import java.io.IOException;

// Class to represent a message
public class Message {
    private String sender;
    private String receiver;
    private String metadata;
    private String body;

    public Message(String sender, String receiver, String metadata, String body) {
        this.sender = sender;
        this.receiver = receiver;
        this.metadata = metadata;
        this.body = body;
    }

    public String getSender() {
        return sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public String getMetadata() {
        return metadata;
    }

    public String getBody() {
        return body;
    }
    
    public void writeToFile(String filename) throws IOException {
        try (FileWriter writer = new FileWriter(filename, true)) {
            writer.write("Message sent from " + sender + " to " + receiver + "\n");
            writer.write("Metadata: " + metadata + "\n");
            writer.write("Compressed Message: " + body + "\n\n");
        }
    }
}

