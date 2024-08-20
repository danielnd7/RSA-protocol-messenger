package RSA_messenger;

import java.io.Serializable;
import java.util.Objects;

public class User implements Comparable<User>, Serializable {
     public String userName;
     public KeyPair publicKey;

     public User(String userName, KeyPair publicKey){
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
     public int compareTo(User anotherUser) {
          return userName.compareToIgnoreCase(anotherUser.userName);
     }

     @Override
     public boolean equals(Object object) {
          return (object instanceof User anotherUser) && (userName.equalsIgnoreCase(anotherUser.userName));
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
