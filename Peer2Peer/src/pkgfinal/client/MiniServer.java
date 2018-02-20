/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.client;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class MiniServer implements Runnable{
    
    boolean searchflag;
    static String dfile;
    static ServerSocket ssoc;
    static Socket sock;
    DataInputStream din;
    DataOutputStream dout;
    ObjectOutputStream oos;
    File shared = new File("D:/Peer2Peer/src/peer2peer/Downloads");
    ArrayList<String> names = new ArrayList<String>(Arrays.asList(shared.list()));
    public static final int port = 25001;
    static Thread mns;
    public MiniServer() throws IOException{
       
        ssoc = new ServerSocket(port);//SEREVR SOCKET INITIATION
        
        System.out.println("started serv sock");
        mns = new Thread(this);
        mns.start();
    }   
    public void run(){
        System.out.println("started thread");
        
        while(true){
            try {
                System.out.println("in while");
                sock = ssoc.accept();//LISTEN FOR CLIENTS
                System.out.println("connected");
                din = new DataInputStream(sock.getInputStream());//RECEIVED REQUEST
                dout = new DataOutputStream(sock.getOutputStream());
                //if(){//REQUEST TO SHARE
                    //while(names!=null){
                        oos = new ObjectOutputStream(sock.getOutputStream());
                        oos.writeUnshared(names);//SENDING LIST OF FILES
                        System.out.println("file list sent");
                        //oos.flush();
                        //oos.close();
               //     }
                //}
                //else if(din.readLine()!=""){//RECEIVED FILE NAME TO BE SENT
                    dfile = din.readUTF();
                    System.out.println("file to be uploaded "+dfile);
                    
                    if(search(dfile))
                    {//FILE IS AVAILABLE.START TRANSFER
                        System.out.println("found file "+dfile);
                        dout.writeUTF("available");
                        MSUpload upload=new MSUpload(sock,dfile);
/*                        File transfer = new File("A:/work/Peer2Peer/src/peer2peer/Downloads/"+dfile);
                        byte[] array = new byte[(int)transfer.length()];
                        
                        BufferedInputStream bin = new BufferedInputStream(new FileInputStream(transfer));
                        bin.read(array,0,array.length);
                        System.out.println("read from file");
                        OutputStream os = sock.getOutputStream();
                        os.write(array,0,array.length);//SEND THE FILE TO OTHER PEER
                        System.out.println("sent file content");
                        os.flush();
                        os.close();             */
                    }
                    else
                    {//FILE UNAVAILABLE
                        System.out.println("file not found");
                        
                        dout.writeUTF("FAILURE");
                        System.out.println("file not found msg");
                    }
               // }    
                
            } catch (Exception ex) {
                Logger.getLogger(MiniServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    public boolean search(String fname){//A METHOD TO SEARCH A FILE
        int flag;
        System.out.println("in search");
        File[] list = shared.listFiles();
        if(list!=null){
            for(File f:list){
                if(fname.equalsIgnoreCase(f.getName()))
                {
                    //searchflag = true;
                    System.out.println("if file search true");
                    return true;
                }
                else
                {
                    //searchflag = false;
                    System.out.println("if file search false");
                    //return false;
                    
                }
            }
        }
    return false;
}
}

