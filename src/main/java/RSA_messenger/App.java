package RSA_messenger;

import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class App {
    // Instance variables

    // Non static
    private static Server server;
    private PrivateUser privateUser;

    // Constructor
    public App() {
        Server server = new Server();
        createUser();
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

        if (server.getUsersList().isEmpty()) { // Theres no users, we have to create one
           System.out.println("The contacts list it's empty.");
        } else {

            System.out.println("Available contacts: ");
            for (int i = 0; i < server.getUsersList().size(); i++) {
                System.out.println("(" + i+1 + ")" +  server.getUsersList().get(i)); // now the PublicUser has a toString :)
            }
            System.out.println("\nSelect one contact: \n");

            int contactIndex = 0;
            Scanner readerScanner = new Scanner(System.in);
            if (readerScanner.hasNextInt()) {
                contactIndex = readerScanner.nextInt() - 1; // We show from 1 to n contacts, so the real index its n-1
            }
            System.out.println("Type a message for " + nameOfTheReceiver(server.getAllUsersMessages().keySet(), contactIndex));


            System.out.println("\nSending messages...\n");
        }


    }

    public void read(){
        System.out.println("\nReading messages...\n");

    }

    // Auxiliary methods
    public void createUser(){

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
