/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import sun.misc.*;

/**
 *
 * @author Michael Hage
 */
public class ChatServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        //Create Identificaiton Variables
        //A - Client
        //B - Server
        String idB = "RESPONDER B", idA = null;
        String publicKA, publicKB = null;
     
        String idAEncrypted;
        byte[] idABytes;
        
        String keyDES_EncryptS, keyDES_String;
        byte[] keyDES_Encrypt;
        //Create RSA KeyPair Variables
        KeyPairGenerator kg;
        KeyPair kp;
        PrivateKey PrA = null, PrB = null;
        PublicKey PuA = null, PuB = null;
        
        ServerSocket s = new ServerSocket(9999);
        // Incoming client socket gets accepted into 'ss'.
        Socket ss = s.accept();
        Scanner clientInput = new Scanner(ss.getInputStream());
        
        // PrintStream p in the same socket as client.
        PrintStream p = new PrintStream(ss.getOutputStream());
        
        Cipher RSACipher = null, DESCipher;
        SecretKey Key;
        
        //Generate KeyPair
        try{
            kg = KeyPairGenerator.getInstance("RSA");
            kp = kg.generateKeyPair();
            PrB = kp.getPrivate();
            PuB = kp.getPublic();
            publicKB = Base64.getEncoder().encodeToString(PuB.getEncoded());
        } catch(Exception e){
            e.printStackTrace();
        }
        
        // This part is an assumption where the keys are sent to the other user
        
        //Receive public key from client
        publicKA = clientInput.nextLine();
        //idA = clientInput.nextLine();
        
        // Sending public key and identity
        p.println(publicKB);
        //p.println(idB);
        
        
        // Converting Bytes to Public Key
        try{
            //Get Public Key of A
            byte[] byteKey = Base64.getDecoder().decode(publicKA);
            X509EncodedKeySpec X509Key = new X509EncodedKeySpec(byteKey);
            PuA = KeyFactory.getInstance("RSA").generatePublic(X509Key);
            
            //Initialize Cipher
            RSACipher = Cipher.getInstance("RSA");
            RSACipher.init(Cipher.DECRYPT_MODE, PrB);
        } catch(Exception e){
            e.printStackTrace();
        }
        
        // Receiving ID of A
        idAEncrypted = clientInput.nextLine();
        
        try{
            idABytes = Base64.getDecoder().decode(idAEncrypted);
            idA = new String(RSACipher.doFinal(idABytes));
        }catch(Exception e){
            e.printStackTrace();
        }
        
        System.out.println("Identity of Client: " + idA);
        
        //Receive DES key from Client
        keyDES_EncryptS = clientInput.nextLine();
        
        try{
            keyDES_Encrypt = Base64.getDecoder().decode(keyDES_EncryptS);
            
            RSACipher.init(Cipher.DECRYPT_MODE, PrB);
            keyDES_Encrypt = RSACipher.doFinal(keyDES_Encrypt);
            
            RSACipher.init(Cipher.DECRYPT_MODE, PuA);
            keyDES_Encrypt = RSACipher.doFinal(keyDES_Encrypt);
            
            keyDES_String = Base64.getEncoder().encodeToString(keyDES_Encrypt);
            
            System.out.println(keyDES_String);
            
            DESKeySpec DESKey = new DESKeySpec(keyDES_String.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            Key = keyFactory.generateSecret(DESKey);
            DESCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
