/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.duke.ece651.teamX.client;


import edu.duke.ece651.teamX.shared.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App{

  /**
   * This main method runs the client, on localhost and port 1651.
   * Specifically, it creates an instance and calls run.
   * @param args is the command line arguments.  These are currently ignored.
   * @throws IOException if creation of the ServerSocket fails.
   */
  public static void main(String[] args) throws IOException {
    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    String hostname = "localhost";//"vcm-32232.vm.duke.edu";//"localhost";
    int port = 8080; // set the port number
    Client client = new Client(port, hostname,in); // create a new client instance
    client.run(); // connect the client to the server
  }


}
