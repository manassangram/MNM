/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.StandardSocketOptions;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manas.sangram
 */
public class Client{
    public static boolean flagreg = false;
    public static boolean flagsearch = false;
    public static Socket csoc;
     DataInputStream din;
        DataOutputStream dout ;
    String uname;
    public String peerip    ;
    File f = new File("D:/Peer2Peer/src/peer2peer/IPAddress.txt"); 
    FileInputStream fin;
    InetAddress ip;
    String ipaddress="";
    private final int port = 25000;
    
    public Client() throws Exception {
        
        fin = new FileInputStream(f);
        while(fin.available()!=0)
        {
            ipaddress=ipaddress+(char)fin.read();//RETRIEVE SERVER IP ADDRESS
        }        
        ip =  InetAddress.getByName(ipaddress);    

        
        try {
            csoc = new Socket(ipaddress,port);//CREATE SOCKET AND START CONNECTION
            System.out.println("serv IP: "+ipaddress);
            //br = new BufferedReader(new InputStreamReader(csoc.getInputStream()));
            din = new DataInputStream(csoc.getInputStream());
            dout = new DataOutputStream(csoc.getOutputStream());
            System.out.println("Client Started");
            
            if(csoc.isConnected()){//IF CONNECTION IS ESTABLISHED
              
                //System.out.println(csoc.getInetAddress().toString());
                //dout.writeUTF(csoc.getLo);//SEND CLIENT IP TO SERVER
                //dout.flush();
                
            //this.wait(10000);//WAIT FOR SERVER RESPONSE
            //br = new BufferedReader(new InputStreamReader(csoc.getInputStream()));//RETRIEVE USERNAME FROM SERVER
                System.out.println("Waiting for username asociated with my IP");
            //    System.out.println("Ready to READ????:"+br.ready());
                    uname = din.readUTF();
                System.out.println("name "+uname);
            //NewApplication.USERNAME = uname;
            
            System.out.println("Gote my name.Waiting for user "+uname);
            }
        }
                catch (Exception ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            //this.wait(10000);//WAIT FOR USER INTERACTION
     //       if(flagreg)//USER WANTS TO REGISTER
       //     {
    public void register(String name) throws Exception
    {
                System.out.println("User wants to register");
                //dout = new DataOutputStream(csoc.getOutputStream());
                //dout.writeUTF("NAME");
                System.out.println("Sending name to server for register");
                //dout.flush();
                //this.wait(10000);
                //dout = new DataOutputStream(csoc.getOutputStream());
                dout.writeUTF(name);//SENDING REGNAME TO SERVER
                //flagreg = false;
                 System.out.println("Sent user name "+name);
                dout.flush();
            }
      //this.wait(15000);//WAIT FOR USER
    
       //         if(flagsearch){//USER SEARCH FOR PEER
                //dout = new DataOutputStream(csoc.getOutputStream());
    public int search(String peerName)throws Exception
    {
                System.out.println("User wants to search for a peer");
                //dout.writeUTF("PEER");
                //dout.flush();
                //this.wait(10000);
                //dout  = new DataOutputStream(csoc.getOutputStream());
                dout.writeUTF(peerName);//SEND NAME TO SERVER
                System.out.println("Sending username to server");
                //this.wait(10000);//WAIT FOR SERVER RESPONSE
                //br = new BufferedReader(new InputStreamReader(csoc.getInputStream()));
                
                String pip = din.readUTF();
                pip = pip.substring(1);
                System.out.println("IP: "+pip);
                peerip = pip;//RECEIVE PEER IP FROM SERVER    
                System.out.println("peer IP: "+peerip);
                System.out.println("Got the ip of searchd peer");
               // flagsearch = false;
                dout.flush();
                if("0".equals(pip)){ 
                    System.out.println("IN if :0");
                    return 0;
                }
                else{
                    System.out.println("IN else :1");
                    return 1;
                }
    }
 //               }//IF CONDITION
 //           }

         
 //       }
    }    

