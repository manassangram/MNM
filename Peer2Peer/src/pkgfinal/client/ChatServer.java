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
import static java.lang.System.exit;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author admin
 */
public class ChatServer {
    public static String text_send="";
    public static String text_in="";
    DataOutputStream dout;
    BufferedReader br;
    public static final int port = 9999;
    public static ServerSocket cssoc;
    public ChatServer() throws IOException{
        cssoc = new ServerSocket(port);
        
        Socket client_chat_soc = cssoc.accept();
        while(true){
        dout = new DataOutputStream(client_chat_soc.getOutputStream());
        dout.writeUTF(text_send);
        br  = new BufferedReader(new InputStreamReader(client_chat_soc.getInputStream()));
        while(br.readLine()!=null){
            text_in=text_in+br.readLine();
            }
        }
    }
}
