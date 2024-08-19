package RSA_messenger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class PrivateUser extends User{
    // Name of the file with data of privateUser
    private static final String userDataFile = "userData.txt";

    //protected String userName;
    //protected KeyPair publicKey;
    private KeyPair privateKey;

    // Constructor
    public PrivateUser(String userName, KeyPair privateKey, KeyPair publicKey) {
        super(userName, publicKey);
        this.privateKey = privateKey;
    }

    // All methods
    public KeyPair getPrivateKey() {
        return privateKey;
    }

    public static PrivateUser loadFromFile() throws IOException {
        PrivateUser loadedUser = null;

        String userName;
        int privateKey;
        int module;
        int publicKey;

        Path filePath = Path.of(userDataFile);
        try (Scanner scanner = new Scanner(filePath)){
            userName = scanner.next();
            module = scanner.nextInt();
            privateKey = scanner.nextInt();
            publicKey = scanner.nextInt();

            loadedUser = new PrivateUser(userName, new KeyPair(module, privateKey), new KeyPair(module, publicKey));

        } catch (NoSuchElementException e){
            throw new RSAMessengerException("Incorrect data in user data file");
        } catch (IllegalStateException e){
            throw new RSAMessengerException("Incorrect data in user data file");
        }

        return loadedUser;
    }

    public void storeInFile(){
        try (PrintWriter printWriter = new PrintWriter(userDataFile)){
            printWriter.println(userName);
            printWriter.println(privateKey.getModule() + " " + privateKey.getKey() + " " + publicKey.getKey());

        } catch (FileNotFoundException e) {
            throw new RSAMessengerException("Error: user data file couldn't be written");
        }
    }

}
