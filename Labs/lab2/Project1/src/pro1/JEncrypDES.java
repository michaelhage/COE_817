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
    
    public static void main(String[] args){
        String key, message, cipher;
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Input message: ");
        message = scan.nextLine();
        
        System.out.println("Input Key: ");
        key = scan.nextLine();
        
        //Generate encrypted key
        
        //Encode message
        System.out.println("Cipher Message:\n"+cipher);
        
        //decode cipher text
        System.out.println("Original Message:\n"+ cipher);
        System.out.println("Check For Equivalence:"+ (cipher.equals(message)));
        
    }
    
}
