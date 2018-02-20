/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.client;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class MiniClient{
    public static String list = "";
    int filesize = 1022386;
    int bytesRead;
    int currentTot=0;
    Socket psoc;
    DataInputStream din;
    ObjectInputStream ois;
    DataOutputStream dout;
    String peerip;
    //public static Thread peer;
    static final int port = 25001;
   public MiniClient() throws IOException{
       
   }
   public void connect() throws IOException
   {
       psoc = new Socket(peerip,port);
       System.out.println("MiniClient Started");
            dout = new DataOutputStream(psoc.getOutputStream());
            din = new DataInputStream(psoc.getInputStream());
    }
       //peer = new Thread(this);
    public boolean isOnline()
    {
       if(psoc.isConnected())//CHECK THE CONNECTION
            return true;
       else
           return false;
    }
public ArrayList<String> getList() throws IOException, ClassNotFoundException{
   

        
            //dout.writeUTF("REQUEST_FILE");//REQUEST FOR FILE
            //dout.flush();
            //this.wait(10000);//WAIT FOR PEER REPLY ON YOUR REQUEST
            ois = new ObjectInputStream(psoc.getInputStream());
            ArrayList<String> list=(ArrayList<String>) ois.readUnshared();
            for(int i=0;i<list.size();i++)
                    System.out.println(list.get(i));
            return list;
        }

            //this.wait();//WAIT FOR USER INTERACTION. AWAKE BY NOTIFY AFTER ENTERING FILE NAME
            //dout = new DataOutputStream(psoc.getOutputStream());
public void download(String f) throws IOException{
    System.out.println("Starting file download");
            dout.writeUTF(f);//SEND FILE NAME TO BE DOWNLOADED
            System.out.println("File name sent");
            //dout.flush();
            //dout.close();
            //this.wait(10000);//WAIT FOR SERVER RESPONSE//WAIT FOR SERVER RESPONSE
            //din = new BufferedReader(new InputStreamReader(psoc.getInputStream()));
            if(din.readUTF().equalsIgnoreCase("FAILURE"))//SERVER REPLY VERIFICATION
            {
                System.out.println("File does not exist");
               NewApplication.msg=false;//FILE UNAVAILABLE. NOTIFY TO USER
            }
            else{
                System.out.println("Start File transfer");
                MCDownload mcd = new MCDownload(psoc,"D:/MNM/downloaded/"+f);
/*            byte[] bytearray = new byte[filesize];//START FILE DOWNLOAD
            InputStream is = psoc.getInputStream();
            File t = new File("A:/work/Peer2Peer/Downloads/"+NewApplication.file);
            FileOutputStream fos = new FileOutputStream(t);//DOWNLOAD FILE
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bytesRead = is.read(bytearray,0,bytearray.length);
            bos.write(bytearray,0,bytearray.length);
            System.out.println("Transfer finished");
            bos.flush();*/
            //bos.close();
        }
        }
}
        
    

