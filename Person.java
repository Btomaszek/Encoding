import java.util.HashMap;
import java.util.Map;

// Class to represent a person
public class Person {
    private String name;
    private Map<String, Person> connections;

    public Person(String name) {
        this.name = name;
        this.connections = new HashMap<>();
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
}


