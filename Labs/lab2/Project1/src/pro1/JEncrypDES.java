/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro1;

import java.util.Scanner;
import java.security.*;
import javax.crypto.*;

/**
 *
 * @author Michael
 */
public class JEncrypDES {
    
    private static Cipher encryptCipher, decryptCipher;
    
    public static void main(String[] args){
        String key, message, ciphertext = "";
        Scanner scan = new Scanner(System.in);
        
        try{
            encryptCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            
            decryptCipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            
        }catch(NoSuchAlgorithmException | NoSuchPaddingException e){
            e.printStackTrace();
        }
        
        System.out.println("Input message: ");
        message = scan.nextLine();
        
        System.out.println("Input Key: ");
        key = scan.nextLine();
        
        //Generate encrypted key
        
        //Encode message
        System.out.println("Cipher Message:\n");
        
        //decode cipher text
        System.out.println("Original Message:\n"+ ciphertext);
        System.out.println("Check For Equivalence:"+ (ciphertext.equals(message)));
        
    }
    
}
