package RSA_messenger;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MessagesPair implements Serializable {
    private List<Message> receivedMessages;
    private List<Message> sentMessages;

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

    public List<Message> getUncheckedReceivedMessages() {
        List<Message> auxList = new ArrayList<>();

        for (Message message : receivedMessages) {
            if (!message.isChecked()) {
                auxList.add(message);
            }
        }
        return auxList;
    }

    public void updateReceivedMessages(int selectedIndex) {
        Message messageToCheck = getUncheckedReceivedMessages().get(selectedIndex);
        boolean checked = false;
        int index = 0;

        while(index < receivedMessages.size() && !checked) {
            if (receivedMessages.get(index).equals(messageToCheck)) {
                receivedMessages.get(index).setChecked(true);
                checked = true;
            }
            index++;
        }
    }

    public List<Message> getSentMessages() {
        return sentMessages;
    }

    // Testing ONLY method
    @Override
    public String toString() {
        return "received: " + receivedMessages + " ; sent: " + sentMessages + ". ";
    }

}
