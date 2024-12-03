import java.security.*;
import org.json.JSONObject; // Add the org.json library (download from Maven or include as a dependency)
import java.util.Base64;

public class SignedMessaging {

    // Step 1: Generate RSA Keys
    public static KeyPair generateKeys() throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        return keyGen.generateKeyPair();
    }

    // Step 2: Compute Hash of Message
    public static byte[] computeHash(String message) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(message.getBytes("UTF-8"));
    }

    // Step 3: Sign the Hash
    public static byte[] signHash(PrivateKey privateKey, byte[] messageHash) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(messageHash);
        return signature.sign();
    }

    // Step 4: Verify the Signature
    public static boolean verifySignature(PublicKey publicKey, byte[] messageHash, byte[] signature) throws Exception {
        Signature verifier = Signature.getInstance("SHA256withRSA");
        verifier.initVerify(publicKey);
        verifier.update(messageHash);
        return verifier.verify(signature);
    }

    // Utility to Convert Bytes to Hex String
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02x", b));
        }
        return hexString.toString();
    }

    public static void main(String[] args) throws Exception {
        // Example Sender and Receiver
        String sender = "Alice";
        String receiver = "Bob";
        String message = "This is the message body.";

        // Step 1: Generate RSA Keys for Sender and Receiver
        KeyPair senderKeys = generateKeys();
        KeyPair receiverKeys = generateKeys();

        // Print Keys (Optional)
        System.out.println("Sender Private Key: " + Base64.getEncoder().encodeToString(senderKeys.getPrivate().getEncoded()));
        System.out.println("Sender Public Key: " + Base64.getEncoder().encodeToString(senderKeys.getPublic().getEncoded()));

        // Step 2: Hash the Message
        byte[] messageHash = computeHash(message);
        System.out.println("Message Hash: " + bytesToHex(messageHash));

        // Step 3: Sign the Message Hash with Sender's Private Key
        byte[] signature = signHash(senderKeys.getPrivate(), messageHash);
        System.out.println("Signature: " + Base64.getEncoder().encodeToString(signature));

        // Step 4: Package Message, Metadata, and Signature as JSON
        JSONObject payload = new JSONObject();
        payload.put("sender", sender);
        payload.put("receiver", receiver);
        payload.put("message_body", message);
        payload.put("signature", Base64.getEncoder().encodeToString(signature));

        System.out.println("Message Payload:\n" + payload.toString(2));

        // Step 5: Receiver Verifies the Signature
        boolean isValid = verifySignature(senderKeys.getPublic(), messageHash, signature);
        System.out.println("Is the signature valid? " + isValid);
    }
}
