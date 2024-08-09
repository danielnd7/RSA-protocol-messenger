package RSA_messenger;

import java.util.*;

public class Server { // Server mock
    private Map<User, MessagesPair> allUsersMessages;

    // Constructor
    public Server(){
        allUsersMessages = new HashMap<>();
    }

    // Basic methods
    public Set<User> getUsersSet() { // returns a sorted set of users (keys)
        return new TreeSet<>(allUsersMessages.keySet());
    }
    public Map<User, MessagesPair> getAllUsersMessages() {
        return allUsersMessages;
    }
    public void setAllUsersMessages(Map<User, MessagesPair> allUsersMessages) {
        this.allUsersMessages = allUsersMessages;
    }
    public void addUser(User user){
        allUsersMessages.put(user, new MessagesPair());
    }
    public void removeUser(User user){
        allUsersMessages.remove(user);
    }

    // Testing ONLY method
    public void printContent(){
        System.out.println(allUsersMessages);
    }
}
