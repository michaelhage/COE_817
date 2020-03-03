/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise2;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Henry
 */
public class Server {
    public static void main(String[] args) throws IOException {
        
        String ID_A;
        String ID_B = "RESPONDER B";
        String KM = "NETWORK SECURITY";
        String KS = "RYERSON ";
        
        ServerSocket s = new ServerSocket(9999);
        // Incoming client socket gets accepted into 'ss'.
        Socket ss = s.accept();
        Scanner clientInput = new Scanner(ss.getInputStream());
        
        // PrintStream p in the same socket as client.
        PrintStream p = new PrintStream(ss.getOutputStream());
        
        try {
            // (1) Responder B outputs the recieved 'message 1'
            ID_A = clientInput.nextLine();
            System.out.println(ID_A);
            p.println(KM);
            
            // Generate a DESKey using 'KM' as the key specification
            DESKeySpec DESKey = new DESKeySpec(KM.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey KMKey = keyFactory.generateSecret(DESKey);
            Cipher DESCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            DESCipher.init(Cipher.ENCRYPT_MODE, KMKey);
            
            byte[] encKS = DESCipher.doFinal(KS.getBytes("UTF-8"));
            byte[] encA = DESCipher.doFinal(ID_A.getBytes("UTF-8"));
            byte[] encB = DESCipher.doFinal(ID_B.getBytes("UTF-8"));
            // From what I read, Base64 encoding and deocding are required because
            // JVM padding does not produce the required block size.
            String encryptedKS = new BASE64Encoder().encode(encKS);
            String encryptedA = new BASE64Encoder().encode(encA);
            String encryptedB = new BASE64Encoder().encode(encB);
            
            // (2) Responder B outputs the ciphertext of 'message 2'
            System.out.print(encryptedKS + " ");
            System.out.print(encryptedA + " ");
            System.out.println(encryptedB);
            
            // Responder B sends the ciphertext of 'message 2'
            p.println(encryptedKS);
            p.println(encryptedA);
            p.println(encryptedB);
            
            // (3) Responder B outputs the ciphertext of 'message 3'
            ID_B = clientInput.nextLine();
            System.out.println(ID_B);
            
            DESKeySpec DESKey2 = new DESKeySpec(KS.getBytes());
            SecretKey KSKey = keyFactory.generateSecret(DESKey2);
            DESCipher.init(Cipher.DECRYPT_MODE, KSKey);
            byte[] decValueB = new BASE64Decoder().decodeBuffer(ID_B);
            byte[] decEncodeValueB = DESCipher.doFinal(decValueB);
            // (4) Responder B outputs the decrypted text of 'message 3'
            String decryptedValueB = new String(decEncodeValueB);
            System.out.println(decryptedValueB);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
