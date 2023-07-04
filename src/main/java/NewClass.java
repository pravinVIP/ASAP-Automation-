/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Admin
 */
	
import java.net.*;
import java.io.*;
import java.util.*;
 
public class NewClass {
 
    public static void main(String[] args) throws MalformedURLException, IOException {
        String url = "https://google.com";
URL urlObj = new URL(url);
URLConnection urlCon = urlObj.openConnection();
 
Map<String, List<String>> map = urlCon.getHeaderFields();
 
 
for (String key : map.keySet()) {
    System.out.println(key + ":");
 
    List<String> values = map.get(key);
 
    for (String aValue : values) {
        System.out.println("\t" + aValue);
    }
}
    }
}
