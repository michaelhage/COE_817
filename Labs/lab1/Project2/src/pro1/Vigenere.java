/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro1;

import java.util.Scanner;
/**
 *
 * @author Michael Hage
 */
public class Vigenere {
    
    public static String keyGen(String message, String key){
        
        int x = message.length();
        int i = key.length();
        int y = i;
        
        while(x > i){
            key+= key.charAt(i++ % y);
        }
        return key;
    }
    
    public static String encode(String message, String key){
        String cipher = "";
        
        for(int i = 0; i < message.length(); i++){
            if(message.charAt(i) == ' '){
                cipher += ' ';
            }
            else{
                int x = (message.charAt(i) + key.charAt(i) - 2*'A') % 26;

                x += 'A';

                cipher += (char)x;
            }
        }
        
        return cipher;
    }
    
    public static String decode(String cipher, String key){
        String message = "";
        
        for(int i = 0; i < cipher.length(); i++){
            
            if(cipher.charAt(i) == ' '){
                message += ' ';
            }
            else{
                int x = (cipher.charAt(i) - key.charAt(i) + 26) % 26;
            
                x += 'A';
            
                message += (char)x;
            }
        }
        
        return message;
    }
    
    public static void main(String[] args){
        String key, message, cipher;
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Input message: ");
        message = scan.nextLine();
        
        System.out.println("Input Key: ");
        key = scan.nextLine();
        
        key = keyGen(message, key);
        System.out.println(key);
        
        cipher = encode(message, key);
        System.out.println("Cipher Message:\n"+cipher);
        
        cipher = decode(cipher, key);
        System.out.println("Original Message:\n"+ cipher);
        System.out.println("Check For Equivalence:"+ (cipher.equals(message)));
        
    }
}
