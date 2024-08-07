package RSA_messenger;

public class App {
    // Instance variables
    private Server server;
    private PrivateUser privateUser;

    // Constructor
    //


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
        System.out.println("sending messages");

    }
    public void read(){
        System.out.println("reading messages");

    }

    // Auxiliary methods
    public void createUser(){
        //  Creates new PrivateUser for this class and PublicUser for the server
    }
}
