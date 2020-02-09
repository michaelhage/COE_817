/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encrypt;

import java.util.ArrayList;
import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author Michael Hage
 */
public class DES {
    private Cipher cipher;
    
    public DES(){
        try{
            cipher = Cipher.getInstance("DES/ECB/PKCS5Padding"); //Set Encryption Mode
        } catch(Exception e){
            e.printStackTrace();
        }
    }
    
    public void encryptMessage(ArrayList<byte[]> message, byte[] key){
        try{
            SecretKeySpec secKey = new SecretKeySpec(key, "DES");
            cipher.init(Cipher.ENCRYPT_MODE, secKey);
            
            for(int i = 0; i < message.size(); i++){
                message.set(i, cipher.doFinal(message.get(i)) );  
            }
        } catch(InvalidKeyException | BadPaddingException | IllegalBlockSizeException e){
            e.printStackTrace();
        }
    }
    
    public void decryptMessage(ArrayList<byte[]> cipherText, byte[] key){
        try{
            SecretKeySpec secKey = new SecretKeySpec(key, "DES");
            cipher.init(Cipher.DECRYPT_MODE, secKey);
            
            for(int i = 0; i < cipherText.size(); i++){
                cipherText.set(i, cipher.doFinal(cipherText.get(i)) );  
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
