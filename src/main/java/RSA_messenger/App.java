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

        //createUser();
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
                int selectedIndex = readerScanner.nextInt() - 1;
                if (selectedIndex  < 0 ||  selectedIndex > server.getUsersSet().size()) {
                    throw  new RSAMessengerException("Invalid index: " + selectedIndex);
                }
                receivingUser = usersList.get(selectedIndex);
            }

            System.out.println("\nType a message for " +  receivingUser + " : ");

            // Reading typed messages with readerScanner
            readerScanner = new Scanner(System.in);
            String textMessage = null;
            if (readerScanner.hasNextLine()) {
                textMessage = readerScanner.nextLine();
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

    // To do
    public void read(){
        List<Message> receivedMessages = new ArrayList<>(server.getAllUsersMessages().get(privateUser).getUncheckedReceivedMessages());

        System.out.println("\nSearching for messages...");

        // Testing
        // System.out.println("\n " + receivedMessages + " \n");

        if (receivedMessages.isEmpty()) {
            System.out.println("\nThere's no new messages...\n");

            //Check for old messages (to do)

            System.out.println("Do you want to read old messages?");
            System.out.println("(1) YES");
            System.out.println("(2) NO");

            Scanner input = new Scanner(System.in);
            int selection = input.nextInt();
            if (selection < 1 || selection > 2) {
                throw new RSAMessengerException("Invalid input: just (1) YES or (2) NO");
            } else {
                if (selection == 1) {
                    for (Message message : server.getAllUsersMessages().get(privateUser).getReceivedMessages()) {
                        System.out.println("\n- From " + message.getFrom() +  ": ");
                        System.out.println(message +  "\n");
                    }
                }
            }
        }
        // Check for new messages
        else {

            if (countNewMessages(receivedMessages) == 1) {
                System.out.println("\nYou have " + countNewMessages(receivedMessages) + " new message!");
            } else if (countNewMessages(receivedMessages) > 1){
                System.out.println("\nYou have " + countNewMessages(receivedMessages) + " new messages!");
            }
            System.out.println("\nSelect one contact: ");

            // Show the list
            for (int i = 0; i < receivedMessages.size(); i++) {
                System.out.println("(" + (i + 1) + ") " + receivedMessages.get(i).getFrom());
            }

            // Select one contact
            Scanner readerScanner = new Scanner(System.in);
            String receivedMessageText = null;
            Message receivedMessageCore = null;
            int selectedIndex = 0;
            if (readerScanner.hasNextInt()) {
                selectedIndex = readerScanner.nextInt() - 1;
                if (selectedIndex  < 0 ||  selectedIndex > receivedMessages.size()) {
                    throw  new RSAMessengerException("Invalid index: " + selectedIndex);
                }
                receivedMessageText = receivedMessages.get(selectedIndex).toString();
                receivedMessageCore = receivedMessages.get(selectedIndex);
            }

            // Read the message and remove it from the received messages list
            System.out.println("\nThe message received from " + receivedMessageCore.getFrom() +  " is: ");
            System.out.println(receivedMessageText);

            // Update the status of the message (unchecked to checked)
            // Update the status of the real received message list
            server.getAllUsersMessages().get(privateUser).updateReceivedMessages(selectedIndex);

            // This last line is for checking the sent message (from de public user that sent the message to us)
            // I think it is optional
            //server.getAllUsersMessages().get()
        }
    }

    private int countNewMessages(List<Message> receivedMessages) {
        return receivedMessages.size();
    }

    public void createUser(){
        String newUserName = null;
        System.out.println("\nLets create your private user...\n");

        boolean uniqueName = false;

        while (!uniqueName){
            System.out.println("Enter your user name: ");

            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNext()){
                newUserName = scanner.next();
            }

            // Checking if there is already a user with the same name:
            if (server.getAllUsersMessages().keySet().contains(new User(newUserName, new KeyPair()))){
                System.out.println("This name is already taken, please enter another one: ");
            } else {
                uniqueName = true;
            }
        }

        System.out.println("\nCreating a new user...\n");
        // Keys calculating here....

        // It's necessary to create two different user to prevent the private key from being uploaded to the server.
        privateUser = new PrivateUser(newUserName, new KeyPair(), new KeyPair());
        server.addUser(new User(newUserName, new KeyPair()));

        System.out.println("The new user was created successfully.\n");

    }

    // Auxiliary methods
    public void createUserDemo(){ // Testing ONLY method
        System.out.println("\nLets create your private user...\n");
        System.out.println("Type your user name: ");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextLine()) {
            Random random = new Random();
            privateUser = new PrivateUser(scanner.nextLine(),
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

}
