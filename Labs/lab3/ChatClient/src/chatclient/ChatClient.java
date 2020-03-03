/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatclient;

import java.io.*;
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
public class ChatClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        //Create Identificaiton Variables
        //A - Client
        //B - Server
        String idA = "INITIATOR A", idB;
        String publicKA = null, publicKB;
        
        String keyDES_String = "Ryerson0", keyDES_EncryptS = null;
        byte[] keyDES_Encrypt;
        
        byte[] idABytes;
        String idAEncrypted = null;
        //Create RSA KeyPair Variables
        KeyPairGenerator kg;
        KeyPair kp;
        KeyGenerator kg1;
        PrivateKey PrA = null, PrB = null;
        PublicKey PuA = null, PuB = null;
        
        Cipher RSACipher = null, DESCipher;
        SecretKey Key;
        
        Thread t1, t2;
        
        // Create simple client socket, using localhost, port number: '9999'
        Socket cs = new Socket("localhost", 9999);
        Scanner serverInput = new Scanner(cs.getInputStream());
        
        // PrintStream adds functionality to another output stream
        // In this case, the socket output stream
        PrintStream p = new PrintStream(cs.getOutputStream());
        
        //Generate KeyPair
        try{
            kg = KeyPairGenerator.getInstance("RSA");
            kp = kg.generateKeyPair();
            PrA = kp.getPrivate();
            PuA = kp.getPublic();
            publicKA = Base64.getEncoder().encodeToString(PuA.getEncoded());
        } catch(Exception e){
            e.printStackTrace();
        }
        
        // Sending public key and identity        
        p.println(publicKA);
        
        publicKB = serverInput.nextLine();

        // Converting Bytes to Public Key
        try{
            //Get Public Key of B
            byte[] byteKey = Base64.getDecoder().decode(publicKB);
            X509EncodedKeySpec X509Key = new X509EncodedKeySpec(byteKey);
            PuB = KeyFactory.getInstance("RSA").generatePublic(X509Key);
            
            //Initalize Cipher
            RSACipher = Cipher.getInstance("RSA");
            RSACipher.init(Cipher.ENCRYPT_MODE, PuB);
            
            idABytes = RSACipher.doFinal(idA.getBytes());
            idAEncrypted = Base64.getEncoder().encodeToString(idABytes);
        } catch(Exception e){
            e.printStackTrace();
        }
        
        // Sending Encrypted ID
        p.println(idAEncrypted);
        
        try{
            // Generate DES key.
            
            System.out.println("DES Key: " + keyDES_String);
            DESKeySpec DESKey = new DESKeySpec(keyDES_String.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            Key = keyFactory.generateSecret(DESKey);
            DESCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            // This initializes the DES algorithm.
            
            keyDES_Encrypt = Base64.getDecoder().decode(keyDES_String);
            
            //Encrypt with Private A Key
            //RSACipher.init(Cipher.ENCRYPT_MODE, PrA);
            //keyDES_Encrypt = RSACipher.doFinal(keyDES_Encrypt);
            
            //keyDES_Encrypt = Base64.getEncoder().encode(keyDES_Encrypt);
            //System.out.println(keyDES_Encrypt.length);
            
            //Encrypt with Public B Key
            RSACipher.init(Cipher.ENCRYPT_MODE, PuB);
            keyDES_Encrypt = RSACipher.doFinal(keyDES_Encrypt); 
            
            keyDES_EncryptS = Base64.getEncoder().encodeToString(keyDES_Encrypt);
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        p.println(keyDES_EncryptS);
        
        t1 = new Thread((Runnable) this);
       t2 = new Thread((Runnable) this);
    }
    
}
