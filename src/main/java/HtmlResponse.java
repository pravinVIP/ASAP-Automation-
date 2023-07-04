
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Admin
 */
public class HtmlResponse {
    
     public static void main(String args[]) throws IOException{
         
         URL urllhtml = null;
                                urllhtml = new URL("https://bbuat.bikebandit.com/helmets/a");
                                String domain2 = urllhtml.getHost();
                                System.out.println("domain2"+domain2);
                               String page = domain2.startsWith("www.") ? domain2.substring(4) : domain2;
                               System.out.println("paeg"+page);
     }
}
