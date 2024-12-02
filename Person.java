import java.util.HashMap;
import java.util.Map;
import java.security.*;

// Class to represent a person
public class Person {
    private String name;
    private Map<String, Person> connections;
    private PublicKey publicKey;
    private PrivateKey privateKey;

     public Person(String name) throws NoSuchAlgorithmException {
        this.name = name;
        this.connections = new HashMap<>();
        generateKeys();
    }

    public String getName() {
        return name;
    }

    public void addConnection(Person person) {
        connections.put(person.getName(), person);
    }

    public Person getConnection(String name) {
        return connections.get(name);
    }
    
    public PublicKey getPublicKey() {
        return publicKey;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }
    
    private void generateKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();
        this.publicKey = pair.getPublic();
        this.privateKey = pair.getPrivate();
    }
}


