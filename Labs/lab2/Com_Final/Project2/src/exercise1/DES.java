/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise1;

import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author h47tran
 */
public class DES {

    /**
     * Run file: Shift+F6
     * @param args the command line arguments
     */
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
            // Generate DES key.
            KeyGenerator kg = KeyGenerator.getInstance("DES");
            SecretKey DESKey = kg.generateKey();
            // This initializes the DES algorithm.
            Cipher DESCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            
            // Set DESCipher for encryption, using DESKey.
            DESCipher.init(Cipher.ENCRYPT_MODE, DESKey);
            byte[] plaintext = userInput.getBytes();
            // Generate ciphertext by encrypting using DESCipher.
            byte[] ciphertext = DESCipher.doFinal(plaintext);
            System.out.println("Ciphertext: " + new String(ciphertext));
            
            // Set DESCipher for decryption, using DESKey.
            DESCipher.init(Cipher.DECRYPT_MODE, DESKey);
            // Decrypt plain text.
            byte[] decrypt = DESCipher.doFinal(ciphertext);
            System.out.println("Decrypted text: " + new String(decrypt));
            
            if (userInput.equals(new String(decrypt)))
                System.out.println("User input and decrypted text are equal.");
            else
                System.out.println("Algorithm failed, message tampered.");
            
        } catch(Exception e) {
            
        }
    }
    
}
