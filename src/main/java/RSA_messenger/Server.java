package RSA_messenger;

import java.util.List;
import java.util.Map;

public class Server { // Server mock

    private Map<PublicUser, List<String>> receivedMessages;
    private Map<PublicUser, List<String>> sentMessages;

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
