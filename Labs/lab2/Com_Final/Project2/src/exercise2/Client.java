/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercise2;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * PLEASE READ: THERE IS A RACE CONDITION, SO SOMETIMES THE CLIENT IS FASTER THAN THE
 * SERVER. TRY TO RUN THE PROGRAM MULTIPLE TIMES UNTIL SUCCESS.
 * @author h47tran
 */
public class Client {
    public static void main(String[] args) throws IOException {
        
        /**
         * Two scanners, one for user input, one for server output from the
         * socket input stream.
         * (1) A passes its identity to B using the output stream.
         * (2) Then receives an encrypted message from B.
         * (3) Then decrypts the message.
         */
        
        /*
        // Uncomment below to get user input; but for convenience, identity is hard-coded
        Scanner userInput = new Scanner(System.in);
        System.out.println("Enter identity:");
        byte[] userIdentity = userInput.nextLine().getBytes();
        */
        
        String ID_A = "INITIATOR A";
        String ID_B, KM, KS;
        
        // Create simple client socket, using localhost, port number: '9999'
        Socket cs = new Socket("localhost", 9999);
        Scanner serverInput = new Scanner(cs.getInputStream());
        
        // PrintStream adds functionality to another output stream
        // In this case, the socket output stream
        PrintStream p = new PrintStream(cs.getOutputStream());
        // (1) Initiator A outputs the cleartext of 'message 1'
        System.out.println(ID_A);
        // User A passes identity to B
        p.println(ID_A);
        
        try {
            // I made the assumption that Initiator A recieves KM.
            // Otherwise, how does A decode 'message 1'?
            KM = serverInput.nextLine();
            DESKeySpec DESKey = new DESKeySpec(KM.getBytes());
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey KMKey = keyFactory.generateSecret(DESKey);
            Cipher DESCipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            DESCipher.init(Cipher.DECRYPT_MODE, KMKey);
            
            // (2) Initiator A outputs the ciphertext of 'message 2'
            KS = serverInput.nextLine();
            ID_A = serverInput.nextLine();
            ID_B = serverInput.nextLine();
            System.out.print(KS + " ");
            System.out.print(ID_A + " ");
            System.out.println(ID_B);
            
            // Decode Base64 encoding and decryption
            byte[] decValue = new BASE64Decoder().decodeBuffer(KS);
            byte[] decValueA = new BASE64Decoder().decodeBuffer(ID_A);
            byte[] decValueB = new BASE64Decoder().decodeBuffer(ID_B);
            byte[] decEncodeValue = DESCipher.doFinal(decValue);
            byte[] decEncodeValueA = DESCipher.doFinal(decValueA);
            byte[] decEncodeValueB = DESCipher.doFinal(decValueB);
            String decryptedValue = new String(decEncodeValue);
            String decryptedValueA = new String(decEncodeValueA);
            String decryptedValueB = new String(decEncodeValueB);
            
            // (3) Initiator A outputs the decrypted 'message 2'
            System.out.print(decryptedValue + " ");
            System.out.print(decryptedValueA + " ");
            System.out.println(decryptedValueB);
            
            // Generate encryption for ID_B.
            DESKeySpec DESKey2 = new DESKeySpec(decryptedValue.getBytes());
            SecretKey KSKey = keyFactory.generateSecret(DESKey2);
            DESCipher.init(Cipher.ENCRYPT_MODE, KSKey);
            byte[] encB = DESCipher.doFinal(decryptedValueB.getBytes("UTF-8"));
            String encryptedB = new BASE64Encoder().encode(encB);
            // Send 'message 3' to server
            p.println(encryptedB);
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        
    }
}
