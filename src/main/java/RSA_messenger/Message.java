package RSA_messenger;

public class Message {
    private String to; // the Sender's userName
    private String from; // the recipient's userName
    private String text; // the text of the message
    private boolean checked; // tells if the user has seen the message

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
        this.checked = false;
    }

    public String getFrom() {
        return from;
    }

    public boolean isChecked() {
        return checked;
    }
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public String toString() {
        return text;
    }
}
