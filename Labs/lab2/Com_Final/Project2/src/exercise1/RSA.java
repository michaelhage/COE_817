/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise1;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import javax.crypto.Cipher;

/**
 *
 * @author h47tran
 */
public class RSA {
    public static void main(String[] args) {
        // Uncomment below to get user input; but for convenience, plaintext is hard-coded
        /* 
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the plaintext message.");
        String userInput = in.nextLine();
        System.out.println(userInput);
        */
        String userInput = "No body can see me";
        System.out.println("Plaintext: " + userInput);
        try {
            KeyPairGenerator kg = KeyPairGenerator.getInstance("RSA");
            // Generate private and public key.
            KeyPair kp = kg.generateKeyPair();
            PrivateKey RSAPrivate = kp.getPrivate();
            PublicKey RSAPublic = kp.getPublic();
            
            // This initializes the RSA Algorithm.
            Cipher RSACipher = Cipher.getInstance("RSA");
            // Encode the message using the public key.
            RSACipher.init(Cipher.ENCRYPT_MODE, RSAPublic);
            byte[] plaintext = userInput.getBytes();
            byte[] ciphertext = RSACipher.doFinal(plaintext);
            System.out.println("Ciphertext: " + new String(ciphertext));
            
            // Decode the message using the private key.
            RSACipher.init(Cipher.DECRYPT_MODE, RSAPrivate);
            byte[] decrypt = RSACipher.doFinal(ciphertext);
            System.out.println("Decrypted text: " + new String(decrypt));
            
            if (userInput.equals(new String(decrypt)))
                System.out.println("User input and decrypted text are equal.");
            else
                System.out.println("Algorithm failed, message tampered.");
            
        } catch (Exception e) {
            
        }
    }
}
