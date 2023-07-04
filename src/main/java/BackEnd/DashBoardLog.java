/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import static BackEnd.OverallLog.loc;
import static BackEnd.OverallLog.sitekey;
import com.mycompany.output.OutputWriter;
import static com.mycompany.output.OutputWriter.ava3;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author Lenovo
 */
class CustomObject {

    String value1;
    String value2;

    CustomObject(String v1, String v2) {
        value1 = v1;
        value2 = v2;
    }
}

public class DashBoardLog {

    public void dash() throws InterruptedException {
        /////////////
        List<String> kkey = new LinkedList();
        List<String> key = new LinkedList();
        List<String> overall = new LinkedList();
        List<String> overal2 = new LinkedList();
        List<List<CustomObject>> pages = new LinkedList();
        List<List<CustomObject>> network = new LinkedList();
        List<List<CustomObject>> db = new LinkedList();
        List<List<CustomObject>> error = new LinkedList();

        List<String> opnetwork = new LinkedList();
        List<String> opdb = new LinkedList();
        List<String> operror = new LinkedList();

        Map<String, String> pagekey = new HashMap<String, String>();
        List<File> fileList = new ArrayList<File>();

        File directoryToZip = new File(loc);

        getAllFiles(directoryToZip, fileList);
        for (File ff : fileList) {
            if (!(ff.toString().contains("log.gz")) && ff.toString().contains("skavastream")) {
                // System.out.println("ff->"+ff);
                File file = new File(ff + "");

                BufferedReader br = null;
                try {
                    br = new BufferedReader(new FileReader(file));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(ErrorLog.class.getName()).log(Level.SEVERE, null, ex);
                }

                String st;
                try {
                    while ((st = br.readLine()) != null) {
                        overal2.add(st);

                        if (st.contains(sitekey)) {
                            overall.add(st);
                        }
                        if (st.contains(sitekey) && st.contains("semantic")) {

                            String aaa = st.substring(st.indexOf("] {"), st.indexOf("\",\"method\""));

                            kkey.add(aaa);
                        }

                    }

                    //
                } catch (IOException ex) {
                    Logger.getLogger(ErrorLog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        //System.out.println("overall start");
        Collections.sort(overall);

        OutputWriter.BackEndFile("overall", overall);
        // System.out.println("overall end");

/////
        ListIterator<String> iterator3 = kkey.listIterator();

        while (iterator3.hasNext()) {

            String test = iterator3.next();

            Pattern p = Pattern.compile("\\.[a-zA-Z]+$");
            Matcher m = p.matcher(test);

            if (m.find()) {
            } else {
                key.add(test);
            }

        }

        key = key.stream().distinct().collect(Collectors.toList());

        // System.out.println(key);
        ListIterator<String> iterator = key.listIterator();
        ListIterator<String> iterator1 = key.listIterator();

        String home = null;
        while (iterator.hasNext()) {
            int count = 0;
            int t = 0;
            String test = iterator.next();

            while (iterator1.hasNext()) {
                String test1 = iterator1.next();

                if (test1.contains(test)) {
                    count++;

                } else {
                    t = 1;
                    break;
                }

            }

            if (count == key.size()) {

                home = test;
                pagekey.put("home", home);

            }

        }
        ListIterator<String> iterator4 = key.listIterator();
        while (iterator4.hasNext()) {

            String tag;
            String test1 = iterator4.next();

            String line1 = test1;
            test1 = test1.replace("{", "");
            String line = test1;

            home = home.replace("{", "");

            String pattern = home + "[/]?([a-zA-Z0-9\\-]+)";

            Pattern r = Pattern.compile(pattern);

            Matcher m = r.matcher(line);

            if (m.find()) {

                tag = m.group(1);

                pagekey.put(tag, line1);

            } else {

            }

        }

        for (Map.Entry<String, String> entry : pagekey.entrySet()) {
            String pageName = entry.getKey();
            String pageKkey = entry.getValue();
            // Map<String, String> currentPage = new HashMap<String, String>();
            List<CustomObject> currentPage = new ArrayList<CustomObject>();
            ListIterator<String> iterator11 = overall.listIterator();

            // Printing the iterated value 
            //  System.out.println("\nUsing ListIterator:\n");
            while (iterator11.hasNext()) {
                String st = iterator11.next();
                if (st.contains(sitekey) && st.contains("semantic")) {

                    String aaa = st.substring(st.indexOf("] {"), st.indexOf("\",\"method\""));
                    if (aaa.equals(pageKkey)) {

                        //OutputWriter.BackEndPage(pageName, st);
                        CustomObject o1 = new CustomObject(pageName, st);
                        currentPage.add(o1);

                    }

                }

            }

            pages.add(currentPage);

        }

        int optime = 0;
        List<String> optimeset = new LinkedList();
        for (int i = 0; i < pages.size(); i++) {
            List<String> page1 = new LinkedList();
            String pageName = null;
            ava3 = 0;

            List<CustomObject> dbItems = new ArrayList<CustomObject>();
            List<CustomObject> errorItems = new ArrayList<CustomObject>();
            List<CustomObject> networkItems = new ArrayList<CustomObject>();
            String name = "";
            //System.out.println("");
            //System.out.println(name);
            for (int j = 0; j < pages.get(i).size(); j++) {
                opdb.clear();
                opnetwork.clear();
                operror.clear();
                long opt = 0;
                pageName = pages.get(i).get(j).value1;
                name = pageName;
                String samantic = pages.get(i).get(j).value2;
                page1.add(samantic);
                //System.out.println(""+samantic);
                //  System.out.println(samantic);

                String line = samantic;
                //  System.out.println(line);
                String pattern = "(\\[.+?\\]).(\\{.+?\\}$)";

                // Create a Pattern object
                Pattern r = Pattern.compile(pattern);

                // Now create matcher object.
                Matcher m = r.matcher(line);
                String json = null;
                if (m.find()) {

                    //      System.out.println("Found time:" + m.group(1));
                    json = m.group(2);
                } else {
                    //  System.out.println("NO MATCH");
                }

                JsonObject empObj;

                try (
                        JsonReader reader = Json.createReader(new StringReader(json));) {

                    empObj = reader.readObject();
                }
                try {
                    opt = empObj.getInt("optime");
                    // System.out.println(addrObj1);
                    //System.out.println("optime data" + opt);
                    if (opt > 1000) {
                        optime++;
                        optimeset.add(samantic);
                    } else {
                        optime = 0;
                        optimeset.clear();
                    }
                    if (optime > 5) {
                        OutputWriter.appendLogProblem("");
                        OutputWriter.appendLogProblem("optime issue");
                        for (String a : optimeset) {
                            OutputWriter.appendLogProblem(a);
                        }
                        OutputWriter.appendLogProblem("");

                        //    System.out.println("Problem optime" + optimeset);
                    } else {

                        //    System.out.println("optime No problem" + optimeset);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
                if (samantic.contains("dbItems")) {
                    opdb.add(samantic);
                    CustomObject dbObj = new CustomObject(pageName, samantic);
                    dbItems.add(dbObj);

                }
                if (samantic.contains("errorItems")) {
                    operror.add(samantic);
                    CustomObject errorObj = new CustomObject(pageName, samantic);
                    errorItems.add(errorObj);
                }
                if (samantic.contains("networkItems")) {
                    opnetwork.add(samantic);
                    CustomObject ntObj = new CustomObject(pageName, samantic);
                    networkItems.add(ntObj);
                }

            }
            //   System.out.println(name + "```````````````````````````````````````````````````````````````");
            Collections.sort(page1);
            OutputWriter.BackEndPage(pageName, page1, opnetwork, opdb, operror);
            for (;;) {
                if (ava3 == 0) {
                    Thread.sleep(100);
                } else {
                    break;
                }
            }
            db.add(dbItems);
            error.add(errorItems);
            network.add(networkItems);
        }

        for (int i = 0; i < db.size(); i++) {

            for (int j = 0; j < db.get(i).size(); j++) {
                Set<String> set = new HashSet<String>();
                String pageName = db.get(i).get(j).value1;
                String samantic = db.get(i).get(j).value2;

                String line = samantic;

                String pattern = "\\[.+?\\].(\\{.+?\\}$)";

                Pattern r = Pattern.compile(pattern);

                Matcher m = r.matcher(line);

                if (m.find()) {

                } else {

                }
                String mt = m.group(1);
                if (mt != null) {

                    InputStream inputStream = new ByteArrayInputStream(m.group(1).trim().getBytes(Charset.forName("UTF-8")));

                    //  System.out.println("###################################");
                    JsonObject empObj;

                    try (
                            JsonReader reader = Json.createReader(new StringReader(mt.toString()));) {

                        empObj = reader.readObject();
                    }
                    //   System.out.println("Starting " + empObj);

                    JsonObject addrObj1 = empObj.getJsonObject("logItems");

                    JsonArray arrObj = addrObj1.getJsonArray("dbItems");
                    for (int ii = 0; ii < arrObj.size(); ii++) {

                        JsonObject addrObj4 = null;
                        addrObj4 = arrObj.getJsonObject(ii);

                        String type = addrObj4.getString("stmt");
                        if (set.add(type) == false) {
                            // System.out.println("contain duplicate");
                            OutputWriter.appendLogProblem("");
                            OutputWriter.appendLogProblem("Issue in db statement repeat:");
                            OutputWriter.appendLogProblem(samantic);
                            OutputWriter.appendLogProblem("");
                        } else {
                            // System.out.println("No duplicate");
                        }
                    }

                }

            }

        }
        // System.out.println("******************************************************");
        // System.out.println("error\n" + error);
        // System.out.println("******************************************************");
        //System.out.println("network\n" + network);

        String name = "";
        Set<String> set3 = new LinkedHashSet<String>();
        for (int i = 0; i < network.size(); i++) {
            String currentTime = "";
            String prevTime = "";
            Set<String> set = new LinkedHashSet<String>();
            Set<String> set1 = new LinkedHashSet<String>();
            Set<String> set2 = new LinkedHashSet<String>();

            Set<String> pset = new LinkedHashSet<String>();
            int setSize = 0;
            for (int j = 0; j < network.get(i).size(); j++) {
                String mt = null;
                set.clear();
                set1.clear();
                set2.clear();
                String pageName = network.get(i).get(j).value1;
                name = pageName;
                String samantic = network.get(i).get(j).value2;

                // System.out.println(pageName + " " + samantic);
                String line = samantic;
                //  System.out.println(line);
                String pattern = "(\\[.+?\\]).(\\{.+?\\}$)";

                // Create a Pattern object
                Pattern r = Pattern.compile(pattern);

                // Now create matcher object.
                Matcher m = r.matcher(line);

                if (m.find()) {
                    currentTime = m.group(1);
                    mt = m.group(2);
                    //    System.out.println("Found time:" + m.group(1));
                } else {
                    //  System.out.println("NO MATCH");
                }

                if (mt != null) {

                    JsonObject empObj;

                    try (
                            JsonReader reader = Json.createReader(new StringReader(mt.toString()));) {

                        empObj = reader.readObject();
                    }
                    // System.out.println("Starting " + empObj);

                    JsonObject addrObj1 = empObj.getJsonObject("logItems");

                    JsonArray arrObj = addrObj1.getJsonArray("networkItems");

                    for (int ii = 0; ii < arrObj.size(); ii++) {

                        try {
                            JsonObject addrObj4 = null;
                            addrObj4 = arrObj.getJsonObject(ii);

                            int status = addrObj4.getInt("rescode");
                            if (status >= 200 && status < 300) {
                                //  System.out.println("status no problem");
                            } else {
                                //   System.out.println("status problem");
                                OutputWriter.appendLogProblem("");
                                OutputWriter.appendLogProblem("Issue in response code:");
                                OutputWriter.appendLogProblem(samantic);
                                OutputWriter.appendLogProblem("");
                            }

                            String type = addrObj4.getString("url");
                            // System.out.println("end with extension"+type);
                            /////////////////////

                            String line1 = type;
                            //  System.out.println(line);
                            String pattern1 = "\\.[a-zA-Z1-9]+$";

                            // Create a Pattern object
                            Pattern r1 = Pattern.compile(pattern1);

                            // Now create matcher object.
                            Matcher m1 = r1.matcher(line1);

                            if (m1.find()) {
                                //System.out.println("Yes ended ex"+type);
                                continue;
                            } else {

                            }

                            /////////////////////
                            // System.out.println("next with yes type" + type);
                            if (set2.add(getText(type)) == false) {

                                OutputWriter.appendLogProblem("");
                                OutputWriter.appendLogProblem("Issue in response body:");
                                OutputWriter.appendLogProblem(samantic);
                                OutputWriter.appendLogProblem("");
                            } else {
                                //  System.out.println("No duplicate");

                            }

                            if (set1.add(type) == false) {
                                // System.out.println("contain duplicate url");
                                OutputWriter.appendLogProblem("");
                                OutputWriter.appendLogProblem("Duplicate in network stream:");
                                OutputWriter.appendLogProblem(samantic);
                                OutputWriter.appendLogProblem("");
                            } else {
                                //  System.out.println("No duplicate");

                            }
                            if (set1.size() > setSize) {
                                setSize = set1.size();
                                set3.addAll(set1);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(DashBoardLog.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }

                }

                if (currentTime != "" && prevTime != "") {

                    String dateStart = prevTime;
                    String dateStop = currentTime;
                    String aaa[] = dateStart.split(" ");
                    aaa[1] = aaa[1].replace("]", "");
                    // System.out.println(aaa[1]);
                    String bb[] = dateStop.split(" ");
                    bb[1] = bb[1].replace("]", "");
                    //System.out.println(bb[1]);
                    //HH converts hour in 24 hours format (0-23), day calculation
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM:HH:mm:ss");
                    Date d1 = null;
                    Date d2 = null;
                    try {
                        d1 = format.parse(aaa[1]);
                        d2 = format.parse(bb[1]);
                        //in milliseconds
                        long diff = d2.getTime() - d1.getTime();

                        long diffSeconds = diff / 1000 % 60;

                        long diffMinutes = diff / (60 * 1000) % 60;

                        long diffHours = diff / (60 * 60 * 1000) % 24;
                        long diffDays = diff / (24 * 60 * 60 * 1000);
                        //System.out.print(diffDays + " days, ");
                        //System.out.print(diffHours + " hours, ");
                        //System.out.print(diffMinutes + " minutes, ");
                        //System.out.print(diffSeconds + " seconds.");
                        //   System.out.println("Time" + aaa[1] + " " + bb[1]);
                        if (diffMinutes <= 10 && diffHours <= 0 && diffDays <= 0) {

                            String mmt = m.group(2);
                            if (mmt != null) {

                                JsonObject empObj;

                                try (
                                        JsonReader reader = Json.createReader(new StringReader(mmt.toString()));) {

                                    empObj = reader.readObject();
                                }
                                // System.out.println("Starting " + empObj);

                                JsonObject addrObj1 = empObj.getJsonObject("logItems");

                                JsonArray arrObj = addrObj1.getJsonArray("networkItems");
                                // System.out.println("networkItems " + arrObj);
                                for (int ii = 0; ii < arrObj.size(); ii++) {
                                    // System.out.println(arrObj.size() + "II" + ii);
                                    JsonObject addrObj4 = null;
                                    addrObj4 = arrObj.getJsonObject(ii);

                                    String type = addrObj4.getString("url");
                                    //  System.out.println("to get response url" + type);

                                    if (set.add(type) == false) {
                                        //    System.out.println("contain duplicate");
                                        OutputWriter.appendLogProblem("");
                                        OutputWriter.appendLogProblem("Duplicate in network stream:");
                                        OutputWriter.appendLogProblem(samantic);
                                        OutputWriter.appendLogProblem("");
                                    } else {
                                        //  System.out.println("No duplicate");

                                    }
                                }

                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                if (set.size() > 0 && pset.size() > 0) {
                    try {
                        pset.retainAll(set);
                        if (pset.size() > 0) {
                            //   System.out.println("duplicate in 10 min" + pset);
                            OutputWriter.appendLogProblem("");
                            OutputWriter.appendLogProblem("10 min cache Duplicate in network stream:");
                            OutputWriter.appendLogProblem(samantic);
                            for (String a : pset) {
                                OutputWriter.appendLogProblem(a);
                            }
                            OutputWriter.appendLogProblem("");
                        } else {
                            //  System.out.println("No problem");
                        }
                    } catch (NullPointerException e) {
                        //  System.out.println("No problem");
                    }
                }
                pset.clear();
                Iterator<String> setIterator = set.iterator();
                while (setIterator.hasNext()) {
                    pset.add(setIterator.next());
                }
                prevTime = currentTime;

            }
        }
        // System.out.println("######################################");

        // Getting ListIterator
        Iterator value = set3.iterator();

        // Displaying the values after iterating through the iterator 
        System.out.println("The iterator values are: ");
        while (value.hasNext()) {
            String val = value.next().toString();
            Iterator value1 = overal2.iterator();

            // Displaying the values after iterating through the iterator 
            System.out.println("The iterator values are: " + val);
            List<String> netlist = new LinkedList();
            while (value1.hasNext()) {
                String val1 = value1.next().toString();

                //  System.out.println(val1);
                if (val1.contains("] {\"url\":\"" + val)) {

                    netlist.add(val1);

                }

            }
            System.out.println("Dashboard2");
            new DashBoard2().dash2(netlist);

        }

        /////////////
    }

    public static void getAllFiles(File dir, List<File> fileList) {
        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                fileList.add(file);
                if (file.isDirectory()) {
                    System.out.println("directory:" + file.getCanonicalPath());
                    getAllFiles(file, fileList);
                } else {
                    System.out.println("     file:" + file.getCanonicalPath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getText(String url) throws IOException {
        // System.out.println("url1"+url);
        if (url.contains(":")) {
            //  System.out.println("contain :");
            url = url.replaceAll(":[0-9]+", "");

        }
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        //add headers to the connection, or check the status if desired..

        // handle error response code it occurs
        int responseCode = connection.getResponseCode();
        InputStream inputStream;
        if (200 <= responseCode && responseCode <= 299) {
            inputStream = connection.getInputStream();
        } else {
            inputStream = connection.getErrorStream();
        }

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        inputStream));

        StringBuilder response = new StringBuilder();
        String currentLine;

        while ((currentLine = in.readLine()) != null) {
            response.append(currentLine);
        }

        in.close();

        return response.toString();
    }
}
