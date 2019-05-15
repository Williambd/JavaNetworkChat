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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lidea9928
 */
public class JavaNetworkingAttempt {

    private static Connection room;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Would You Like To Start a chatroom (s), or connect to an existing one(c)?");
            char command = sc.nextLine().toLowerCase().charAt(0);
            if (command == 's') {
                room = new Connection(4444);
            } else if (command == 'c') {
                System.out.println("WHAT ADRESS?");
                room = new Connection(sc.nextLine(), 4444);
            }
            while (true) {
                try {
                    while (room.readyToTake()) {
                        System.out.print(room.TakeLine());
                    }
                    String out = ScanLine();
                    if (!out.equals("&^101")){
                        room.SendLine(out);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(JavaNetworkingAttempt.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private static String ScanLine() {
        InputStreamReader instream = new InputStreamReader(System.in);
        BufferedReader in = new BufferedReader(instream);
        try {
            if (in.ready()) {
                return in.readLine();
            }
        } catch (IOException ex) {
            Logger.getLogger(JavaNetworkingAttempt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "&^101";
    }
}
