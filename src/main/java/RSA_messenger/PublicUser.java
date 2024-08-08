package RSA_messenger;

import java.util.Objects;

public class PublicUser implements Comparable<PublicUser> {
     public String userName;
     public KeyPair publicKey;

     public PublicUser(String userName, KeyPair publicKey){
          this.userName = userName.toUpperCase();
          this.publicKey = publicKey;
     }

     // All methods
     public String getUserName() {
          return userName;
     }
     public KeyPair getPublicKeyKey() {
          return publicKey;
     }

     @Override
     public int compareTo(PublicUser anotherUser) {
          return userName.compareToIgnoreCase(anotherUser.userName);
     }

     @Override
     public boolean equals(Object object) {
          return (object instanceof PublicUser anotherUser) && (userName.equalsIgnoreCase(anotherUser.userName));
     }

     @Override
     public int hashCode() {
          return Objects.hash(userName.toUpperCase());
     }

     @Override
     public String toString() {
          return userName;
     }
}
