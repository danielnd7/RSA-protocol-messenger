package RSA_messenger;

public class PrivateUser extends User{

    //protected String userName;
    //protected KeyPair publicKey;
    private KeyPair privateKey;

    // Constructor
    public PrivateUser(String userName, KeyPair privateKey, KeyPair publicKey) {
        super(userName, publicKey);
        this.privateKey = privateKey;
    }

    // All methods
    public KeyPair getPrivateKey() {
        return privateKey;
    }
}
