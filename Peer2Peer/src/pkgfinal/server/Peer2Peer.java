package pkgfinal.server;

import java.io.IOException;
import static java.lang.System.exit;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author admin
 */
public class Peer2Peer {
    public static void main(String args[]){
        
        try {
            Server server = new pkgfinal.server.Server();
            System.out.println("started server obj");
            } catch (IOException ex) {
            Logger.getLogger(Peer2Peer.class.getName()).log(Level.SEVERE, null, ex);
            
            exit(0);
        }
    }
}
