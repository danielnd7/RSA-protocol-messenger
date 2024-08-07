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
    }
    public void loadPrivateUserFromFile(){
        //... In case of absence, calls CreateUser
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public void send(){

    }
    public void read(){

    }

    // Auxiliary methods
    public void createUser(){
        //  Creates new PrivateUser for this class and PublicUser for the server
    }
}
