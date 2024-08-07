package RSA_messenger;

import java.util.List;

public class Server { // Server mock
    private List<PublicUser> usersList;
    private List<Message> allMessages;

    // Basic methods
    public List<PublicUser> getUsersList() {
        return usersList;
    }
    public List<Message> getAllMessages() {
        return allMessages;
    }


}
