/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import com.mycompany.output.OutputWriter;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
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
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;

/**
 *
 * @author pravin.a
 */
public class DashBoard2 {

    public static void dash2(List<String> list) {
        Collections.sort(list);
        List<String> opnetwork = new LinkedList();
        List<String> opdb = new LinkedList();
        List<String> operror = new LinkedList();
        int optime = 0;
        List<String> optimeset = new LinkedList();

        String name = "";
        ListIterator<String> iterator = list.listIterator();

        // Printing the iterated value 
        while (iterator.hasNext()) {
            long opt = 0;

            String stream = iterator.next();

            //System.out.println(""+samantic);
            //  System.out.println(samantic);
            String line = stream;
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
                System.out.println("opt:" + opt);
                // System.out.println(addrObj1);
                //System.out.println("optime data" + opt);
                if (opt > 1000) {
                    optime++;
                    optimeset.add(stream);
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
            if (stream.contains("dbItems")) {
                opdb.add(stream);

            }
            if (stream.contains("errorItems")) {
                operror.add(stream);

            }
            if (stream.contains("networkItems")) {
                opnetwork.add(stream);

            }
            ListIterator<String> iterator2 = opdb.listIterator();
            while (iterator2.hasNext()) {
                Set<String> set = new HashSet<String>();
                String stream1 = iterator2.next();
                String line4 = stream1;

                String pattern4 = "\\[.+?\\].(\\{.+?\\}$)";

                Pattern r4 = Pattern.compile(pattern4);

                Matcher m4 = r.matcher(line4);

                if (m4.find()) {

                } else {

                }
                String mt = m4.group(1);
                if (mt != null) {

                    InputStream inputStream = new ByteArrayInputStream(m.group(1).trim().getBytes(Charset.forName("UTF-8")));

                    //  System.out.println("###################################");
                    JsonObject empObj4;

                    try (
                            JsonReader reader = Json.createReader(new StringReader(mt.toString()));) {

                        empObj4 = reader.readObject();
                    }
                    //   System.out.println("Starting " + empObj);

                    JsonObject addrObj1 = empObj4.getJsonObject("logItems");

                    JsonArray arrObj = addrObj1.getJsonArray("dbItems");
                    for (int ii = 0; ii < arrObj.size(); ii++) {

                        JsonObject addrObj4 = null;
                        addrObj4 = arrObj.getJsonObject(ii);

                        String type = addrObj4.getString("stmt");
                        if (set.add(type) == false) {
                            // System.out.println("contain duplicate");
                            OutputWriter.appendLogProblem("");
                            OutputWriter.appendLogProblem("Issue in db statement repeat:");
                            OutputWriter.appendLogProblem(stream1);
                            OutputWriter.appendLogProblem("");
                        } else {
                            // System.out.println("No duplicate");
                        }
                    }

                }
            }

            //????????
            Set<String> set3 = new LinkedHashSet<String>();

            ListIterator<String> iterator1 = opnetwork.listIterator();

            // Printing the iterated value 
            System.out.println("\nUsing ListIterator:\n");
            String currentTime = "";
            String prevTime = "";
            Set<String> set = new LinkedHashSet<String>();
            Set<String> set1 = new LinkedHashSet<String>();
            Set<String> set2 = new LinkedHashSet<String>();

            Set<String> pset = new LinkedHashSet<String>();
            int setSize = 0;
            System.out.println("network items");
            while (iterator1.hasNext()) {
                String mt = null;
                set.clear();
                set1.clear();
                set2.clear();
                String stream1 = iterator1.next();
                String line1 = stream1;

                String pattern1 = "(\\[.+?\\]).(\\{.+?\\}$)";

                Pattern r1 = Pattern.compile(pattern1);

                Matcher m1 = r1.matcher(line1);

                if (m1.find()) {
                    currentTime = m1.group(1);
                    mt = m1.group(2);
                    System.out.println("Found time:" + m.group(1));
                    System.out.println("Found json:" + m.group(2));
                } else {

                }
                //???????
                if (mt != null) {

                    JsonObject empObj1;

                    try (
                            JsonReader reader = Json.createReader(new StringReader(mt.toString()));) {

                        empObj1 = reader.readObject();
                    }
                    // System.out.println("Starting " + empObj);

                    JsonObject addrObj1 = empObj1.getJsonObject("logItems");
                    System.out.println("logItems" + addrObj1);

                    JsonArray arrObj = addrObj1.getJsonArray("networkItems");
                    System.out.println("networkItems" + arrObj);

                    for (int ii = 0; ii < arrObj.size(); ii++) {

                        JsonObject addrObj4 = null;
                        addrObj4 = arrObj.getJsonObject(ii);
                        int status = addrObj4.getInt("rescode");
                        System.out.println("status" + status);
                        if (status >= 200 && status < 300) {
                            //  System.out.println("status no problem");
                        } else {
                            //   System.out.println("status problem");
                            OutputWriter.appendLogProblem("");
                            OutputWriter.appendLogProblem("Issue in response code:");
                            OutputWriter.appendLogProblem(stream1);
                            OutputWriter.appendLogProblem("");
                        }
                        String type = addrObj4.getString("url");
                        // System.out.println("end with extension"+type);
                        /////////////////////

//                            String line2 = type;
//                            //  System.out.println(line);
//                            String pattern2 = "\\.[a-zA-Z1-9]+$";
//
//                            // Create a Pattern object
//                            Pattern r2 = Pattern.compile(pattern2);
//
//                            // Now create matcher object.
//                            Matcher m2 = r2.matcher(line2);
//
//                            if (m2.find()) {
//                                //System.out.println("Yes ended ex"+type);
//                                continue;
//                            } else {
//
//                            }
                        if (set1.add(type) == false) {
                            System.out.println("contain duplicate url");
                            OutputWriter.appendLogProblem("");
                            OutputWriter.appendLogProblem("Duplicate in network stream:");
                            OutputWriter.appendLogProblem(stream1);
                            OutputWriter.appendLogProblem("");
                        } else {
                            //  System.out.println("No duplicate");

                        }
                        if (set1.size() > setSize) {
                            setSize = set1.size();
                            set3.addAll(set1);
                        }
                        System.out.println("set3" + set3);

                    }

                }

                //                if (currentTime != "" && prevTime != "") {
//
//                    String dateStart = prevTime;
//                    String dateStop = currentTime;
//                    String aaa[] = dateStart.split(" ");
//                    aaa[1] = aaa[1].replace("]", "");
//                    // System.out.println(aaa[1]);
//                    String bb[] = dateStop.split(" ");
//                    bb[1] = bb[1].replace("]", "");
//                    //System.out.println(bb[1]);
//                    //HH converts hour in 24 hours format (0-23), day calculation
//                    SimpleDateFormat format = new SimpleDateFormat("yyyy-dd-MM:HH:mm:ss");
//                    Date d1 = null;
//                    Date d2 = null;
//                    try {
//                        d1 = format.parse(aaa[1]);
//                        d2 = format.parse(bb[1]);
//                        //in milliseconds
//                        long diff = d2.getTime() - d1.getTime();
//
//                        long diffSeconds = diff / 1000 % 60;
//
//                        long diffMinutes = diff / (60 * 1000) % 60;
//
//                        long diffHours = diff / (60 * 60 * 1000) % 24;
//                        long diffDays = diff / (24 * 60 * 60 * 1000);
//                        //System.out.print(diffDays + " days, ");
//                        //System.out.print(diffHours + " hours, ");
//                        //System.out.print(diffMinutes + " minutes, ");
//                        //System.out.print(diffSeconds + " seconds.");
//                        //   System.out.println("Time" + aaa[1] + " " + bb[1]);
//                        if (diffHours <= 1 && diffDays <= 0) {
//
//                            String mmt = m.group(2);
//                            if (mmt != null) {
//
//                                JsonObject empObj3;
//
//                                try (
//                                        JsonReader reader = Json.createReader(new StringReader(mmt.toString()));) {
//
//                                    empObj3 = reader.readObject();
//                                }
//                                // System.out.println("Starting " + empObj);
//
//                                JsonObject addrObj1 = empObj3.getJsonObject("logItems");
//
//                                JsonArray arrObj = addrObj1.getJsonArray("networkItems");
//                                // System.out.println("networkItems " + arrObj);
//                                System.out.println(stream1+" "+arrObj.size());
//                                for (int ii = 0; ii < arrObj.size(); ii++) {
//                                    // System.out.println(arrObj.size() + "II" + ii);
//                                    System.out.println("hai");
//                                    JsonObject addrObj4 = null;
//                                    addrObj4 = arrObj.getJsonObject(ii);
//
//                                    String type = addrObj4.getString("url");
//                                    //  System.out.println("to get response url" + type);
//
//                                    if (set.add(type) == false) {
//                                        //    System.out.println("contain duplicate");
//                                        OutputWriter.appendLogProblem("");
//                                        OutputWriter.appendLogProblem("Duplicate in network stream:");
//                                        OutputWriter.appendLogProblem(stream1);
//                                        OutputWriter.appendLogProblem("");
//                                    } else {
//                                        //  System.out.println("No duplicate");
//
//                                    }
//                                }
//
//                            }
//
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//                }
//                if (set.size() > 0 && pset.size() > 0) {
//                    try {
//                        pset.retainAll(set);
//                        if (pset.size() > 0) {
//                            System.out.println("duplicate in 1 hr" + pset);
//                            OutputWriter.appendLogProblem("");
//                            OutputWriter.appendLogProblem("1 hr cache Duplicate in network stream:");
//                            OutputWriter.appendLogProblem(stream1);
//                            for (String a : pset) {
//                                OutputWriter.appendLogProblem(a);
//                            }
//                            OutputWriter.appendLogProblem("");
//                        } else {
//                            //  System.out.println("No problem");
//                        }
//                    } catch (NullPointerException e) {
//                        //  System.out.println("No problem");
//                    } catch (FileNotFoundException ex) {
//                        Logger.getLogger(DashBoardLog.class.getName()).log(Level.SEVERE, null, ex);
//                    } catch (UnsupportedEncodingException ex) {
//                        Logger.getLogger(DashBoardLog.class.getName()).log(Level.SEVERE, null, ex);
//                    }
//                }
//                pset.clear();
//                Iterator<String> setIterator = set.iterator();
//                while (setIterator.hasNext()) {
//                    pset.add(setIterator.next());
//                }
//                prevTime = currentTime;
//
//            
//                 
//                 
//            } 
                //???????
                // System.out.println(iterator1.next());
            }

            //????????
        }
        //   System.out.println(name + "```````````````````````````````````````````````````````````````");

    }

}
