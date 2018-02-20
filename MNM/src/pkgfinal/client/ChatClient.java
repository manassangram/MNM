/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 *
 * @author admin
 */
public class ChatClient {
    public static String text_send="";
    public static String peerip;
    public static String text_in="";
    DataOutputStream dout;
    BufferedReader br;
    public static Socket ccsoc;
    public static final int port = 9999;
    ChatClient() throws IOException, InterruptedException{
        ccsoc = new Socket(peerip,port);
        this.wait(5000);
        while(true){
        dout = new DataOutputStream(ccsoc.getOutputStream());
        dout.writeUTF(text_send);
        br  = new BufferedReader(new InputStreamReader(ccsoc.getInputStream()));
        while(br.readLine()!=null){
            text_in=text_in+br.readLine();
        }
        }
    }
}
