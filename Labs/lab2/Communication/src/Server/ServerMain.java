/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import Encrypt.*;
import java.io.*;  
import java.net.*;  
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Michael Hage
 */
public class ServerMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        
        ServerSocket ss = new ServerSocket(3333);
        Socket s = ss.accept();
        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());
        ObjectInputStream obin = new ObjectInputStream(s.getInputStream());
        ObjectOutputStream obout = new ObjectOutputStream(s.getOutputStream());
        Scanner in = new Scanner(System.in);
        String id = "Initiator A";
        DES cipher = new DES();
        
        String recMessage = "", sendMessage = "";
        
        recMessage = din.readUTF();
        System.out.println(recMessage);
        
        dout.writeUTF("Hello");
        
        /*while (!recMessage.equals("stop")) {
            recMessage = din.readUTF();
            System.out.println("client says: " + recMessage);
            System.out.println("Input a message");
            sendMessage = in.nextLine();
            System.out.println("You Sent: " + sendMessage);
            dout.writeUTF(sendMessage);
            dout.flush();
        }*/
        
        dout.close();
        din.close();
        s.close();
        ss.close();
        
    }
    
}
