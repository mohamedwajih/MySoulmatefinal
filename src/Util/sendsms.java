/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.HttpURLConnection; 


/**
 *
 * @author asus
 */
public class sendsms {

    public sendsms() {
    }
    
    public void send(int num,String txt){
        try {
   // Construct data
   String apiKey = "apikey=u6V3YvnIvBA-n661oTWLmY2hxxExbrM2Ury3AcNwfu";
   String message = "&message="+txt;
   String sender = "&sender=My Soulmate";
   String numbers = "&numbers="+String.valueOf(num);
    
   // Send data
   HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
   String data = apiKey + numbers + message + sender;
   conn.setDoOutput(true);
   conn.setRequestMethod("POST");
   conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
   conn.getOutputStream().write(data.getBytes("UTF-8"));
   final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
   final StringBuffer stringBuffer = new StringBuffer();
   String line;
   while ((line = rd.readLine()) != null) {
    //stringBuffer.append(line);
   
   }
   rd.close();
    
   //return stringBuffer.toString();
  } catch (Exception e) {
   //System.out.println("Error SMS "+e);
   
   //return "Error "+e;
    }
    
}
}
