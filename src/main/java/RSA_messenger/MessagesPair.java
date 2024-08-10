package RSA_messenger;

import java.util.ArrayList;
import java.util.List;

public class MessagesPair {
    private List<Message> receivedMessages;
    private List<Message> sentMessages;

    // The methods setReceivedMessages and setSentMessages (both have been removed) seem to be unnecessary
    // because we can add or remove messages returned by get...Messages()
    // But the easiest way is to use add...Message

    public void addReceivedMessage(Message message){
        receivedMessages.add(message);
    }
    public void addSentMessage(Message message){
        sentMessages.add(message);
    }

    public MessagesPair() {
        receivedMessages = new ArrayList<>();
        sentMessages = new ArrayList<>();
    }

    public List<Message> getReceivedMessages() {
        return receivedMessages;
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    // Testing ONLY method
    @Override
    public String toString() {
        return "reseived: " + receivedMessages + " ; sent: " + sentMessages + ". ";
    }

}
