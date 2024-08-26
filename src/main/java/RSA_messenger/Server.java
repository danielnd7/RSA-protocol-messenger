package RSA_messenger;

import java.io.*;
import java.util.*;

public class Server implements Serializable { // Server mock
    private Map<User, MessagesPair> allUsersMessages;

    // Constructor
    public Server(){
        allUsersMessages = new HashMap<>();
    }

    // Basic methods
    public Set<User> getUsersSet() { // returns a sorted set of users (keys)
        return new TreeSet<>(allUsersMessages.keySet());
    }
    public Map<User, MessagesPair> getAllUsersMessages() {
        return allUsersMessages;
    }
    public void setAllUsersMessages(Map<User, MessagesPair> allUsersMessages) {
        this.allUsersMessages = allUsersMessages;
    }
    public void addUser(User user){
        allUsersMessages.put(user, new MessagesPair());
    }
    public void removeUser(User user){
        allUsersMessages.remove(user);
    }

    // Testing ONLY method
    public void printContent(){
        System.out.println(allUsersMessages);
    }

    public void serializeServer() {
        try (FileOutputStream fileOutput = new FileOutputStream("user_own_data/server.ser");
             ObjectOutputStream objectOutput = new ObjectOutputStream(fileOutput)){

            objectOutput.writeObject(this);

            System.out.println("Serialized data is saved in server.ser (TESTING OUTPUT)\n");


        } catch (IOException e) {
            e.printStackTrace();
            throw new RSAMessengerException("Error: server data file couldn't be written\n");
        }
    }


    public static Server deserializeServer(){
        Server server = null;

        try (FileInputStream fileInput = new FileInputStream("user_own_data/server.ser");
             ObjectInputStream objectInput = new ObjectInputStream(fileInput)){

            server = (Server) objectInput.readObject();

            System.out.println("Server was deserialized from server.ser (TESTING OUTPUT)\n");

        } catch (IOException e) {
            throw new RSAMessengerException("Server data file couldn't be loaded :(\n");
        } catch (ClassNotFoundException c) {
            throw new RSAMessengerException("Server data file not found :(\n");
        }


        return server;
    }
}