package RSA_messenger;

import java.util.*;
import java.util.random.*; // Provisional

public class App {
    // Instance variables

    // Non static
    private static Server server;
    private PrivateUser privateUser;

    // Constructor
    public App() {
        Server server = new Server();

        // If the private user its null create a new one
        if (privateUser.getUserName().equals(null)) {
            createUser();
        }
    }


    // All methods
    public void loadServerFromFile(){
        // if there is no the file, setServer will be called
        Server server1 = new Server();
    }
    public void loadPrivateUserFromFile(){
        //... In case of absence, calls CreateUser
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void send(){

        addRandomsUsers();

        // Server has a new method getUsersSet which returns a sorted set of users
        // It's a better option to use the new method, so the redundant lists can be removed from the Server class

        if (server.getUsersSet().isEmpty()) { // Theres no users, we have to create one
           System.out.println("The contacts list it's empty.");
        } else {

            System.out.println("Available contacts: ");
            int index = 1;
            for (PublicUser user : server.getUsersSet()) {
                System.out.println("(" + index + ")" + user); // now the PublicUser has a toString :)
                index++;
            }

            System.out.println("\nSelect one contact: \n");
            int contactIndex = 0;
            Scanner readerScanner = new Scanner(System.in);
            if (readerScanner.hasNextInt()) {
                contactIndex = readerScanner.nextInt() - 1; // We show from 1 to n contacts, so the real index its n-1
            }
            String receiverName = nameOfTheReceiver(server.getUsersSet(), contactIndex);
            System.out.println("\nType a message for " +  receiverName + " : \n");
            // I created nameOfReceiver because i dont find a better way to select an element from the set, cause
            // we cannot do like this: server.gerUserSet.get(index) because there no index on sets

            // Send messages with scanner (to do)
            readerScanner = new Scanner(System.in);
            String textMessage = null;
            if (readerScanner.hasNext()) {
                textMessage = readerScanner.next();
            }
            Message message = new Message(receiverName, privateUser.getUserName(),textMessage);

            System.out.println("\nSending messages...\n");

            // Need to establish the message, put in the map the receiver and the message in his arraylist of received messages
            // And put in the map the sender and his sent message on his arraylist of sent messages
        }
    }

    public void read(){
        System.out.println("\nReading messages...\n");

    }

    // Auxiliary methods
    public void createUser(){
        System.out.println("\nType your user name: \n");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            Random random = new Random();
            privateUser = new PrivateUser(scanner.next(),new KeyPair(random.nextInt(), random.nextInt()));
        }
    }

    // Not real methods, just to test the program
    private static void addRandomsUsers() {
        Map<PublicUser, MessagesPair> auxMap =  server.getAllUsersMessages();
        auxMap.put(new PublicUser("Lucas", new KeyPair(2,5)), new MessagesPair());
        auxMap.put(new PublicUser("Danichelo", new KeyPair(1,6)), new MessagesPair());
        server.setAllUsersMessages(auxMap);
    }

    private String nameOfTheReceiver(Set<PublicUser> publicUsers, int contactIndex) {
        Iterator iterator = publicUsers.iterator();
        PublicUser userRes = (PublicUser) iterator.next();
        int index = 0;

        while (index != contactIndex) {
            userRes = (PublicUser) iterator.next();
            index++;
        }
        return  userRes.getUserName();
    }
}
