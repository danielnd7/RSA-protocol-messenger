package RSA_messenger;

import java.util.*;


public class App {
    // Instance variables

    // Non static
    private static Server server;
    private PrivateUser privateUser;

    // Constructor
    public App() {
        server = new Server();
        // If the private user its null create a new one

        createUser();
    }


    // All methods
    public void loadServerFromFile(){
        // if there is no the file, setServer will be called
        //Server server1 = new Server();
    }
    public void loadPrivateUserFromFile(){
        //... In case of absence, calls CreateUser
    }

    public static Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void send(){
        addRandomsUsers(); // Testing ONLY


        if (server.getUsersSet().isEmpty()) { // Theres no users, we have to create one
           System.out.println("The contacts list it's empty.");
        } else {

            System.out.println("Available contacts: ");

            ArrayList<User> usersList = new ArrayList<>(server.getUsersSet());
            for (int i = 0; i < usersList.size(); i++) {
                int indexToShow = i + 1;
                System.out.println("(" + indexToShow + ") " + usersList.get(i));
            }

            System.out.println("\nSelect one contact: ");

            User receivingUser = null;
            Scanner readerScanner = new Scanner(System.in);
            if (readerScanner.hasNextInt()){
                receivingUser = usersList.get(readerScanner.nextInt() - 1); // We show from 1 to n contacts, so the real index its n-1
            }

            System.out.println("\nType a message for " +  receivingUser + " : ");

            // Reading typed messages with readerScanner
            readerScanner = new Scanner(System.in);
            String textMessage = null;
            if (readerScanner.hasNext()) {
                textMessage = readerScanner.next();
            }

            Message message = new Message(receivingUser.getUserName(), privateUser.getUserName(), textMessage);

            System.out.println("\nSending messages...");
            // Adding the message to the Server's map:

            // Adding to the sender's messages
            server.getAllUsersMessages().get(privateUser).addSentMessage(message);
            // Adding to the recipient's messages
            server.getAllUsersMessages().get(receivingUser).addReceivedMessage(message);


            System.out.println("\nThe message has been sent succesfully!\n");
        }
    }

    private User publicUserOfTheReceiver(Set<User> usersSet, int contactIndex) {
        Iterator iterator = usersSet.iterator();
        User userRes = (User) iterator.next();
        int index = 0;

        while (index != contactIndex) {
            userRes = (User) iterator.next();
            index++;
        }
        return  userRes;
    }

    public void read(){
        System.out.println("\nReading messages...\n");

    }

    // Auxiliary methods
    public void createUser(){
        System.out.println("\nLets create your private user...\n");
        System.out.println("Type your user name: ");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            Random random = new Random();
            privateUser = new PrivateUser(scanner.next(),
                    new KeyPair(random.nextInt(), random.nextInt()),
                    new KeyPair(random.nextInt(), random.nextInt()));
        }
    }

    // Not real methods, just to test the program
    // made public and not static for testing
    public static void addRandomsUsers() {
        server.addUser(new User("Lucas", new KeyPair(2,5)));
        server.addUser(new User("Danichelo", new KeyPair(1,6)));
        server.addUser(new User("Tolo", new KeyPair(5,6)));
    }

    private String nameOfTheReceiver(Set<User> users, int contactIndex) {
        Iterator iterator = users.iterator();
        User userRes = (User) iterator.next();
        int index = 0;

        while (index != contactIndex) {
            userRes = (User) iterator.next();
            index++;
        }
        return  userRes.getUserName();
    }
}
