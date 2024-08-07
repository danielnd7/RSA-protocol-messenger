package RSA_messenger;

public class PrivateUser {
    private String userName;
    private KeyPair privateKey;

    // Constructor
    public PrivateUser(String userName, KeyPair privateKey){
        this.userName = userName;
        this.privateKey = privateKey;
    }

    // All methods
    public String getUserName() {
        return userName;
    }
    public KeyPair getPrivateKey() {
        return privateKey;
    }
}
