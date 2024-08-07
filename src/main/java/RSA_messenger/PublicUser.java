package RSA_messenger;

public class PublicUser {
     public String userName;
     public KeyPair publicKey;

     public PublicUser(String userName, KeyPair publicKey){
          this.userName = userName;
          this.publicKey = publicKey;
     }

     // All methods
     public String getUserName() {
          return userName;
     }
     public KeyPair getPublicKeyKey() {
          return publicKey;
     }
}
