/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;


import Encrypt.*;
import java.io.*;  
import java.net.*; 
import java.util.Scanner;
import java.util.ArrayList;
/**
 *
 * @author Michael Hage
 */
public class ClientMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        
        Socket s = new Socket("localhost", 3333);
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        Scanner in = new Scanner(System.in);
        String id = "Responder B";
        DES cipher = new DES();
        
        Object temp;
        String sendMessage = "" , recMessage = "",
                keyS = "Ryerson", keyM = "Network Security";
        
        dout.writeUTF("Connection Established");
        System.out.println("Conneciton Established");
        
        recMessage = din.readUTF();
        System.out.println(recMessage);
        /*while (!sendMessage.equals("stop")) {
            System.out.println("Input a message");
            sendMessage = in.nextLine();
            System.out.println("You sent: " + sendMessage);
            dout.writeUTF(sendMessage);
            dout.flush();
            recMessage = din.readUTF();
            System.out.println("Server says: " + recMessage);
        }*/

        din.close();
        dout.close();
        s.close();
        
    }
    
}
