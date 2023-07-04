
import static com.mycompany.har.PerformanceCheckHar.html;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
public class HarRead {

    private static String readAllBytesJava7(String filePath) throws IOException {
        String content = "";

        content = new String(Files.readAllBytes(Paths.get(filePath)));

        return content;
    }

    public static void main(String args[]) throws IOException {
        String bf = readAllBytesJava7("D:\\1.har");
        byte[] bytes = bf.getBytes();

        InputStream inputStream = new ByteArrayInputStream(bytes);

        JsonObject empObj = null;
        try {
            try (
                    JsonReader reader = Json.createReader(inputStream)) {

                empObj = reader.readObject();
            }
        } catch (Exception ex) {

            ex.printStackTrace();
        }

        JsonObject addrObj = empObj.getJsonObject("log");
        JsonArray arrObjpages = addrObj.getJsonArray("pages");
        JsonObject addrObjpg = arrObjpages.getJsonObject(0);
        String page = addrObjpg.getString("title");
        JsonArray arrObj = addrObj.getJsonArray("entries");

        for (int i = 0; i < arrObj.size(); i++) {

            JsonObject addrObj4 = null;
            addrObj4 = arrObj.getJsonObject(i);
            JsonObject addrObj8 = addrObj4.getJsonObject("request");
                        String type = addrObj4.getString("_resourceType");
            String calls = addrObj8.getString("url");
          
            if(calls.contains("https://ext5.youngliving.com")){
                  System.out.println("^^^^^^^^^^^^^^^^^^^^");
            System.out.println(calls);
            System.out.println(type);
            System.out.println("VVVVVVVVVVVVVVVVVVV");
             JsonArray arrObjhtml = addrObj8.getJsonArray("headers");
             System.out.println("1header"+arrObjhtml);
                        for (int ihtml = 0; ihtml < arrObjhtml.size(); ihtml++) {
                        JsonObject addrObjhtml = null;
                        addrObjhtml = arrObjhtml.getJsonObject(ihtml);
                            //System.out.println("add"+addrObjhtml);
                        try{
                            System.out.println("name.."+addrObjhtml.getString("name"));
                            System.out.println("val.."+addrObjhtml.getString("value"));
                            String findingHtml="";
                            if(addrObjhtml.getString("name").contains("referer")){
                            findingHtml=addrObjhtml.getString("value");
                            }
                
                            
                            System.out.println("%%%%%%%%%%%%%%%%%%%%%%");
                            System.out.println("html--"+findingHtml);
                            html=findingHtml;
                            System.out.println("%%%%%%%%%%%%%%%%%%%%%%");
                        }catch(Exception e) {}
                        }}

        }

    }
}
