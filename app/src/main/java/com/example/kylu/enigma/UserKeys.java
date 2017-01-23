package com.example.kylu.enigma;

/**
 * Created by kylu on 23.01.17.
 */

public class UserKeys {

    public String privateKey;
    public String publicKey;

    public UserKeys()
    {

    }

    public UserKeys(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;

    }
}
