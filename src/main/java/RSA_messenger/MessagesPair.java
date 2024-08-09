package RSA_messenger;

import java.util.ArrayList;
import java.util.List;

public class MessagesPair {
    private List<Message> receivedMessages;
    private List<Message> sentMessages;
    

    public MessagesPair() {
        receivedMessages = new ArrayList<>();
        sentMessages = new ArrayList<>();
    }

    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }
    public void setReceivedMessages(List<Message> receivedMessages) {
        this.receivedMessages = receivedMessages;
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }
    public void setSentMessages(List<Message> sentMessages) {
        this.sentMessages = sentMessages;
    }

    // Testing ONLY method
    @Override
    public String toString() {
        return "reseived: " + receivedMessages + " ; sent: " + sentMessages + ". ";
    }

}
