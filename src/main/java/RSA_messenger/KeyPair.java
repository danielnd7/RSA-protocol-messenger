package RSA_messenger;

public class KeyPair {
    private int module;
    private int key;

    // Constructor
    public KeyPair(int module, int key){
        this.module = module;
        this.key = key;
    }
    public KeyPair(){
        this(0,0);
    }
    // All methods
    public int getModule() {
        return module;
    }
    public int getKey() {
        return key;
    }
}
