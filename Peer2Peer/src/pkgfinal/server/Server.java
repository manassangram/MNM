/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class Server implements Runnable{
    public static HashMap<String,String> map;
    ServerSocket server;
    static Thread t;
    Socket s_client;
    private final int port = 25000;
    public Server() throws FileNotFoundException, IOException{
        server = new ServerSocket(port);
        map = new HashMap<String,String>();
        System.out.println("started serversocket with port"+port);
        t =new Thread(this);
        System.out.println("started server thread");
        t.start();
    }
    public void run(){

        while(true){
           
            try {
                s_client = server.accept();//WAITS FOR CLIENT REQUEST
                ServerThread st = new ServerThread();
                st.map = this.map;
                st.s_client = this.s_client;
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}


