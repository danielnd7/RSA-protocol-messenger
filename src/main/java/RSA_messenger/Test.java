package RSA_messenger;

import java.util.Scanner;

public class Test
{
    public static void main( String[] args )
    {
        int selectedOption = 0;
        System.out.println( "\nWelcome to RSA-protocol-messenger!" );

        // Preparing for work
        App app = new App();
        app.loadServerFromFile();
        app.loadPrivateUserFromFile();

        // while


        while (selectedOption != 3){

            System.out.println("What would you like to do?");
            System.out.println("(1) Read your messages");
            System.out.println("(2) Write a new message");
            System.out.println("(3) Finish");

            Scanner scanner = new Scanner(System.in);
            if (scanner.hasNextInt()){
                selectedOption = scanner.nextInt();
            }

            if (selectedOption == 1){ // read
                app.read();
            } else if (selectedOption == 2) { // send
                app.send();
            }

        }






    }
}
