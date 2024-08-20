package RSA_messenger;

import java.io.Serializable;

public class KeyPair implements Serializable {
    private int module;
    private int key;

    // Constructor
    public KeyPair(int module, int key){
        this.module = module;
        this.key = key;
    }
    public KeyPair(){
        this(1234,2222);
    }
    // All methods
    public int getModule() {
        return module;
    }
    public int getKey() {
        return key;
    }
}
