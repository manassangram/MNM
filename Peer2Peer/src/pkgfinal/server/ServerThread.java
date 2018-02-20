/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.server;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author manas.sangram
 */
public class ServerThread implements Runnable
{
    DataInputStream din;
    DataOutputStream dout;
    FileWriter fwr;
    BufferedWriter bwr;
    //String path="A:/work/Peer2Peer/src/peer2peer/users.txt";
    Properties p;
    Socket s_client;
    HashMap<String,String> map;
    InetAddress ip;
    String ipaddress="";
    int rsflag;
    Thread t;
    public ServerThread()
    {

        t =new Thread(this);
        System.out.println("started server thread");
        t.start();
    }
    public void run()
    {
        try{
        din = new DataInputStream(s_client.getInputStream());
                dout = new DataOutputStream(s_client.getOutputStream());
                String ip = s_client.getInetAddress().toString();//din.readUTF();//RETRIEVE CLIENT IP ADDRESS
                
                System.out.println("connected client ip : "+ip);
                //File f =new File(path);//LOAD THE CONTENTS OF FILE INTO HASHMAP
                //FileInputStream fin = new FileInputStream(f);
                //System.out.println("file stream created ");
                /*p = new Properties();    //WRITE CODE TO READ FILE CONTENTS INTO MAP.
                p.load(fin);
                for(String t:p.stringPropertyNames()){
                    map.put(t,p.get(t).toString());
                }
                System.out.println(" map loop");*/
                if(map.containsKey(ip)){//IF THE CLIENT'S IP IS FOUND SEND THE CORRESPONDING USERNAME
                    //dout = new DataOutputStream(s_client.getOutputStream());
                    dout.writeUTF(map.get(ip));
                    rsflag=1;
                    System.out.println(" uname value sent"+map.get(ip));
                    dout.flush();
                }
                else{//IF IP NOT FOUND ADD THE IP TO HASHMAP
                    map.put(ip,"null");
                    rsflag=0;
                    /*f.delete();//WRITE CODE TO SAVE INTO FILE
                    bwr = new BufferedWriter(new FileWriter(f));
                    for(String temp:map.keySet()){
                        bwr.write(temp+","+map.get(temp));
                        bwr.newLine();
                    }
                    System.out.println(" map loop 2");
                    bwr.flush();*/
                    //bwr.close();
                    //dout = new DataOutputStream(s_client.getOutputStream());
                    dout.writeUTF(map.get(ip));
                    System.out.println(" uname value sent"+map.get(ip));
                    dout.flush();
                }
                //din.close();
                //t.wait(15000);//WAIT FOR CLIENT'S RESPONSE
                //din = new BufferedReader(new InputStreamReader(s_client.getInputStream()));
                if(rsflag==0){//CLIENT REQUESTS FOR REFISTRATION
                    //din.close();
                    //t.wait(5000);
                    //din = new BufferedReader(new InputStreamReader(s_client.getInputStream()));
                    String name = din.readUTF();//RETRIEVE THE NAME TO BE REGISTERED
                    System.out.println("Name to be registered "+name);
                    map.remove(ip);
                    map.put(ip, name);
                    rsflag=1;
                    /*f.delete();//SAVE INTO FILE
                    //bwr = new BufferedWriter(new FileWriter(f));
                    for(String temp:map.keySet()){
                        bwr.write(temp+","+map.get(temp));
                        bwr.newLine();
                    }
                    bwr.flush();*/
                    System.out.println("Registration DONE");
                    //dout.close();
                    //din.close();
                }
                
                while(!s_client.isClosed()){
                    //din = new DataInputStream(s_client.getInputStream());
                //dout = new DataOutputStream(s_client.getOutputStream());
                    System.out.println("is connected in search");
                if(rsflag==1){//CLIENT REQUESTS FOR ANOTHER PEER
                    System.out.println("Search started");
                    //din.close();
                    //t.wait(5000);
                    //din = new BufferedReader(new InputStreamReader(s_client.getInputStream()));
                    String peer = din.readUTF();//RETRIEVE THE PEER NAME
                    System.out.println("Peer name to be searched :"+peer);
                    if(map.containsValue(peer))
                    {//SEARCH FOR PEER IN THE HASHMAP
                        System.out.println("Has peer");//dout = new DataOutputStream(s_client.getOutputStream());
                        for(Map.Entry entry:map.entrySet()){
                            if(peer.equalsIgnoreCase(entry.getValue().toString())){
                                System.out.println("Found the peer.IP is"+entry.getKey().toString());
                                dout.writeUTF(entry.getKey().toString());//SEND THE PEER'S IP TO CLIENT
                                break;
                            }
                        }
                        dout.flush();
                        
                        //dout.close();
                    }
                    else
                    {
                        dout.writeUTF("0");
 
                    }
                }
                
                }
                dout.close();
                din.close();
                
            } catch (Exception ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            finally{
                System.out.println("in final. Closing socket");
                try {
                    s_client.close();
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
}
