/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.client;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author manas.sangram
 */
public class MSUpload {
    public MSUpload(Socket soc,String dfile) throws FileNotFoundException, IOException
    {
        //ServerSocket ss = new ServerSocket(25003);
        while(true)
        {
         //   Socket soc = //ss.accept();
        File f = new File("D:/MNM/src/peer2peer/Downloads/"+dfile);
        byte[] array = new byte[(int)f.length()];
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(f));
        bis.read(array, 0, array.length);
        OutputStream os = soc.getOutputStream();
        os.write(array,0,array.length);
        os.flush();
        soc.close();
        }
    }
}