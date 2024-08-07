package RSA_messenger;

public class Test
{
    public static void main( String[] args )
    {
        System.out.println( "Welcome to RSA-protocol-messenger!" );

        // Preparing for work
        App app = new App();
        app.loadServerFromFile();
        app.loadPrivateUserFromFile();

        // while
        System.out.println("What would you like to do?");
        System.out.println("(1) To Read your messages");
        System.out.println("(2) To Write a message");
        System.out.println("(3) Finish");






    }
}
