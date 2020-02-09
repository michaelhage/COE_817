/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RSA;

import java.util.Scanner;
import java.io.IOException;
import java.security.*;
import java.util.Base64;
import javax.crypto.Cipher;

/**
 *
 * @author Michael Hage
 */
public class JEncrypRSA {
    
    public static void main(String[] args){
        
        Scanner input = new Scanner(System.in);
        byte[] message, messageEncrypt = null, messageDecrypt = null;
        KeyPairGenerator keyGen = null;
        Cipher cipher = null;
        
        try{
            keyGen = KeyPairGenerator.getInstance("RSA");    
        }catch(Exception e){
            e.printStackTrace();;
        }

        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.genKeyPair();   

        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        System.out.println("Input Message: ");
        String tempMessage = input.nextLine();

        message = tempMessage.getBytes();

        System.out.println("Text in Bytes: "+ message);
        System.out.println("Text: "+ new String(message));
        
        try{
            cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            messageEncrypt = cipher.doFinal(message);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        System.out.println("Text in Bytes: "+ messageEncrypt);
        System.out.println("Text: "+ new String(messageEncrypt));
        
        try{
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            messageDecrypt = cipher.doFinal(messageEncrypt);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        System.out.println("Text Decrypted in Bytes: "+ messageDecrypt); // Print Decoded Message
        System.out.println("Text Decrypted: "+ new String(messageDecrypt));
            
        System.out.println("Is the Original and Decrypted Strings the same: "
                + new String(messageDecrypt).equals(new String(message))); // Check for Equivalence
    }
    
}