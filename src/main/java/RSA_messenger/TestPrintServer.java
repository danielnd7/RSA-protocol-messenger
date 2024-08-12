package RSA_messenger;

public class TestPrintServer {
    public static void main(String[] args) {


        // Create one of the existing users (Danichelo / Lucas / Tolo) to make it work


        App app = new App();
        App.addRandomsUsers();

        App.getServer().printContent();

        app.send();
        app.read();

        App.getServer().printContent();



    }
}
