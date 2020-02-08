/*
 * DES Encryption Method
 */
package DES;

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
        String tempMessage;
        Scanner scan = new Scanner(System.in);
        byte[] message, messageEncrypt, messageDecrypt;
        
        try{
            KeyGenerator keyGen = KeyGenerator.getInstance("DES"); //Create DES Key Generator
            SecretKey secKey = keyGen.generateKey(); //Generate Secret Key
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding"); //Set Encryption Mode
            
            cipher.init(Cipher.ENCRYPT_MODE, secKey); // Cipher is able to encrypt now
            
            System.out.println("Input message: "); // Input Message
            tempMessage = scan.nextLine();
            
            message = tempMessage.getBytes(); //Convert to Bytes Array
            
            System.out.println("Text in Bytes: "+ message);
            System.out.println("Text: "+ new String(message));
            
            messageEncrypt = cipher.doFinal(message); // Encrypt Message
            
            System.out.println("Text Encrypted in Bytes: "+ messageEncrypt); // Display Encrypted Message
            System.out.println("Text Encrypted: "+ new String(messageEncrypt));
            
            cipher.init(Cipher.DECRYPT_MODE, secKey); // Set Cipher to Decrypt Mode
            
            messageDecrypt = cipher.doFinal(messageEncrypt); // Decode Message
            
            System.out.println("Text Decrypted in Bytes: "+ messageDecrypt); // Print Decoded Message
            System.out.println("Text Decrypted: "+ new String(messageDecrypt));
            
            System.out.println("Is the Original and Decrypted Strings the same: "
                    + new String(messageDecrypt).equals(new String(message))); // Check for Equivalence
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
