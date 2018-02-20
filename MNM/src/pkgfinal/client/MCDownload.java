/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal.client;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 *
 * @author manas.sangram
 */
public class MCDownload {
    public static final int filesize=1024*1024;
    public MCDownload(Socket soc,String path) throws IOException
    {
        byte[] mybytearray = new byte[filesize];
        InputStream is = soc.getInputStream();
        FileOutputStream fos = new FileOutputStream(path);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        int bytesRead = is.read(mybytearray, 0,mybytearray.length);
        bos.write(mybytearray,0,mybytearray.length);
        bos.close();
        soc.close();
    }
    
}
