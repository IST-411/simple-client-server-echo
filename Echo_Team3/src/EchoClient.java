/* 
Project: L01 Group Work
Purpose Details: Client-Server Echo Application
Course: IST 411
Author: Ahmed Metwoali, Alexa McInvaille, Elyse Swider, Ryan Waters
Date Developed: 5/20/21
Last Date Changed: 5/20/21
Revision: 1
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EchoClient {

    public static void main(String[] args) {
        System.out.println("Echo Client");

        try {
            System.out.println("Waiting for connection.....");
            InetAddress localAddress = InetAddress.getLocalHost();

            try ( Socket clientSocket = new Socket(localAddress, 8080);  PrintWriter out = new PrintWriter(
                    clientSocket.getOutputStream(), true);  BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()))) {
                System.out.println("Connected to server");
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    System.out.println("Enter text: ");
                    String inputLine = scanner.nextLine();
                    if ("quit".equalsIgnoreCase(inputLine)) {
                        break;
                    }
                    out.println(inputLine);
                    String response = br.readLine();
                    System.out.println("Server response: " + response);
                }

            }

        } catch (IOException ex) {
            Logger.getLogger(EchoClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
