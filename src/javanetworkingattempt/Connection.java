/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javanetworkingattempt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lidea9928
 */
public class Connection {
    private ServerSocket server;
    private Socket client;
    private PrintWriter netOut;
    private BufferedReader netIn;
    public int linesSent;
    public int linesRecieved;
    
    Connection(String Adress, int portNumber){
        try {
            client = new Socket(Adress,4444);
            netOut = new PrintWriter(client.getOutputStream(), true);
            netIn = new BufferedReader(new InputStreamReader(client.getInputStream()));

        } catch (IOException ex) {
            Logger.getLogger(JavaNetworkingAttempt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    Connection (int portNumber){
                try {
            System.out.println("Building server...");
            InetAddress inetAddress = InetAddress.getLocalHost();
            server = new ServerSocket(portNumber);

            System.out.println("Server generated at " + inetAddress.getHostAddress() + ", waiting for connecting client");
            client = server.accept();

            System.out.println("Client Connected");

            netOut = new PrintWriter(client.getOutputStream(), true);
            netIn = new BufferedReader(new InputStreamReader(client.getInputStream()));

        } catch (IOException ex) {
            Logger.getLogger(JavaNetworkingAttempt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     *Disconnects from the current serverClient, sending an exit(-1)
     */
    public void DisconnectClient(){
            netOut.println("exit(-1)");
            try {
                client.close();
            } catch (IOException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
    /**
     * Closes the client and server, sending an exit(-1)
     */
    public void End(){
        try {
            netOut.println("exit(-1)");
            client.close();
        } catch (IOException ex) {
        }
    }
    
    /**
     * Gets line sent from Client
     * @return Line sent from client
     */
public String TakeLine() {
        try {
            if (netIn.ready()) {
                linesRecieved+=1;
                String in = netIn.readLine();
                if (!in.equals("&^!001")){
                return in+"\n";
            }}

        } catch (IOException ex) {
        }
        return "";
    }
    public boolean readyToTake() throws IOException{
        return netIn.ready();
    }
    
    /**
     * Sends String to Client
     * @param out
     */
    public void SendLine(String out){
        linesSent +=1;
        netOut.println(out);
    }
    
    /**
     * Sends Signal for Client to Continue Sending Information
     */
    public void Invoke(){
        netOut.println("&^!001");
    }
    
    public void GetAddress(){
        
    }
     
}
