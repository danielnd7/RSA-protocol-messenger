package RSA_messenger;

import java.util.*;

public class Server { // Server mock
    private Map<PublicUser, MessagesPair> allUsersMessages;

    // Constructor
    public Server(){
        allUsersMessages = new HashMap<>();
    }

    // Basic methods
    public Set<PublicUser> getUsersSet() { // returns a sorted set of users (keys)
        return new TreeSet<>(allUsersMessages.keySet());
    }
    public Map<PublicUser, MessagesPair> getAllUsersMessages() {
        return allUsersMessages;
    }
    public void setAllUsersMessages(Map<PublicUser, MessagesPair> allUsersMessages) {
        this.allUsersMessages = allUsersMessages;
    }
    public void addUser(PublicUser user){
        allUsersMessages.put(user, new MessagesPair());
    }
    public void removeUser(PublicUser user){
        allUsersMessages.remove(user);
    }
}
