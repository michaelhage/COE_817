/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RSA;

import java.util.Scanner;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.util.Base64;

/**
 *
 * @author Michael Hage
 */
public class JEncryptRSA {
    
    public static void main(String[] args){
        
        Scanner input = new Scanner(System.in);
        byte[] message;
        
        try{
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            KeyPair keyPair = keyGen.genKeyPair();
            
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();
        }catch(Exception e){
            e.printStackTrace();;
        }
        
        String tempMessage = input.nextLine();
        
        message = tempMessage.getBytes();
        
        System.out.println("Text in Bytes: "+ message);
        System.out.println("Text: "+ new String(message));
        
    }
    
}
