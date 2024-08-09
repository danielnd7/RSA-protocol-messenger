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
        addRandomsUsers();

        // Server has a new method getUsersSet which returns a sorted set of users
        // It's a better option to use the new method, so the redundant lists can be removed from the Server class

        if (server.getUsersSet().isEmpty()) { // Theres no users, we have to create one
           System.out.println("The contacts list it's empty.");
        } else {

            System.out.println("Available contacts: ");
            int index = 1;
            for (User user : server.getUsersSet()) {
                System.out.println("(" + index + ")" + user); // now the PublicUser has a toString :)
                index++;
            }

            System.out.println("\nSelect one contact: \n");
            int contactIndex = 0;
            Scanner readerScanner = new Scanner(System.in);
            if (readerScanner.hasNextInt()) {
                contactIndex = readerScanner.nextInt() - 1; // We show from 1 to n contacts, so the real index its n-1
            }
            String receiverName = nameOfTheReceiver(server.getUsersSet(),contactIndex);
            System.out.println("\nType a message for " +  receiverName + " : ");
            // I created nameOfReceiver because i dont find a better way to select an element from the set, cause
            // we cannot do like this: server.gerUserSet.get(index) because there no index on sets

            // Send messages with scanner (to do)
            readerScanner = new Scanner(System.in);
            String textMessage = null;
            if (readerScanner.hasNext()) {
                textMessage = readerScanner.next();
            }
            Message message = new Message(receiverName, privateUser.getUserName(), textMessage);

            System.out.println("\nSending messages...\n");

            // Need to establish the message, put in the map the receiver and the message in his arraylist of received messages
            // And put in the map the sender and his sent message on his arraylist of sent messages

            // Prepare the list of messages of the receiver and the sender
           // List<Message> receiverMessages = server.getAllUsersMessages().get(publicUserOfTheReceiver(server.getUsersSet(), contactIndex));
           // List<Message> sentMessages = server.getAllUsersMessages().get(privateUser);

            // Here we have a problem, when i try to get the received messages thats okey cause its a public user, and the map key its a public user
            // But when I want to update my private list of sentMessages we find that a privateUser and a publicUser are not the same things, so I cannot update
            // this list.
            // Maybe we can create a superclass User class that is extended by 2 subclasses which are privateUser and publicUser

            // ---------- NEW ---------------
            // Adding the message to the Server's map:
            // Adding to the sender's messages
            MessagesPair sendersMessages = server.getAllUsersMessages().get(privateUser); // a MessagePair of a user who sends the message
            sendersMessages.getSentMessages().add(message);
            // Adding to the recipient's messages
            User recipient = new User(receiverName, new KeyPair(5, 5)); // Only matters the NAME
            MessagesPair recipientMessages = server.getAllUsersMessages().get(recipient);
            recipientMessages.getReceivedMessages().add(message);




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
