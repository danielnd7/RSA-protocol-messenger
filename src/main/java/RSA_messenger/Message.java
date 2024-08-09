package RSA_messenger;

public class Message {
    private String to; // the Sender's userName
    private String from; // the recipient's userName
    private String text; // the text of the message

    // Create methods to create a message
    public Message(String to, String from, String text){
        if (to.equals(null)) {
            throw new RSAMessengerException("The sender's name is null");
        } else if (from.equals(null)) {
            throw new RSAMessengerException("The receiver's name is null");
        } else if (text.equals(null)) {
            throw new RSAMessengerException("The text is null");
        }

        this.to = to;
        this.from = from;
        this.text = text;
    }
}
