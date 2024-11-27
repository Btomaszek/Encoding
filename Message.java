import java.util.HashMap;
import java.util.Map;

// Class to represent a message
class Message {
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
}

