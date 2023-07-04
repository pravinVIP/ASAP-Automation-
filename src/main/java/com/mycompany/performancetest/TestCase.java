package com.mycompany.performancetest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static com.mycompany.input.InputReader.jsCallio;
import static com.mycompany.input.InputReader.CssCallio;
import static com.mycompany.input.InputReader.CDN;
import static com.mycompany.input.InputReader.callCount;
import static com.mycompany.input.InputReader.htmlCacheio;

import static com.mycompany.input.InputReader.sizeio;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.*;
import static com.mycompany.input.InputReader.jsCssFontCacheio;
import static com.mycompany.input.InputReader.transport_Security;
import com.mycompany.output.OutputWriter;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.ProtocolException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;

/**
 *
 * @author pravin.a
 */
public class TestCase {

    private int getcount(String pat, String txt) {
        int M = pat.length();
        int N = txt.length();
        int res = 0;

        /* A loop to slide pat[] one by one */
        for (int i = 0; i <= N - M; i++) {
            /* For current index i, check for  
        pattern match */
            int j;
            for (j = 0; j < M; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)) {
                    break;
                }
            }

            // if pat[0...M-1] = txt[i, i+1, ...i+M-1]  
            if (j == M) {
                res++;
                j = 0;
            }
        }
        return res;

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

    //1,leave the commant statements 2,if doc contain \t or \n than fail
    public String Minify_HTML(String html4) throws MalformedURLException, IOException {
        String issue = "\n1,Minify Html Issue:\n";
        System.out.println("html minify");

        // write the output to stdout
        int count = 0;
        int doc = 0;
        int doct = 0;

        int val = 0;
        int start = 0;
        int countletter = 0;
       /** Doc contain new 2 empty line then fail */
        if (html4.contains("\n\n")) {
            issue = issue + "more empty lines found\n";
            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }

        String diff[] = null;

        try {
            /**Split doc into lines*/
            diff = html4.split("\\n");

        } catch (Exception ex) {
            OutputWriter.appendToFile(ex);
            ex.printStackTrace();
        }
        for (String a : diff) {

         
            count++;

            if (a.contains("<HTML")) {

                start = 1;
            }
            /**dont check multi-line and document comment */
            if (start == 1) {

                if (a.contains("/*") || a.contains("/**")) {
                    doc = 1;
                    
                    if (a.contains("*/")) {

                        doc = 0;

                    } else {
                        doc = 1;

                        continue;
                    }

                }
                if (a.contains("*/")) {
                    doc = 0;
                }
                if (doc == 1) {

                    continue;
                }

//                if (a.contains("<script type=\"text/javascript\">")) {
//                    doct = 1;
//
//                    if (a.contains("</script>")) {
//
//                        doct = 0;
//
//                    } else {
//                        doct = 1;
//
//                    }
//
//                }
//                if (a.contains("</script>")) {
//                    doct = 0;
//                }
//                if (doct == 1) {
//
//                    if (a.contains("       ")) {
//
//                        //System.out.println("@@@@@@@@@@@@@@@@@@@@@@empty space in script");
//                        issue = issue + "Line " + count + "empty space in script\n";
//                        val = 1;
//                        break;
//                    }
//                }
            /**Contain empty line then fail*/
                if (a.equals("")) {
                    try {

                        // System.out.println("@@@@@@@@@@@@@@@@@@@@@@empty line");
                        issue = issue + "Line " + count + "empty space in script\n";
                        val = 1;
                        break;

                    } catch (Exception ex) {
                        val = 1;
                        OutputWriter.appendToFile(ex);
                        ex.printStackTrace();
                    }

                }
/**Contain 2 tab space then fail*/
                if (a.contains("       ")) {
                    try {       

                        // System.out.println("@@@@@@@@@@@@@@@@@@@@@@contain space");
                        issue = issue + "Line " + count + "contain space\n";
                        val = 1;
                        break;

                    } catch (Exception ex) {
                        val = 1;
                        OutputWriter.appendToFile(ex);
                        ex.printStackTrace();
                    }

                }
/**15 characters in more than or equal to 7 lines then issue*/
                if (!(a.length() >= 15)) {
                    countletter++;
                    if (countletter >= 7) {
                        try {

                            // System.out.println("@@@@@@@@@@@@@@@@@@@@@@contain less 100 length");
                            issue = issue + "Line " + count + "contain less 100 length\n";
                            val = 1;
                            break;

                        } catch (Exception ex) {
                            val = 1;
                            OutputWriter.appendToFile(ex);
                            ex.printStackTrace();
                        }
                    }

                }
            }

        }

        if (val == 1) {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        } else {

            return "Pass";
        }

    }

    //2
    public String CompressHtml(Map< String, Map<String, List<String>>> mapurl) throws FileNotFoundException, UnsupportedEncodingException {
        boolean pass = true;
        String fail = "Fail";
        String url = null;
        String issue = "\nIssue in Compress Html:\n";
        Map<String, List<String>> map = null;
        for (Map.Entry<String, Map<String, List<String>>> entryy : mapurl.entrySet()) {

            url = entryy.getKey();
            map = (entryy.getValue());

            int avail = 0;

            for (Map.Entry<String, List<String>> entry : map.entrySet()) {

                if ((entry.getKey()) != null) {
                    if ((entry.getKey()).toLowerCase().contains("Transfer-Encoding".toLowerCase()) || (entry.getKey()).toLowerCase().contains("Vary".toLowerCase())) {
                        avail++;
                        if ((entry.getValue()).toString().toLowerCase().contains("chunked".toLowerCase()) || (entry.getValue()).toString().toLowerCase().contains("Accept-Encoding".toLowerCase())) {
                            // System.out.println(entry.getKey()+" "+entry.getValue());
                            pass = true;
                            // System.out.println("2,CompressHtml sucess" + entry.getKey() + " " + entry.getValue());
                            break;
                        } else {
                            issue = issue + "Compress Html not match in url:" + url + "\n";
                            //System.out.println("2,CompressHtmlnot match" + entry.getKey() + " " + entry.getValue());
                            pass = false;
                            fail = fail.concat("\n" + url);
                            issue = issue + "Compress Html not foune in url:" + url + "\n";
                        }
                    }
                }
            }
            if (avail == 0) {
                // System.out.println("2,CompressHtmlnot found");
                issue = issue + "Compress Html not foune in url:" + url + "\n";
                pass = false;
                fail = fail.concat("\n" + url);
            }
        }
        if (pass == true) {
            return "Pass";
        } else {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }

    }

    //3 chect content-type availability
    public String CharacterSet(Map< String, Map<String, List<String>>> mapurl) throws FileNotFoundException, UnsupportedEncodingException {
        String issue = "\nIssue in Character Set:\n";
        boolean pass = true;
        String fail = "Fail";
        String url = null;
        Map<String, List<String>> map = null;
        for (Map.Entry<String, Map<String, List<String>>> entryy : mapurl.entrySet()) {

            url = entryy.getKey();
            map = (entryy.getValue());

            int avail = 0;
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {

                if ((entry.getKey()) != null && (entry.getKey()).toLowerCase().equals(
                        "Content-Type".toLowerCase())) {
                    avail++;
                    if ((entry.getValue()).toString().toLowerCase().contains("UTF-8".toLowerCase())) {
                        // System.out.println("url find "+entry.getKey()+entry.getValue());
                        break;
                    } else {
                        // System.out.println("url find "+entry.getKey()+entry.getValue());
                        pass = false;
                        fail = fail + "\n" + url;
                        issue = issue + "in url:" + url + "\n";
                    }
                }
            }
            if (avail == 0) {
                pass = false;
                fail = fail + "\n" + url;
            }
        }
        if (pass == true) {
            return "Pass";
        } else {
            String superclassName = new Exception().getStackTrace()[1].getClassName();

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }
    }

    //4
    //Check No of js call with jsCount(from input file).
    //Check the name of js with jsName(from input file)
    public String MinNo_HTTP_JsReq(List<String> set) throws IOException {
        String issue = "\n4,No of HTTP Reques Issue:\n";
        boolean pass = true;
        String fail = "Fail";
        String url;
        if (set.isEmpty()) {
            return "Pass";
        }

        if (set.size() <= Integer.parseInt(callCount)) {
            //  System.out.println("less than 5 calls");

        } else {
            pass = false;
            fail = fail.concat("\n maximum count reached");
            // System.out.println("4 \n more than 5 calls" + set);
        }
        Iterator<String> iterator = set.iterator();

        while (iterator.hasNext()) {

            url = iterator.next();
            URL url_Object = new URL(url);
            URLConnection urlConnection_Obj = url_Object.openConnection();

            String[] parts = urlConnection_Obj.toString().split("/");
            String lastWord = parts[parts.length - 1];
            // System.out.println(lastWord);
            int js = 0;
            for (String jsCallio1 : jsCallio) {
                if (lastWord.contains(jsCallio1.trim())) {
                    js = 1;
                } // }

            }
            if (js == 0) {
                pass = false;
                issue = issue + "issue in url:" + url + "\n";
                //fail = fail.concat("\n" + url + "\n");
                // System.out.println("4 " + url);
            }

        }

        if (pass != false) {
            return "Pass";
        } else {
            String superclassName = new Exception().getStackTrace()[1].getClassName();

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }
    }

    //5
    public static String Minify(List<String> set) {
        boolean pass = true;
        String issue = "\nJS  Minification Issue\n";
        String fail = "Fail";
        // System.out.println("JSMinification Issue");
        // System.out.println("12");
        if (set.isEmpty()) {
            return "Pass";
        }

        Iterator<String> iterator = set.iterator();
        // System.out.println(file);
        while (iterator.hasNext()) {
            int lettercount = 0;
            String urlString[] = iterator.next().split(",CCTV,");
            String res = "";
            String ul = urlString[0];
            System.out.println("minify url:" + ul);
            try {
                res = urlString[1];
            } catch (Exception ex) {
//                OutputWriter.appendToFile(ex);
//                ex.printStackTrace();

            }
            if (res.contains("\n\n")) {
                issue = issue + ul + "more empty line found\n";
                OutputWriter.appendHarProblem(issue);

                return "Fail";
            }
            String diff[] = null;
            try {
                diff = res.split("\\n");

            } catch (Exception ex) {
                OutputWriter.appendToFile(ex);
                ex.printStackTrace();
            }
            int count = 0;
            int doc = 0;
            int doct = 0;
            int val = 0;
            for (String line : diff) {

                //  System.out.println(count + line.length() + "++jscss+" + line);
                count++;

                if (line.contains("/*") || line.contains("/**")) {
                    doc = 1;
                    // System.out.println("contain docum");
                    if (line.contains("*/")) {

                        doc = 0;

                    } else {
                        doc = 1;

                        continue;
                    }

                }
                if (line.contains("*/")) {
                    doc = 0;
                }
                if (doc == 1) {

                    continue;
                }
                /////////
//                if (line.contains("<script type=\"text/javascript\">")) {
//                    doct = 1;
//
//                    if (line.contains("</script>")) {
//
//                        doct = 0;
//
//                    } else {
//                        doct = 1;
//
//                    }
//
//                }
//                if (line.contains("</script>")) {
//                    doct = 0;
//                }
                if (doct == 1) {

                    if (line.contains("       ")) {
                        // System.out.println("@@@@@@@@@@@@@@@@@@@@@@empty space in script");
                        issue = issue + ul + " Line no" + count + " has empty space " + "\n";
                        pass = false;
                        fail = fail + "\nFail->" + urlString;
                        //  issue = issue + ul+"\n";
                        break;
                    }
                }
//                if (line.equals("")) {
//                    if (reader.readLine().equals("")) {
//                        try {
//                            
//                            System.out.println("@@@@@@@@@@@@@@@@@@@@@@empty line");
//                            issue = issue + urlString + " Line no" + count + " has empty line in css"+"\n";
//                            val = 1;
//                            break;
//
//                        } catch (Exception ex) {
//                            val = 1;
//
//                        }
//
//                    }
//                }

                if (line.contains("       ")) {
                    try {

                        //System.out.println("@@@@@@@@@@@@@@@@@@@@@@contain space");
                        issue = issue + ul + " Line no" + count + " has contain space " + "\n";
                        pass = false;
                        fail = fail + "\nFail->" + urlString;
                        // issue = issue + ul+"\n";
                        break;

                    } catch (Exception ex) {
                        val = 1;

                    }

                }

                if (!(line.length() >= 15)) {
                    lettercount++;
                    if (lettercount >= 7) {
                        try {

                            //  System.out.println("@@@@@@@@@@@@@@@@@@@@@@contain less 15 length");
                            issue = issue + ul + " Line no" + count + " has contain less 15 length " + "\n";
                            pass = false;
                            fail = fail + "\nFail->" + urlString;
                            //issue = issue + ul+"\n";
                            break;

                        } catch (Exception ex) {
                            val = 1;

                        }
                    }

                }
            }

        }
        if (pass == true) {
            return "Pass";
        } else {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }
    }

    public static String Minify1(List<String> set) {
        boolean pass = true;
        String issue = "\nCSS Minification Issue\n";
        String fail = "Fail";
        // System.out.println("JS CSS Minification Issue");
        // System.out.println("12");
        if (set.isEmpty()) {
            return "Pass";
        }

        Iterator<String> iterator = set.iterator();
        // System.out.println(file);
        while (iterator.hasNext()) {
            int lettercount = 0;
            String urlString[] = iterator.next().split(",CCTV,");
            String res = "";
            String ul = urlString[0];
            // System.out.println("minify url:" + ul);
            try {
                res = urlString[1];
            } catch (Exception ex) {
                OutputWriter.appendToFile(ex);
                ex.printStackTrace();

            }
            if (res.contains("\n\n")) {
                issue = issue + ul + "more empty line found\n";
                OutputWriter.appendHarProblem(issue);

                return "Fail";
            }
            String diff[] = null;
            try {
                diff = res.split("\\n");

            } catch (Exception ex) {
                OutputWriter.appendToFile(ex);
                ex.printStackTrace();
            }
            int count = 0;
            int doc = 0;
            int doct = 0;
            int val = 0;
            for (String line : diff) {

                //  System.out.println(count + line.length() + "++jscss+" + line);
                count++;

                if (line.contains("/*") || line.contains("/**")) {
                    doc = 1;
                    // System.out.println("contain docum");
                    if (line.contains("*/")) {

                        doc = 0;

                    } else {
                        doc = 1;

                        continue;
                    }

                }
                if (line.contains("*/")) {
                    doc = 0;
                }
                if (doc == 1) {

                    continue;
                }
                /////////
//                if (line.contains("<script type=\"text/javascript\">")) {
//                    doct = 1;
//
//                    if (line.contains("</script>")) {
//
//                        doct = 0;
//
//                    } else {
//                        doct = 1;
//
//                    }
//
//                }
//                if (line.contains("</script>")) {
//                    doct = 0;
//                }
                if (doct == 1) {

                    if (line.contains("       ")) {
                        // System.out.println("@@@@@@@@@@@@@@@@@@@@@@empty space in script");
                        issue = issue + ul + " Line no" + count + " has empty space " + "\n";
                        pass = false;
                        fail = fail + "\nFail->" + urlString;
                        //  issue = issue + ul+"\n";
                        break;
                    }
                }
//                if (line.equals("")) {
//                    if (reader.readLine().equals("")) {
//                        try {
//                            
//                            System.out.println("@@@@@@@@@@@@@@@@@@@@@@empty line");
//                            issue = issue + urlString + " Line no" + count + " has empty line in css"+"\n";
//                            val = 1;
//                            break;
//
//                        } catch (Exception ex) {
//                            val = 1;
//
//                        }
//
//                    }
//                }

                if (line.contains("       ")) {
                    try {

                        //System.out.println("@@@@@@@@@@@@@@@@@@@@@@contain space");
                        issue = issue + ul + " Line no" + count + " has contain space " + "\n";
                        pass = false;
                        fail = fail + "\nFail->" + urlString;
                        // issue = issue + ul+"\n";
                        break;

                    } catch (Exception ex) {
                        val = 1;

                    }

                }

                if (!(line.length() >= 15)) {
                    lettercount++;
                    if (lettercount >= 7) {
                        try {

                            // System.out.println("@@@@@@@@@@@@@@@@@@@@@@contain less 15 length");
                            issue = issue + ul + " Line no" + count + " has contain less 15 length " + "\n";
                            pass = false;
                            fail = fail + "\nFail->" + urlString;
                            //issue = issue + ul+"\n";
                            break;

                        } catch (Exception ex) {
                            val = 1;

                        }
                    }

                }
            }

        }
        if (pass == true) {
            return "Pass";
        } else {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }
    }

    //6 //41 //13
    public static String Compress(Map< String, Map<String, List<String>>> mapurl) throws FileNotFoundException, UnsupportedEncodingException {
        String issue = "\nIssue in Compress:\n";
        boolean pass = true;
        String url;
        String fail = "Fail";
        if (mapurl.isEmpty()) {
            return "Pass";
        }
        Map<String, List<String>> map = null;
        for (Map.Entry<String, Map<String, List<String>>> etry : mapurl.entrySet()) {
            // System.out.println(entry.getKey() + " : " + entry.getValue());
            url = etry.getKey();
            map = (etry.getValue());
            int avai = 0;
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {

                if ((entry.getKey()) != null && (entry.getKey()).contains("Content-Encoding")) {
                    avai++;
                    if ((entry.getValue().toString()).contains("gzip")) {
                        // System.out.println((entry.getKey()+" "+entry.getValue()));
                        break;
                    } else {
                        pass = false;
                        issue = issue + url + "\n";
                        fail = fail.concat("\n" + url + "\n");
                    }
                }

            }
            if (avai == 0) {
                pass = false;
                fail = fail.concat("\n" + url + "\n");
            }

        }

        if (pass == true) {
            return "Pass";
        } else {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }
    }

    //8  //36
    public static String Max_Age(Map< String, Map<String, List<String>>> mapurl) throws FileNotFoundException, UnsupportedEncodingException {
        String issue = "\nIssue in Cache:\n";
        boolean pass = true;
        String fail = "Fail";
        String url;
        if (mapurl.isEmpty()) {
            return "Pass";
        }
        Map<String, List<String>> map = null;
        for (Map.Entry<String, Map<String, List<String>>> etry : mapurl.entrySet()) {
            // System.out.println(entry.getKey() + " : " + entry.getValue());
            url = etry.getKey();
            map = (etry.getValue());

            //  System.out.println("Printing All Response Header for URL: " + url_Object.toString() + "\n");
            int avai = 0;
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                //System.out.println(entry.getKey() + " : " + entry.getValue());
                if ((entry.getKey()) != null && (entry.getKey()).contains("Cache-Control")) {
                    avai++;
                    if (entry.getValue().toString().contains("=")) {
                        String cache = (entry.getValue()).toString();
                        // System.out.println("1***************"+cache);

                        String Cache = cache.substring(((cache.indexOf("=")) + 1), cache.indexOf("]"));
                        // System.out.println("2***************"+Cache);
                        if (Integer.parseInt(Cache) >= Integer.parseInt(jsCssFontCacheio)) {

                        } else {
                            pass = false;
                            issue = issue + "\n Issue Cache size:" + Cache + " " + url + "\n";
                            fail = fail.concat("\n size-" + Cache + " " + url + "\n");

                        }
                    } else {
                        pass = false;
                        issue = issue + "\n Issue in url:" + url + "\n";
                        fail = fail.concat("\n size-" + url + "\n");

                    }
                }

            }
            if (avai == 0) {
                pass = false;
                issue = issue + "\n Issue in url:" + url + "\n";
                fail = fail.concat("\n size-" + url + "\n");
            }

        }
        if (pass == true) {
            return "Pass";
        } else {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }

    }
//10

    public String size(List<String> set, List<String> set1, List<String> set2, List<String> set3, List<String> set4) throws IOException {
        boolean pass = true;
        String fail = "Fail";
        String issue = "\nIssue in File size:\n";
        if (set.isEmpty() && set1.isEmpty() && set2.isEmpty() && set3.isEmpty() && set4.isEmpty()) {
            return "Pass";
        }
        try {
            for (String s : set) {
                String arr[] = s.split(",CCTV,");
                String key = arr[0];
                int val = Integer.parseInt(arr[1]);

                if (val <= Integer.parseInt(sizeio)) {
                    ///      System.out.println("size");
                } else {
                    pass = false;
                    //  fail = fail.concat("\n" + me.getKey() + "->" + me.getValue());
                    issue = issue + key + "->" + val + "\n";
                    //     System.out.println("fail");

                }

            }
            for (String s : set1) {
                String arr[] = s.split(",CCTV,");
                String key = arr[0];
                int val = Integer.parseInt(arr[1]);

                if (val <= Integer.parseInt(sizeio)) {
                    ///      System.out.println("size");
                } else {
                    pass = false;
                    //  fail = fail.concat("\n" + me.getKey() + "->" + me.getValue());
                    issue = issue + key + "->" + val + "\n";
                    //     System.out.println("fail");

                }

            }
            for (String s : set2) {
                String arr[] = s.split(",CCTV,");
                String key = arr[0];
                int val = Integer.parseInt(arr[1]);

                if (val <= Integer.parseInt(sizeio)) {
                    ///      System.out.println("size");
                } else {
                    pass = false;
                    //  fail = fail.concat("\n" + me.getKey() + "->" + me.getValue());
                    issue = issue + key + "->" + val + "\n";
                    //     System.out.println("fail");

                }

            }
            for (String s : set3) {
                String arr[] = s.split(",CCTV,");
                String key = arr[0];
                int val = Integer.parseInt(arr[1]);

                if (val <= Integer.parseInt(sizeio)) {
                    ///      System.out.println("size");
                } else {
                    pass = false;
                    //  fail = fail.concat("\n" + me.getKey() + "->" + me.getValue());
                    issue = issue + key + "->" + val + "\n";
                    //     System.out.println("fail");

                }

            }
            for (String s : set4) {
                String arr[] = s.split(",CCTV,");
                String key = arr[0];
                int val = Integer.parseInt(arr[1]);

                if (val <= Integer.parseInt(sizeio)) {
                    ///      System.out.println("size");
                } else {
                    pass = false;
                    //  fail = fail.concat("\n" + me.getKey() + "->" + me.getValue());
                    issue = issue + key + "->" + val + "\n";
                    //     System.out.println("fail");

                }

            }

        } catch (Exception ex) {
            OutputWriter.appendToFile(ex);
            ex.printStackTrace();
        }
        if (pass != false) {
            return "Pass";
        } else {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }
    }
//20

    public String size(List<String> set) throws IOException {
        boolean pass = true;
        String fail = "Fail";
        String issue = "\nIssue in File size:\n";
        if (set.isEmpty()) {
            return "Pass";
        }
        try {
            for (String s : set) {
                String arr[] = s.split(",CCTV,");
                String key = arr[0];
                int val = Integer.parseInt(arr[1]);

                if (val <= Integer.parseInt(sizeio)) {
                    ///      System.out.println("size");
                } else {
                    pass = false;
                    //  fail = fail.concat("\n" + me.getKey() + "->" + me.getValue());
                    issue = issue + key + "->" + val + "\n";
                    //     System.out.println("fail");

                }

            }

        } catch (Exception ex) {
            OutputWriter.appendToFile(ex);
            ex.printStackTrace();
        }
        if (pass != false) {
            return "Pass";
        } else {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }
    }

    //11
    public String MinNo_HTTP_CssReq(List<String> set) throws IOException {
        String issue = "\n11,Issue in No of CSS calls\n";
        boolean pass = true;
        String fail = "Fail";
        String url;
        //  System.out.println("11");
        if (set.isEmpty()) {
            return "Pass";
        }
        try {
            if (set.size() <= Integer.parseInt(callCount)) {
                // System.out.println("less than 5 calls");

            } else {
                pass = false;
                fail = fail.concat("\n more than 5 calls");
                //    System.out.println("\n more than 5 calls" + set);
                issue = issue + "More than 5 calls" + set + "\n";
            }
            Iterator<String> iterator = set.iterator();

            while (iterator.hasNext()) {
                int contain = 0;
                url = iterator.next();
                URL url_Object = new URL(url);
                URLConnection urlConnection_Obj = url_Object.openConnection();

                String[] parts = urlConnection_Obj.toString().split("/");
                String lastWord = parts[parts.length - 1];
                //    System.out.println(lastWord);
                for (String CssCallio1 : CssCallio) {
                    //   System.out.println(lastWord+" ()()"+CssCallio1);
                    if (lastWord.contains(CssCallio1.trim())) {
                        //  System.out.println("got");
                        contain = 1;
                    } // }

                }
                if (contain == 0) {
                    pass = false;
                    fail = fail.concat("\n" + url);
                    issue = issue + url + "\n";
                    //       System.out.println("url" + url);
                }

            }

        } catch (Exception ex) {
            OutputWriter.appendToFile(ex);
            ex.printStackTrace();
        }
        if (pass != false) {
            return "Pass";
        } else {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }
    }

    //12  
    //13
    public String Compress_HTML(Map< String, Map<String, List<String>>> mapurl) throws FileNotFoundException, UnsupportedEncodingException {
        String issue = "\nIssue in Compress_HTML\n";
        boolean pass = true;
        String fail = "Fail";
        String url = null;
        Map<String, List<String>> map = null;
        for (Map.Entry<String, Map<String, List<String>>> entryy : mapurl.entrySet()) {
            // System.out.println(entry.getKey() + " : " + entry.getValue());
            url = entryy.getKey();
            map = (entryy.getValue());
            int avail = 0;

            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                //System.out.println("5");
                if ((entry.getKey()) != null && (entry.getKey()).toLowerCase().contains("Content-Encoding".toLowerCase())) {
                    avail++;
                    if ((entry.getValue()).toString().toLowerCase().contains("gzip".toLowerCase())) {
                        pass = true;
                    } else {
                        pass = false;
                        fail = fail.concat("\n" + url);
                        issue = issue + url + "\n";
                    }
                } else {

                }
            }
            if (avail == 0) {
                pass = false;
                fail = fail.concat("\n" + url);
                issue = issue + url + "\n";
            }
        }
        if (pass == true) {
            return "Pass";
        } else {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }
    }

//15
    public String Avoid_Import(List<String> set) throws IOException {
        String issue = "\n15,Issue Contain @Import:\n";
        boolean pass = true;
        String url;
        String fail = "Fail";
        if (set.isEmpty()) {
            return "Pass";
        }

        Iterator<String> iterator = set.iterator();
        // System.out.println(file);
        while (iterator.hasNext()) {

            String urlString[] = iterator.next().split(",CCTV,");
            String res = "";
            String ul = urlString[0];
            //  System.out.println("@imp url:" + ul);
            try {
                res = urlString[1];
            } catch (Exception ex) {
                OutputWriter.appendToFile(ex);
                ex.printStackTrace();

            }
            if (res.contains("@import")) {
                // System.out.println("conatins @ imp");
                pass = false;
                issue = issue + ul + "\n";
            }
        }

        if (pass != false) {
            return "Pass";
        } else {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }
    }
    //17

    public String dns(String res, String html, List<String> set, List<String> set1, List<String> set2, List<String> set3, List<String> set4) throws MalformedURLException, IOException {
        String issue = "\n17,Issue in dns :\n";
        // System.out.println("html:"+html);
        boolean pass = true;
        String url;
        String fail = "Fail";
        if (set.isEmpty() && set1.isEmpty() && set2.isEmpty() && set3.isEmpty() && set4.isEmpty()) {
            return "Pass";
        }
        URI uri1 = null;

        try {
            uri1 = new URI(html);
        } catch (URISyntaxException ex) {
            OutputWriter.appendToFile(ex);
            ex.printStackTrace();
        }

        String dom = uri1.getHost();
        String domain11 = dom.startsWith("www.") ? dom.substring(4) : dom;
        {
            String a = "dns";

            if (res.contains(a)) {

            } else {
                pass = false;
                fail = fail.concat("html not containing dns");
                issue = issue + "html not containing dns" + "\n";
                String superclassName = new Exception().getStackTrace()[1].getClassName();

                OutputWriter.appendHarProblem(issue);

                return "Fail";

            }

        }
        try {
            Iterator<String> iterator = set.iterator();
            // System.out.println(file);
            while (iterator.hasNext()) {
                int ava = 0;
                url = iterator.next();
                url = url.replaceAll("\\?(.+?)$", "");
                // System.out.println("dns url:" + url);
                URI uri = new URI(url); // System.out.println(ioe);
                //System.out.println(me);
                String domain = uri.getHost();
                String domain1 = domain;
                if (domain1.contains(domain11)) {
                    continue;
                }
                String a = "<link rel=\"dns-prefetch\" href=\"//" + domain1;
                String b = "<link href=\"https://" + domain1 + "\" rel=\"dns-prefetch\"";

                if (res.contains(a) || res.contains(b)) {

                } else {
                    pass = false;
                    fail = fail.concat("\n" + url + "\n");
                    issue = issue + url + "\n";

                }
                //  System.out.println(ava);

            }
            Iterator<String> iterator1 = set1.iterator();
            // System.out.println(file);
            while (iterator1.hasNext()) {
                int ava = 0;
                url = iterator1.next();
                url = url.replaceAll("\\?(.+?)$", "");
                URI uri = new URI(url); // System.out.println(me);
                //System.out.println(ioe);
                String domain = uri.getHost();
                String domain1 = domain;
                if (domain1.contains(domain11)) {
                    continue;
                }
                String a = "<link rel=\"dns-prefetch\" href=\"//" + domain1;
                String b = "<link href=\"https://" + domain1 + "\" rel=\"dns-prefetch\"";

                if (res.contains(a) || res.contains(b)) {

                } else {
                    pass = false;
                    fail = fail.concat("\n" + url + "\n");
                    issue = issue + url + "\n";

                }
                // System.out.println(ava);

            }
            Iterator<String> iterator2 = set2.iterator();
            // System.out.println(file);
            while (iterator2.hasNext()) {
                int ava = 0;
                url = iterator2.next();
                url = url.replaceAll("\\?(.+?)$", "");
                URI uri = new URI(url); //   System.out.println(ioe);
                // System.out.println(me);
                String domain = uri.getHost();
                String domain1 = domain;
                if (domain1.contains(domain11)) {
                    continue;
                }
                String a = "<link rel=\"dns-prefetch\" href=\"//" + domain1;
                String b = "<link href=\"https://" + domain1 + "\" rel=\"dns-prefetch\"";
                if (res.contains(a) || res.contains(b)) {

                } else {
                    pass = false;
                    fail = fail.concat("\n" + url + "\n");
                    issue = issue + url + "\n";

                }

            }
            Iterator<String> iterator3 = set3.iterator();
            // System.out.println(file);
            while (iterator3.hasNext()) {
                int ava = 0;
                url = iterator3.next();
                url = url.replaceAll("\\?(.+?)$", "");
                URI uri = new URI(url); // System.out.println(ioe);
                // System.out.println(me);
                String domain = uri.getHost();
                String domain1 = domain;
                if (domain1.contains(domain11)) {
                    continue;
                }
                String a = "<link rel=\"dns-prefetch\" href=\"//" + domain1;
                String b = "<link href=\"https://" + domain1 + "\" rel=\"dns-prefetch\"";
                if (res.contains(a) || res.contains(b)) {

                } else {
                    pass = false;
                    fail = fail.concat("\n" + url + "\n");
                    issue = issue + url + "\n";

                }
                // System.out.println(ava);
                if (ava == 0) {

                } else {
                }

            }
            Iterator<String> iterator4 = set4.iterator();
            // System.out.println(file);
            while (iterator4.hasNext()) {
                int ava = 0;
                url = iterator4.next();
                url = url.replaceAll("\\?(.+?)$", "");
                URI uri = new URI(url); //  System.out.println(ioe);
                //  System.out.println(me);
                String domain = uri.getHost();
                String domain1 = domain;
                if (domain1.contains(domain11)) {
                    continue;
                }
                String a = "<link rel=\"dns-prefetch\" href=\"//" + domain1;
                String b = "<link href=\"https://" + domain1 + "\" rel=\"dns-prefetch\"";
                if (res.contains(a) || res.contains(b)) {

                } else {
                    pass = false;
                    fail = fail.concat("\n" + url + "\n");
                    issue = issue + url + "\n";

                }

            }

        } catch (Exception ex) {
            OutputWriter.appendToFile(ex);
            ex.printStackTrace();
        }
        if (pass == true) {
            return "Pass";
        } else {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }
    }

    //18
    public String alldns(String res, String html, Map<String, String> all) throws IOException, URISyntaxException {
        String url;
        String type;
        boolean pass = true;

        String issue = "\n18,DNS issue in all calls:\n";
        //System.out.println("html:"+html);
        String fail = "Fail";
        if (all.isEmpty()) {
            return "Pass";
        }
        {
            String a = "dns";

            String inputLine;
            int passCondition = 0;

            if (!res.contains("dns")) {
                pass = false;
                fail = fail.concat("html not containing dns");
                issue = issue + "html not containing dns" + "\n";
                pass = false;
                OutputWriter.appendHarProblem(issue);

                return fail;
            }

        }
        Map<String, List<String>> map = null;
        URI uri1 = null;

        uri1 = new URI(html);

        String dom = uri1.getHost();
        String domain11 = dom.startsWith("www.") ? dom.substring(4) : dom;
        for (Map.Entry<String, String> entryy : all.entrySet()) {
            int ava = 0;
            // System.out.println(entry.getKey() + " : " + entry.getValue());
            url = entryy.getKey();
            type = (entryy.getValue()).toString();
            url = url.replaceAll("\\?(.+?)$", "");
            URI uri = null;

            uri = new URI(url);

            String domain = uri.getHost();
            String domain1 = domain;
            if (domain1.contains(domain11)) {
                continue;
            }
            String a = "<link rel=\"dns-prefetch\" href=\"//" + domain1;
            String b = "<link href=\"https://" + domain1 + "\" rel=\"dns-prefetch\"";
            //System.out.println("All "+a);

            if (res.contains(a) || res.contains(b)) {
                ava++;
            } else {
                pass = false;
                issue = issue + url + "\n";

            }

        }
        if (pass == true) {
            return "Pass";
        } else {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }

    }

    //22
    public String cdn(List<String> set) throws URISyntaxException, FileNotFoundException, UnsupportedEncodingException {
        boolean pass = true;
        String issue = "\n22,Issue in CDN\n";
        String url;
        String fail = "Fail";

        if (set.isEmpty()) {
            return "Pass";
        }

        Iterator<String> iterator = set.iterator();
        // System.out.println(file);
        while (iterator.hasNext()) {
            int b = 0;
            url = iterator.next();
            URI uri = new URI(url);
            String domain = uri.getHost();
            String domain1 = domain.startsWith("www.") ? domain.substring(4) : domain;
            // System.out.println("CDN url:" + url);
            for (String dn : CDN) {
                // System.out.println("CDN"+dn);
                if (domain1.contains(dn.trim())) {
                    b++;
                    //  System.out.println("CDN contains:" + dn);
                } else {

                }
            }
            if (b == 0) {
                pass = false;
                //System.out.println("cdn issue");
                issue = issue + url + "\n";
            }
        }

        if (pass == true) {
            return "Pass";
        } else {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }
    }

    //23 MaxAge(8)
    //23
    public String Max_AgeImg(Map< String, Map<String, List<String>>> mapurl) throws FileNotFoundException, UnsupportedEncodingException {
        boolean pass = true;
        String fail = "Fail";
        String url;
        String issue = "\nIssue in Image Cache \n";
        if (mapurl.isEmpty()) {
            return "Pass";
        }
        Map<String, List<String>> map = null;
        for (Map.Entry<String, Map<String, List<String>>> etry : mapurl.entrySet()) {
            // System.out.println(entry.getKey() + " : " + entry.getValue());
            url = etry.getKey();
            map = (etry.getValue());

            //  System.out.println("Printing All Response Header for URL: " + url_Object.toString() + "\n");
            int avai = 0;
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                //System.out.println(entry.getKey() + " : " + entry.getValue());
                if ((entry.getKey()) != null && (entry.getKey()).contains("Cache-Control")) {
                    avai++;
                    if (entry.getValue().toString().contains("=")) {
                        String cache = (entry.getValue()).toString();
                        // System.out.println("1***************"+cache);

                        String Cache = cache.substring(((cache.indexOf("=")) + 1), cache.indexOf("]"));
                        // System.out.println("2***************"+Cache);
                        if (Integer.parseInt(Cache) >= Integer.parseInt(jsCssFontCacheio)) {

                        } else {
                            pass = false;
                            fail = fail.concat("\n size-" + Cache + " " + url + "\n");
                            issue = issue + " size-" + Cache + " " + url + "\n";

                        }
                    } else {
                        pass = false;
                        fail = fail.concat("\n size-" + url + "\n");
                        issue = issue + " size-" + url + "\n";

                    }
                }

            }
            if (avai == 0) {
                pass = false;
                fail = fail.concat("\n size-" + url + "\n");
                issue = issue + " size-" + url + "\n";
            }

        }
        if (pass == true) {
            return "Pass";
        } else {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }
    }
//    //24
//     public String sprite(List<String> img) {
//          int spritenum=0;
//         Iterator<String> iterator = img.iterator();
//            // System.out.println(file);
//            while (iterator.hasNext()) {
//
//             String url1 = iterator.next();
//                if (url1.contains("sprite")) {
//                
//                spritenum++;
//                }
//            }
//            if(spritenum==0){
//                 String superclassName = new Exception().getStackTrace()[1].getClassName();
//          if(superclassName.contains("com.mycompany.har.PerformanceHar")){OutputWriter.appendHarProblem(issue);}
//          else if(superclassName.contains("com.mycompany.url.PerformanceUrl")){OutputWriter.appendHarProblem(issue);}
//                return "Fail";}
//            else{return "Pass";}
//            
//    }
//    
//    
//    
//    
//    
////25
//    public static int sp_height, sp_weight;
//    public static int[][] sp_arrayb;
//    public static int[][] sp_arrayc;
//
//    public String sprite_opti(List<String> set){
//        boolean pass = true;
//        String url1;
//        String fail = "Fail";
//        try {
//            Iterator<String> iterator = set.iterator();
//            // System.out.println(file);
//            while (iterator.hasNext()) {
//
//                url1 = iterator.next();
//                if (url1.contains("sprite")) {
//                    URL url = new URL(url1);
//                    // File pngInput = new File("https://d167y3o4ydtmfg.cloudfront.net/kraftrecipes/v201912252343/img/common_sprite.png");
//                    BufferedImage pngImage = ImageIO.read(url);
//                    checkTransparency(pngImage);
//                    int[][] a = new int[sp_height + 1][sp_weight + 1];
//                    // System.out.println("***********************************");
//                    // System.out.println(a.length);
////ArrayList<Integer> a=new ArrayList<Integer>();
//                    a = sp_arrayb;
//
//                    long c = printMaxSubSquare(a);
//                    //  System.out.println(c);
//                    if (((a.length) / 4) > c) {
//                    } else {
//                        pass = false;
//                        fail = fail.concat("\n" + url + "\n");
//                    }
//                }
//
//            }
//            
//        } catch (Exception ex) {
//              OutputWriter.appendToFile(ex);
//        }
//       if (pass == true) {
//
//                return "Pass";
//
//            } else {
//
//                return fail;
//            }
//    }
//
//    private static void checkTransparency(BufferedImage image) {
//
//        if (containsTransparency(image)) {
//            //System.out.println("image contains transparency");
//        } else {
//            //  System.out.println("Image does NOT contain transparency");
//        }
//    }
//
//    private static boolean containsTransparency(BufferedImage image) {
//        sp_height = image.getHeight();
//        sp_weight = image.getWidth();
//        sp_arrayb = new int[sp_height + 1][sp_weight + 1];
//        for (int i = 0; i < image.getHeight(); i++) {
//            for (int j = 0; j < image.getWidth(); j++) {
//                //System.out.print(i + " " + j + " ");
//                if (isTransparent(image, j, i)) {
//                    //  System.out.println("true");
//                    sp_arrayb[i][j] = 1;
//                } else {
//                    //  System.out.println("false");
//                    sp_arrayb[i][j] = 0;
//                }
//            }
//        }
//        return false;
//    }
//
//    public static boolean isTransparent(BufferedImage image, int x, int y) {
//        int pixel = image.getRGB(x, y);
//        //System.out.println((pixel)+" # ");
//        //  System.out.println((pixel>>24)+" # ");
//        //  System.out.println(((pixel>>24) == 0x00)+" # ");
//        return ((pixel >> 24) & 0xff) == 0x00;
//    }
//    // method for Maximum size square sub-matrix with all 1s 
//
//    static long printMaxSubSquare(int M[][]) {
//        int i, j;
//        int R = M.length;         //no of rows in M[][] 
//        int C = M[0].length;     //no of columns in M[][] 
//        int S[][] = new int[R][C];
//
//        int max_of_s, max_i, max_j;
//
//        /* Set first column of S[][]*/
//        for (i = 0; i < R; i++) {
//            S[i][0] = M[i][0];
//        }
//
//        /* Set first row of S[][]*/
//        for (j = 0; j < C; j++) {
//            S[0][j] = M[0][j];
//        }
//
//        /* Construct other entries of S[][]*/
//        for (i = 1; i < R; i++) {
//            for (j = 1; j < C; j++) {
//                if (M[i][j] == 1) {
//                    S[i][j] = Math.min(S[i][j - 1],
//                            Math.min(S[i - 1][j], S[i - 1][j - 1])) + 1;
//                } else {
//                    S[i][j] = 0;
//                }
//            }
//        }
//
//        /* Find the maximum entry, and indexes of maximum entry  
//            in S[][] */
//        max_of_s = S[0][0];
//        max_i = 0;
//        max_j = 0;
//        for (i = 0; i < R; i++) {
//            for (j = 0; j < C; j++) {
//                if (max_of_s < S[i][j]) {
//                    max_of_s = S[i][j];
//                    max_i = i;
//                    max_j = j;
//                }
//            }
//        }
//        int row = 0;
//        int col = 0;
//        //System.out.println("Maximum size sub-matrix is: ");
//        for (i = max_i; i > max_i - max_of_s; i--) {
//            row++;
//            for (j = max_j; j > max_j - max_of_s; j--) {
//                col++;
//                //System.out.print(M[i][j] + " ");
//            }
//            // System.out.println();
//        }
//
//        //c[row][col]; 
//        sp_arrayc = new int[row + 1][col + 1];
//        // System.out.println(sp_arrayc.length);
//        return sp_arrayc.length;
//    }

//31
    public String Avoid_DocumentWrite(List<String> set) throws IOException {
        String issue = "\n31Issue in Avoid_DocumentWrite: \n";
        boolean pass = true;
        String url;
        String fail = "Fail";
        if (set.isEmpty()) {
            return "Pass";
        }

        Iterator<String> iterator = set.iterator();
        // System.out.println(file);
        while (iterator.hasNext()) {

            String urlString[] = iterator.next().split(",CCTV,");
            String res = "";
            String ul = urlString[0];
            // System.out.println("Doc url:" + ul);
            try {
                res = urlString[1];
            } catch (Exception ex) {
                OutputWriter.appendToFile(ex);
                ex.printStackTrace();

            }
            if (res.contains("document.write()")) {
                //  System.out.println("contains doc.write");
                pass = false;
                issue = issue + ul + "\n";
            }
        }

        if (pass == true) {
            return "Pass";
        } else {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }
    }

    //32
    public String Avoid_CostlyExpressions(List<String> set) throws FileNotFoundException, UnsupportedEncodingException {
        boolean pass = true;
        int count = 0;
        String url;
        String fail = "Fail";
        String issue = "\n32,Issue in Avoid_CostlyExpressions\n";
        if (set.isEmpty()) {
            return "Pass";
        }

        Iterator<String> iterator = set.iterator();
        // System.out.println(file);
        while (iterator.hasNext()) {

            String urlString[] = iterator.next().split(",CCTV,");
            String res = "";
            String ul = urlString[0];
            // System.out.println("costly url:" + ul);
            try {
                res = urlString[1];
            } catch (Exception ex) {
                OutputWriter.appendToFile(ex);
                ex.printStackTrace();

            }
            int num = getcount("()", res);
            // System.out.println("count ()" + num);
            if (num > 3) {
                pass = false;
                issue = issue + ul + "\n";
            }

        }

        if (pass == true) {
            return "Pass";
        } else {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }
    }

    //37
    public String etag(List<String> set, List<String> set1, List<String> set2, List<String> set3, List<String> set4) throws IOException {
        boolean pass = true;
        String url;
        String fail = "Fail";
        String issue = "\nIssue in Etag\n";
        if (set.isEmpty() && set1.isEmpty() && set2.isEmpty() && set3.isEmpty() && set4.isEmpty()) {
            return "Pass";
        }
        try {
            Iterator<String> iterator = set.iterator();

            while (iterator.hasNext()) {

                url = iterator.next();
                try {
                    URL url_Object = new URL(url);
                    URLConnection urlConnection_Obj = url_Object.openConnection();
                    Map<String, List<String>> map = urlConnection_Obj.getHeaderFields();
                    int avai = 0;
                    for (Map.Entry<String, List<String>> entry : map.entrySet()) {

                        if ((entry.getKey()) != null && (entry.getKey()).contains("ETag")) {
                            if (entry.getValue().toString().equals("")) {
                                avai++;
                                pass = false;
                                fail = fail.concat("\n" + url + "\n");
                                issue = issue + url + "\n";
                            }
                        }
                    }
                    if (avai == 0) {
                        pass = false;
                        fail = fail.concat("\n" + url + "\n");
                        issue = issue + url + "\n";
                    }

                } catch (IOException ex) {
                    OutputWriter.appendToFile(ex);
                    ex.printStackTrace();
                }
            }
            Iterator<String> iterator1 = set1.iterator();

            while (iterator1.hasNext()) {

                url = iterator1.next();
                try {
                    URL url_Object = new URL(url);
                    URLConnection urlConnection_Obj = url_Object.openConnection();
                    Map<String, List<String>> map = urlConnection_Obj.getHeaderFields();
                    int avai = 0;
                    for (Map.Entry<String, List<String>> entry : map.entrySet()) {

                        if ((entry.getKey()) != null && (entry.getKey()).contains("ETag")) {
                            if (entry.getValue().toString().equals("")) {
                                avai++;
                                pass = false;
                                fail = fail.concat("\n" + url + "\n");
                                issue = issue + url + "\n";
                            }
                        }
                    }
                    if (avai == 0) {
                        pass = false;
                        fail = fail.concat("\n" + url + "\n");
                        issue = issue + url + "\n";
                    }

                } catch (IOException ex) {
                    OutputWriter.appendToFile(ex);
                    ex.printStackTrace();
                }
            }
            Iterator<String> iterator2 = set2.iterator();

            while (iterator2.hasNext()) {

                url = iterator2.next();
                try {
                    URL url_Object = new URL(url);
                    URLConnection urlConnection_Obj = url_Object.openConnection();
                    Map<String, List<String>> map = urlConnection_Obj.getHeaderFields();
                    int avai = 0;
                    for (Map.Entry<String, List<String>> entry : map.entrySet()) {

                        if ((entry.getKey()) != null && (entry.getKey()).contains("ETag")) {
                            if (entry.getValue().toString().equals("")) {
                                avai++;
                                pass = false;
                                fail = fail.concat("\n" + url + "\n");
                                issue = issue + url + "\n";
                            }
                        }
                    }
                    if (avai == 0) {
                        pass = false;
                        fail = fail.concat("\n" + url + "\n");
                        issue = issue + url + "\n";
                    }

                } catch (IOException ex) {
                    OutputWriter.appendToFile(ex);
                    ex.printStackTrace();
                }
            }
            Iterator<String> iterator3 = set3.iterator();

            while (iterator3.hasNext()) {

                url = iterator3.next();
                try {
                    URL url_Object = new URL(url);
                    URLConnection urlConnection_Obj = url_Object.openConnection();
                    Map<String, List<String>> map = urlConnection_Obj.getHeaderFields();
                    int avai = 0;
                    for (Map.Entry<String, List<String>> entry : map.entrySet()) {

                        if ((entry.getKey()) != null && (entry.getKey()).contains("ETag")) {
                            if (entry.getValue().toString().equals("")) {
                                avai++;
                                pass = false;
                                fail = fail.concat("\n" + url + "\n");
                                issue = issue + url + "\n";
                            }
                        }
                    }
                    if (avai == 0) {
                        pass = false;
                        fail = fail.concat("\n" + url + "\n");
                        issue = issue + url + "\n";
                    }

                } catch (IOException ex) {
                    OutputWriter.appendToFile(ex);
                    ex.printStackTrace();
                }
            }

        } catch (Exception ex) {
            OutputWriter.appendToFile(ex);
            ex.printStackTrace();
        }
        if (pass == true) {
            return "Pass";
        } else {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }
    }

    //38
    public String reduceNo(List<String> set) throws FileNotFoundException, UnsupportedEncodingException {
        String issue = "\n38,Issue in no of API Issue\n";
        int maxCall = Integer.parseInt(callCount);
        if (set.isEmpty()) {
            return "Pass";
        }
        if (set.size() <= maxCall) {
            return "Pass";
        } else {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }
    }

    //39
//    public String cacheApi(Map< String, Map<String, List<String>>> mapurl) throws FileNotFoundException, UnsupportedEncodingException {
//        String issue = "\nIssue in cache Api\n";
//        boolean pass = true;
//        String fail = "Fail";
//        String url;
//        if (mapurl.isEmpty()) {
//            return "Pass";
//        }
//
//        Map<String, List<String>> map = null;
//        for (Map.Entry<String, Map<String, List<String>>> etry : mapurl.entrySet()) {
//            // System.out.println(entry.getKey() + " : " + entry.getValue());
//            url = etry.getKey();
//            map = (etry.getValue());
//
//            //  System.out.println("Printing All Response Header for URL: " + url_Object.toString() + "\n");
//            int avai = 0;
//            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
//                //System.out.println(entry.getKey() + " : " + entry.getValue());
//                if ((entry.getKey()) != null && (entry.getKey()).contains("Cache-Control")) {
//                    avai++;
//                    if (url.contains("xact")) {
//                        if (entry.getValue().toString().toLowerCase().equals("no-cache".toLowerCase())) {
//
//                        } else {
//                            pass = false;
//                            fail = fail.concat("\n" + url + "\n");
//                            issue = issue + url + "\n";
//                        }
//
//                    } else {
//                        if (entry.getValue().toString().contains("=")) {
//                            String cache = (entry.getValue()).toString();
//                            // System.out.println("1***************"+cache);
//
//                            String Cache = cache.substring(((cache.indexOf("=")) + 1), cache.indexOf("]"));
//                            // System.out.println("2***************"+Cache);
//                            if (Integer.parseInt(Cache) >= Integer.parseInt(jsCssFontCacheio)) {
//
//                            } else {
//                                pass = false;
//                                fail = fail.concat("\n size-" + Cache + " " + url + "\n");
//                                issue = issue + url + " size-" + Cache + " " + url + "\n";
//
//                            }
//                        } else {
//                            pass = false;
//                            fail = fail.concat("\n size-" + url + "\n");
//                            issue = issue + url + "size-" + url + "\n";
//
//                        }
//                    }
//                }
//
//            }
//            if (avai == 0) {
//                pass = false;
//                fail = fail.concat("\n size-" + url + "\n");
//                issue = issue + url + "size-" + url + "\n";
//            }
//
//        }
//        if (pass == true) {
//            return "Pass";
//        } else {
//            String superclassName = new Exception().getStackTrace()[1].getClassName();
//            if (superclassName.contains("com.mycompany.har.PerformanceHar")) {
//                OutputWriter.appendHarProblem(issue);
//            } else if (superclassName.contains("com.mycompany.url.PerformanceUrl")) {
//                OutputWriter.appendUrlProblem(issue);
//            }
//            return "Fail";
//        }
//
//    }
    //40
    public static String alive(Map< String, Map<String, List<String>>> mapurl) throws FileNotFoundException, UnsupportedEncodingException {
        boolean pass = true;
        String url;
        String issue = "\nIssue in alive\n";
        String fail = "Fail";
        if (mapurl.isEmpty()) {
            return "Pass";
        }
        Map<String, List<String>> map = null;
        for (Map.Entry<String, Map<String, List<String>>> etry : mapurl.entrySet()) {
            // System.out.println(entry.getKey() + " : " + entry.getValue());
            url = etry.getKey();
            map = (etry.getValue());
            int avail = 0;
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {

                if ((entry.getKey()) != null && (entry.getKey()).contains("Connection".toLowerCase())) {
                    avail++;
                    if ((entry.getValue().toString().toLowerCase()).contains("keep-alive".toLowerCase())) {
                        //  System.out.println(entry.getKey()+" "+entry.getValue());
                        break;
                    } else {
                        pass = false;
                        fail = fail.concat("\n" + url + "\n");
                        issue = issue + url + "\n";
                    }
                }

            }
            if (avail == 0) {
                pass = false;
                fail = fail.concat("\n" + url + "\n");
                issue = issue + url + "\n";
            }

        }

        if (pass != false) {
            return "Pass";
        } else {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }

    }

    //64
    public String duplicate1(List<String> set) {
        boolean pass = true;
        String issue = "\nIssue in duplicate Assert:\n";
        String fail = "Fail";
        if (set.isEmpty()) {
            return "Pass";
        }
        try {
            java.util.HashSet unique = new HashSet();
//            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//            System.out.println("set ->"+set);
//            System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            for (String s : set) {
                if (!unique.add(s)) {   //  // java.util.Set only unique object so if object will not bee add in Set it will return false so can consider it as Duplicate
                    pass = false;
                    issue = issue + "\n" + s;
                }

            }
            //  System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            // System.out.println("duplicate" + issue);
            // System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$");

        } catch (Exception ex) {
            OutputWriter.appendToFile(ex);
            ex.printStackTrace();
        }
        if (pass == true) {
            return "Pass";
        } else {
            OutputWriter.appendHarProblem(issue);
            return "Fail";
        }
    }

    //42
    public String duplicate(List<String> set) {
        boolean pass = true;
        String fail = "Fail";
        String issue = "\nIssue in duplicate api:\n";
        if (set.isEmpty()) {
            return "Pass";
        }
        try {
            java.util.HashSet unique = new HashSet();
            java.util.HashSet res = new HashSet();

            for (String s : set) {
                if (s.contains(",")) {
                    String arr[] = s.split(",");
                    int a = 0;
                    int b = 0;
                    if (!unique.add(arr[0])) {   //  // java.util.Set only unique object so if object will not bee add in Set it will return false so can consider it as Duplicate
                        a = 1;
                        //  System.out.println("###same url");
                    }
                    if (!res.add(arr[1])) {
                        b = 1;
                        // System.out.println("###same response");

                    }
                    if (a == 1 && b == 1) {
                        // System.out.println("###same" + arr[0]);
                        pass = false;
                        issue = issue + s + "\n";

                    }
                } else {
                    if (!unique.add(s)) {   //  // java.util.Set only unique object so if object will not bee add in Set it will return false so can consider it as Duplicate
                        issue = issue + s + "\n";

                    }
                }

            }

        } catch (Exception ex) {
            OutputWriter.appendToFile(ex);
            ex.printStackTrace();
        }

        if (pass == true) {
            return "Pass";
        } else {
            OutputWriter.appendHarProblem(issue);
            return "Fail";
        }
    }

    //46
    public String response(String Pageurl) throws IOException {
        String issue = "\n46,Issue in response code\n" + Pageurl + "\n";
        URL url = null;
        try {
            url = new URL(Pageurl);
        } catch (MalformedURLException ex) {
            OutputWriter.appendToFile(ex);
            Logger.getLogger(TestCase.class.getName()).log(Level.SEVERE, null, ex);
        }
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) url.openConnection();
        } catch (IOException ex) {
            OutputWriter.appendToFile(ex);
            ex.printStackTrace();
        }
        try {
            connection.setRequestMethod("GET");
        } catch (ProtocolException ex) {
            OutputWriter.appendToFile(ex);
            ex.printStackTrace();
        }
        try {
            connection.connect();
        } catch (IOException ex) {
            OutputWriter.appendToFile(ex);
            ex.printStackTrace();
        }

        String code = null;
        try {
            code = "" + connection.getResponseCode();
        } catch (IOException ex) {
            OutputWriter.appendToFile(ex);
            ex.printStackTrace();
        }
        //  System.out.println(code);
        if (code.matches("2[0-9]{2}")) {
            return "pass";
        } else {
            String superclassName = new Exception().getStackTrace()[1].getClassName();

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }

    }

    //49
    public String noDomain(List<String> set, List<String> set1, List<String> set2) throws FileNotFoundException, UnsupportedEncodingException {
        int maxCall = Integer.parseInt(callCount);
        String issue = "49,Issue in Api, Css, Js  No. of Domain is more than max call:" + maxCall + "\n";

        if (set.isEmpty()) {
            return "Pass";
        }
        try {
            if (set.size() <= maxCall) {
                if (set1.size() <= maxCall) {
                    if (set2.size() <= maxCall) {
                        return "Pass";
                    } else {
                        String superclassName = new Exception().getStackTrace()[1].getClassName();

                        OutputWriter.appendHarProblem(issue);

                        return "Fail";
                    }
                } else {

                    OutputWriter.appendHarProblem(issue);

                    return "Fail";
                }
            } else {

                OutputWriter.appendHarProblem(issue);

                return "Fail";
            }
        } catch (Exception ex) {
            OutputWriter.appendToFile(ex);
            ex.printStackTrace();
        }

        OutputWriter.appendHarProblem(issue);

        return "Fail";
    }

    //59
    public String skavaStream(List<String> set) throws FileNotFoundException, UnsupportedEncodingException {
        String issue = "\n56,Issue in skavaStream in Api\n";
        boolean pass = true;
        String url;
        String fail = "Fail";
        if (set.isEmpty()) {
            return "Pass";
        }
        try {
            Iterator<String> iterator = set.iterator();

            while (iterator.hasNext()) {

                url = iterator.next().toLowerCase();
                if (url.contains("skavastream") || url.contains("orchestrationservices")) {

                } else {
                    pass = false;
                    fail = fail.concat("\n" + url + "\n");
                    issue = issue + url + "\n";
                }
            }

        } catch (Exception ex) {
            OutputWriter.appendToFile(ex);
            ex.printStackTrace();
        }
        if (pass == true) {
            return "Pass";
        } else {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }
    }
//57

    public static String Max_AgeHtml(Map< String, Map<String, List<String>>> mapurl) throws FileNotFoundException, UnsupportedEncodingException {
        boolean pass = true;
        String fail = "Fail";
        String issue = "\n Issue in Max_Age Html Cache\n";

        String url = null;
        Map<String, List<String>> map = null;
        for (Map.Entry<String, Map<String, List<String>>> entryy : mapurl.entrySet()) {
            // System.out.println(entry.getKey() + " : " + entry.getValue());
            url = entryy.getKey();
            map = (entryy.getValue());

            //  System.out.println("Printing All Response Header for URL: " + url_Object.toString() + "\n");
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                //System.out.println(entry.getKey() + " : " + entry.getValue());
                if ((entry.getKey()) != null && (entry.getKey()).contains("Cache-Control")) {

                    // System.out.println(entry.getValue().toString().contains("="));
                    if (entry.getValue().toString().contains("=")) {
                        String cache = (entry.getValue()).toString();
                        // System.out.println("1***************"+cache);

                        String Cache = cache.substring(((cache.indexOf("=")) + 1), cache.indexOf("]"));
                        // System.out.println("2***************"+Cache);
                        if (Integer.parseInt(Cache) >= Integer.parseInt(htmlCacheio)) {

                            // int myInt = (Integer.parseInt(Cache) >= Integer.parseInt(htmlCacheio)) ? 1 : 0;
                            // System.out.println("3***************");
                        } else {
                            pass = false;
                            fail = fail.concat("\n size-" + Cache + " " + url + "\n");
                            issue = issue + "\n size-" + Cache + " " + url + "\n";
                            //  int myInt = (Integer.parseInt(Cache) >= Integer.parseInt(htmlCacheio)) ? 1 : 0;
                            // System.out.println("4***************"+myInt);
                        }
                    } else {
                        pass = false;
                        fail = fail.concat("\n size-" + url + "\n");
                        issue = issue + "\n size-" + url + "\n";
                        // int myInt = (Integer.parseInt(Cache) >= Integer.parseInt(htmlCacheio)) ? 1 : 0;}
                    }

                }
            }
        }
        if (pass == true) {
            return "Pass";
        } else {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }

    }
//58

    public static String Max_AgeFont(Map< String, Map<String, List<String>>> mapurl) throws FileNotFoundException, UnsupportedEncodingException {
        boolean pass = true;
        String issue = "\nIssue in Max_AgeFont\n";
        String fail = "Fail";

        String url = null;
        Map<String, List<String>> map = null;
        for (Map.Entry<String, Map<String, List<String>>> entryy : mapurl.entrySet()) {
            // System.out.println(entry.getKey() + " : " + entry.getValue());
            url = entryy.getKey();
            map = (entryy.getValue());

            //  System.out.println("Printing All Response Header for URL: " + url_Object.toString() + "\n");
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                //System.out.println(entry.getKey() + " : " + entry.getValue());
                if ((entry.getKey()) != null && (entry.getKey()).contains("Cache-Control")) {

                    // System.out.println(entry.getValue().toString().contains("="));
                    if (entry.getValue().toString().contains("=")) {
                        String cache = (entry.getValue()).toString();
                        // System.out.println("1***************"+cache);

                        String Cache = cache.substring(((cache.indexOf("=")) + 1), cache.indexOf("]"));
                        // System.out.println("2***************"+Cache);
                        if (Integer.parseInt(Cache) >= Integer.parseInt(jsCssFontCacheio)) {

                            // int myInt = (Integer.parseInt(Cache) >= Integer.parseInt(htmlCacheio)) ? 1 : 0;
                            // System.out.println("3***************");
                        } else {
                            pass = false;
                            fail = fail.concat("\n size-" + Cache + " " + url + "\n");
                            issue = issue + "\n size-" + Cache + " " + url + "\n";
                            //  int myInt = (Integer.parseInt(Cache) >= Integer.parseInt(htmlCacheio)) ? 1 : 0;
                            // System.out.println("4***************"+myInt);
                        }
                    } else {
                        pass = false;
                        fail = fail.concat("\n size-" + url + "\n");
                        issue = issue + "\n size-" + url + "\n";
                        // int myInt = (Integer.parseInt(Cache) >= Integer.parseInt(htmlCacheio)) ? 1 : 0;}
                    }

                }
            }
        }
        if (pass == true) {
            return "Pass";
        } else {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }

    }

    // security
//      public String Transport_Security(List<String> set) throws InterruptedException {
//        
//        boolean pass = true;
//        String url;
//        String fail = "Fail";
//if(set.isEmpty()){return "Pass";}
//        Iterator<String> iterator = set.iterator();
//
//        while (iterator.hasNext()) {
//
//            url = iterator.next();
//            try {
//                URL url_Object = new URL(url);
//                URLConnection urlConnection_Obj = url_Object.openConnection();
//                Map<String, List<String>> map = urlConnection_Obj.getHeaderFields();
//                for (Map.Entry<String, List<String>> entry : map.entrySet()) {
//
//                    if ((entry.getKey()) != null && (entry.getKey()).contains("Strict-Transport-Security")) {
//                        if ((entry.getValue()).contains("max-age=78840;")) {
//
//                        } else {
//                            pass=false;
//                            fail = fail.concat("\n" + url + "\n");
//                        }
//                    }
//
//                }
//                if (pass == true) {
//                    return "Pass";
//                } else {
//
//                    return fail;
//                }
//
//            } catch (IOException e) {
//            }
//        }
//
//        return "error";
//    }
//        public String X_Content_Type (List<String> set) throws InterruptedException {
//        
//        boolean pass = true;
//        String url;
//        String fail = "Fail";
//if(set.isEmpty()){return "Pass";}
//        Iterator<String> iterator = set.iterator();
//
//        while (iterator.hasNext()) {
//
//            url = iterator.next();
//            try {
//                URL url_Object = new URL(url);
//                URLConnection urlConnection_Obj = url_Object.openConnection();
//                Map<String, List<String>> map = urlConnection_Obj.getHeaderFields();
//                for (Map.Entry<String, List<String>> entry : map.entrySet()) {
//
//                    if ((entry.getKey()) != null && (entry.getKey()).contains("X-Content-Type-Options")) {
//                        if ((entry.getValue()).contains("nosniff")) {
//
//                        } else {
//                            pass=false;
//                            fail = fail.concat("\n" + url + "\n");
//                        }
//                    }
//
//                }
//                if (pass == true) {
//                    return "Pass";
//                } else {
//
//                    return fail;
//                }
//
//            } catch (IOException e) {
//            }
//        }
//
//        return "error";
//    }
//          public String X_FRAME_OPTIONS(List<String> set) throws InterruptedException {
//        
//        boolean pass = true;
//        String url;
//        String fail = "Fail";
//if(set.isEmpty()){return "Pass";}
//        Iterator<String> iterator = set.iterator();
//
//        while (iterator.hasNext()) {
//
//            url = iterator.next();
//            try {
//                URL url_Object = new URL(url);
//                URLConnection urlConnection_Obj = url_Object.openConnection();
//                Map<String, List<String>> map = urlConnection_Obj.getHeaderFields();
//                for (Map.Entry<String, List<String>> entry : map.entrySet()) {
//
//                    if ((entry.getKey()) != null && (entry.getKey()).contains("X-FRAME-OPTIONS")) {
//                        if ((entry.getValue()).contains("SAMEORIGIN")) {
//
//                        } else {
//                            pass=false;
//                            fail = fail.concat("\n" + url + "\n");
//                        }
//                    }
//
//                }
//                if (pass == true) {
//                    return "Pass";
//                } else {
//
//                    return fail;
//                }
//
//            } catch (IOException e) {
//            }
//        }
//
//        return "error";
//    }
//            public String X_Robots_Tag(List<String> set) throws InterruptedException {
//        
//        boolean pass = true;
//        String url;
//        String fail = "Fail";
//if(set.isEmpty()){return "Pass";}
//        Iterator<String> iterator = set.iterator();
//
//        while (iterator.hasNext()) {
//
//            url = iterator.next();
//            try {
//                URL url_Object = new URL(url);
//                URLConnection urlConnection_Obj = url_Object.openConnection();
//                Map<String, List<String>> map = urlConnection_Obj.getHeaderFields();
//                for (Map.Entry<String, List<String>> entry : map.entrySet()) {
//
//                    if ((entry.getKey()) != null && (entry.getKey()).contains("X-Robots-Tag")) {
//                        if ((entry.getValue()).contains("noindex, nofollow")) {
//
//                        } else {
//                            pass=false;
//                            fail = fail.concat("\n" + url + "\n");
//                        }
//                    }
//
//                }
//                if (pass == true) {
//                    return "Pass";
//                } else {
//
//                    return fail;
//                }
//
//            } catch (IOException e) {
//            }
//        }
//
//        return "error";
//    }
//              public String X_XSS_Protection(List<String> set) throws InterruptedException {
//        
//        boolean pass = true;
//        String url;
//        String fail = "Fail";
//if(set.isEmpty()){return "Pass";}
//        Iterator<String> iterator = set.iterator();
//
//        while (iterator.hasNext()) {
//
//            url = iterator.next();
//            try {
//                URL url_Object = new URL(url);
//                URLConnection urlConnection_Obj = url_Object.openConnection();
//                Map<String, List<String>> map = urlConnection_Obj.getHeaderFields();
//                for (Map.Entry<String, List<String>> entry : map.entrySet()) {
//
//                    if ((entry.getKey()) != null && (entry.getKey()).contains("X-XSS-Protection")) {
//                        if ((entry.getValue()).contains("1; mode=block")) {
//
//                        } else {
//                            pass=false;
//                            fail = fail.concat("\n" + url + "\n");
//                        }
//                    }
//
//                }
//                if (pass == true) {
//                    return "Pass";
//                } else {
//
//                    return fail;
//                }
//
//            } catch (IOException e) {
//            }
//        }
//
//        return "error";
//    }
//    
//    
//    
    //59
    public static String Transport_Security(Map< String, Map<String, List<String>>> mapurl) throws FileNotFoundException, UnsupportedEncodingException {
        String issue = "\nIssue Transport_Security\n";
        boolean passCondition = false;
        Map<String, List<String>> map = null;
        String url;
        for (Map.Entry<String, Map<String, List<String>>> entryy : mapurl.entrySet()) {
            // System.out.println(entry.getKey() + " : " + entry.getValue());
            url = entryy.getKey();
            map = (entryy.getValue());

            for (Map.Entry<String, List<String>> entry : map.entrySet()) {

                if ((entry.getKey()) != null && (entry.getKey()).contains("Strict-Transport-Security")) {
                    String cache = (entry.getValue()).toString();
                    // System.out.println("1***************"+cache);
                    if (cache.contains("=")) {
                        if (cache.contains(";")) {
                            String Cache = cache.substring(((cache.indexOf("=")) + 1), cache.indexOf(";"));
                            // System.out.println("2***************"+Cache);
                            //  System.out.println("transport_Security"+transport_Security);
                            if (Integer.parseInt(Cache) >= Integer.parseInt(transport_Security)) {
                                //  System.out.println("Cache"+Cache);
                                passCondition = true;
                                break;
                                // int myInt = (Integer.parseInt(Cache) >= Integer.parseInt(jsCssFontCacheio)) ? 1 : 0;

//System.out.println("3***************"+myInt);
                            } else {
                                issue = issue + "url";

                            }
                        } else {

                            String Cache = cache.substring(((cache.indexOf("=")) + 1), cache.indexOf("]"));
                            // System.out.println("2***************"+Cache);
                            // System.out.println("transport_Security"+transport_Security);
                            if (Integer.parseInt(Cache) >= Integer.parseInt(transport_Security)) {
                                // System.out.println("Cache"+Cache);
                                passCondition = true;
                                break;
                                // int myInt = (Integer.parseInt(Cache) >= Integer.parseInt(jsCssFontCacheio)) ? 1 : 0;

//System.out.println("3***************"+myInt);
                            } else {
                                issue = issue + "url";
                            }
                        }

                    }
                } else {
                }
            }
        }
        if (passCondition == true) {

            //  System.out.println("Compress:Pass");
            return "Pass";

        } else {
            issue = issue + "url";

            OutputWriter.appendHarProblem(issue);

            //  System.out.println("Compress:Fail");
            return "Fail";
        }

    }
    //60

    public String X_Content_Type(Map< String, Map<String, List<String>>> mapurl) throws FileNotFoundException, UnsupportedEncodingException {
        String issue = "\nIssue in X_Content_Type:\n";
        boolean pass = true;
        String fail = "Fail";
        String url = null;
        Map<String, List<String>> map = null;
        for (Map.Entry<String, Map<String, List<String>>> entryy : mapurl.entrySet()) {
            // System.out.println(entry.getKey() + " : " + entry.getValue());
            url = entryy.getKey();
            map = (entryy.getValue());

            int avail = 0;
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                //System.out.println("5");
                if ((entry.getKey()) != null && (entry.getKey()).toLowerCase().equals(
                        "X-Content-Type".toLowerCase())) {
                    avail++;
                    if ((entry.getValue()).toString().toLowerCase().contains("nosniff".toLowerCase())) {
                        // System.out.println("url find "+entry.getKey()+entry.getValue());
                        break;
                    } else {
                        // System.out.println("url find "+entry.getKey()+entry.getValue());
                        pass = false;
                        fail = fail + "\n" + url;
                        issue = issue + "in url:" + url;
                    }
                }
            }
            if (avail == 0) {
                pass = false;
                fail = fail + "\n" + url;
            }
        }
        if (pass == true) {
            return "Pass";
        } else {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }

    }
//    //61

    public String X_FRAME_OPTIONS(Map< String, Map<String, List<String>>> mapurl) throws FileNotFoundException, UnsupportedEncodingException {
        String issue = "\nIssue in X_FRAME_OPTIONS:\n";
        boolean pass = true;
        String fail = "Fail";
        String url = null;
        Map<String, List<String>> map = null;
        for (Map.Entry<String, Map<String, List<String>>> entryy : mapurl.entrySet()) {
            // System.out.println(entry.getKey() + " : " + entry.getValue());
            url = entryy.getKey();
            map = (entryy.getValue());

            int avail = 0;
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                //System.out.println("5");
                if ((entry.getKey()) != null && (entry.getKey()).toLowerCase().equals(
                        "X-FRAME-OPTIONS".toLowerCase())) {
                    avail++;
                    if ((entry.getValue()).toString().toLowerCase().contains("SAMEORIGIN".toLowerCase())) {
                        // System.out.println("url find "+entry.getKey()+entry.getValue());
                        break;
                    } else {
                        // System.out.println("url find "+entry.getKey()+entry.getValue());
                        pass = false;
                        fail = fail + "\n" + url;
                        issue = issue + "in url:" + url;
                    }
                }
            }
            if (avail == 0) {
                pass = false;
                fail = fail + "\n" + url;
            }
        }
        if (pass == true) {
            return "Pass";
        } else {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }

    }

    //62
    public String X_Robots_Tag(Map< String, Map<String, List<String>>> mapurl) throws FileNotFoundException, UnsupportedEncodingException {
        String issue = "\nIssue in X_Robots_Tag:\n";
        boolean pass = true;
        String fail = "Fail";
        String url = null;
        Map<String, List<String>> map = null;
        for (Map.Entry<String, Map<String, List<String>>> entryy : mapurl.entrySet()) {
            // System.out.println(entry.getKey() + " : " + entry.getValue());
            url = entryy.getKey();
            map = (entryy.getValue());

            int avail = 0;
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                //System.out.println("5");
                if ((entry.getKey()) != null && (entry.getKey()).toLowerCase().equals(
                        "X-Robots-Tag".toLowerCase())) {
                    avail++;
                    if ((entry.getValue()).toString().toLowerCase().contains("noindex, nofollow".toLowerCase())) {
                        // System.out.println("url find "+entry.getKey()+entry.getValue());
                        break;
                    } else {
                        // System.out.println("url find "+entry.getKey()+entry.getValue());
                        pass = false;
                        fail = fail + "\n" + url;
                        issue = issue + "in url:" + url;
                    }
                }
            }
            if (avail == 0) {
                pass = false;
                fail = fail + "\n" + url;
            }
        }
        if (pass == true) {
            return "Pass";
        } else {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }

    }
    //63

    public String X_XSS_Protection(Map< String, Map<String, List<String>>> mapurl) throws FileNotFoundException, UnsupportedEncodingException {
        String issue = "\nIssue in X_XSS_Protection:\n";
        boolean pass = true;
        String fail = "Fail";
        String url = null;
        Map<String, List<String>> map = null;
        for (Map.Entry<String, Map<String, List<String>>> entryy : mapurl.entrySet()) {
            // System.out.println(entry.getKey() + " : " + entry.getValue());
            url = entryy.getKey();
            map = (entryy.getValue());

            int avail = 0;
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                //System.out.println("5");
                if ((entry.getKey()) != null && (entry.getKey()).toLowerCase().equals(
                        "X-XSS-Protection".toLowerCase())) {
                    avail++;
                    if ((entry.getValue()).toString().toLowerCase().contains("1; mode=block".toLowerCase())) {
                        // System.out.println("url find "+entry.getKey()+entry.getValue());
                        break;
                    } else {
                        // System.out.println("url find "+entry.getKey()+entry.getValue());
                        pass = false;
                        fail = fail + "\n" + url;
                        issue = issue + "in url:" + url;
                    }
                }
            }
            if (avail == 0) {
                pass = false;
                fail = fail + "\n" + url;
            }
        }
        if (pass == true) {
            return "Pass";
        } else {

            OutputWriter.appendHarProblem(issue);

            return "Fail";
        }

    }

}
