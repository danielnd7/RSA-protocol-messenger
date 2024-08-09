package RSA_messenger;

import java.util.*;

public class Server { // Server mock
    private Map<PublicUser, MessagesPair> allUsersMessages;

    // Constructor
    public Server(){
        allUsersMessages = new HashMap<>();
    }

    private List<PublicUser> usersList; // redundant... the same as allUsersMessages.keySet()
    private List<Message> allMessages; // redundant... the same as allUsersMessages.get(PublicUser)

    // Basic methods
    public Set<PublicUser> getUsersSet() { // returns a sorted set of users (keys)
        return new TreeSet<>(allUsersMessages.keySet());
    }
    public List<PublicUser> getUsersList() {
        return usersList;
    }
    public List<Message> getAllMessages() {
        return allMessages;
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
}
