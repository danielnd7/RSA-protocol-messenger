package RSA_messenger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server { // Server mock
    private Map<PublicUser, MessagesPair> allUsersMessages;

    // Constructor
    public Server(){
        allUsersMessages = new HashMap<>();
    }

    private List<PublicUser> usersList;
    private List<Message> allMessages;

    // Basic methods
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
}
