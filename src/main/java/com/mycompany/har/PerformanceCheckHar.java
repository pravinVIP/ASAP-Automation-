package com.mycompany.har;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import static com.mycompany.input.InputReader.CDN;
import static com.mycompany.input.InputReader.Domainio;
import static com.mycompany.input.InputReader.ImgCacheio;
import static com.mycompany.input.InputReader.htmlCacheio;
import static com.mycompany.input.InputReader.apiCacheio;
import java.util.Collection;
import com.mycompany.performancetest.TestCase;
import static com.mycompany.performancetest.ThreadRunningToTrack.write;
import static com.mycompany.userInterface.Ui.harlList;

import static com.mycompany.output.OutputWriter.st;
import static com.mycompany.userInterface.ProcessView.loadBar;
import static com.mycompany.userInterface.ProcessView.loadTxt;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import static com.mycompany.input.InputReader.jsCssFontCacheio;
import static com.mycompany.input.InputReader.transport_Security;
import com.mycompany.output.OutputWriter;
import static com.mycompany.output.OutputWriter.writer;

import static com.mycompany.userInterface.Ui.env;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.InitialDirContext;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author pravin.a
 */
class CustomObject1 {

    String value1;
    Map<String, List<String>> value2;

    CustomObject1(String v1, Map<String, List<String>> v2) {
        value1 = v1;
        value2 = v2;
    }
}

public class PerformanceCheckHar {

    public static String domain = "";
    public static int xcol;
    public static List<String> img = new LinkedList();
    public static List<String> font = new LinkedList();
    public static List<String> js = new LinkedList();
    public static List<String> css = new LinkedList();
    public static List<String> api = new LinkedList();
    public static List<String> css4 = new LinkedList();
    public static List<String> js4 = new LinkedList();
    public static String html4 = "";
    public static List<String> api4 = new LinkedList();
    public static String html = "";
    public static String htmlEncoding = "Pass";
    public static String htmlcharacterSet = "Pass";

    public static String jsCompress = "Pass";
    public static String jscache = "Pass";
    public static String cssCompress = "Pass";
    public static String csscache = "Pass";
    public static String etag = "Pass";
    public static String cdnAssert = "Pass";
    public static String respond = "Pass";
    public static String imgAge = "Pass";
    public static String fontAge = "Pass";
    public static String ConnectionAlive = "Pass";

    public static String apiCompress = "Pass";
    public static String apicache = "Pass";

    public static String htmlcache = "Pass";
    public static String fontcache = "Pass";
    public static String X_Content_Type = "Pass";
    public static String Transport_Security = "Pass";
    public static String X_FRAME_OPTIONS = "Pass";
    public static String X_Robots_Tag = "Pass";
    public static String X_XSS_Protection = "Pass";

    public static String IssuehtmlEncoding = "";
    public static String IssuehtmlcharacterSet = "";

    public static String IssuejsCompress = "";
    public static String Issuejscache = "";
    public static String IssuecssCompress = "";
    public static String Issuecsscache = "";
    public static String Issueetag = "";
    public static String IssuecdnAssert = "";
    public static String Issuerespond = "";
    public static String IssueimgAge = "";
    public static String IssuefontAge = "";
    public static String IssueConnectionAlive = "";
    public static String IssueapiCompress = "";
    public static String Issueapicache = "";

    public static String Issuehtmlcache = "";
    public static String Issuefontcache = "";
    public static String IssueX_Content_Type = "";
    public static String IssueTransport_Security = "";
    public static String IssueX_FRAME_OPTIONS = "";
    public static String IssueX_Robots_Tag = "";
    public static String IssueX_XSS_Protection = "";

    public static String filePath;
    public static String fileTag;

    public static List<String> img1 = new LinkedList();
    public static List<String> font1 = new LinkedList();
    public static List<String> js1 = new LinkedList();
    public static List<String> html1 = new LinkedList();
    public static List<String> css1 = new LinkedList();
    public static List<String> api1 = new LinkedList();
    public static Map< String, String> all = new HashMap< String, String>();

    List<CustomObject1> img2 = new LinkedList();
    List<CustomObject1> font2 = new LinkedList();
    List<CustomObject1> js2 = new LinkedList();
    List<CustomObject1> css2 = new LinkedList();
    List<CustomObject1> api2 = new LinkedList();
    //  List<CustomObject1> html2 = new LinkedList();

    PerformanceCheckHar() {
    }

    private static String readAllBytesJava7(String filePath) throws IOException {
        String content = "";

        content = new String(Files.readAllBytes(Paths.get(filePath)));

        return content;
    }

    public void tst() throws IOException {

        TestCase test = new TestCase();
        int i;
        String get;
        Cell read = null;

        Row row1 = st.getRow(4);
        Cell c11 = row1.createCell(xcol);
        c11.setCellValue(fileTag);
        for (i = 6; i <= 60; i++) {
            try {

                Row row = st.getRow(i);

                Cell c = row.getCell(2);

                get = c.getStringCellValue();

                //1-Minify HTML
                if ((get).contains("Minify HTML")) {

                    String minify = null;
                    if (html4 == "") {
                        row = st.getRow(i);
                        Cell c1 = row.createCell(xcol);
                        c1.setCellValue("N/A");
                        continue;
                    }

                    try {

                        minify = test.Minify_HTML(html4);
                       
                    } catch (IOException ex) {
                        OutputWriter.appendToFile(ex);
                        ex.printStackTrace();
                    }
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(minify);

                }
                //67 
                //check dns lookup and check contains CDN domains(From Input file)
                if ((get).contains("PDN passes through CDN")) {
                    String compress = "N/A";
                    String issue = "67,Issue in CDN for PDN site:\n";

                    if (env.trim().equalsIgnoreCase("pdn")) {
                        String url = html;
                        URI uri = null;

                        uri = new URI(url);

                        String domain = uri.getHost();

                        
                        Properties env = new Properties();
                        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
                        InitialDirContext idc = new InitialDirContext(env);
                        Attributes attrs = idc.getAttributes(domain, new String[]{"CNAME"});
                        Attribute attr = attrs.get("CNAME");
                        String cname = attr.get().toString();
                        int b = 0;
                        for (String dn : CDN) {
                           
                            if (cname.contains(dn.trim())) {
                                b++;
                               
                            } else {

                            }
                        }
                        if (b != 0) {
                            compress = "Pass";
                        } else {
                            compress = "Fail";

                            issue = issue + url + "\n";
                            OutputWriter.appendHarProblem(issue);

                        }

                    }

                    row = st.getRow(i);

                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(compress);
                }
                //66
                //Document contians "DOCTYPE"
                if ((get).contains("DOCTYPE")) {

                    String minify = null;
                    if (html4 == "") {
                        row = st.getRow(i);
                        Cell c1 = row.createCell(xcol);
                        c1.setCellValue("N/A");
                        continue;
                    }
                    if (html4.contains("<!DOCTYPE HTML")) {
                        minify = "Pass";
                    } else {
                        minify = "Fail";

                    }
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(minify);

                }

                //2 
                //if html contains gzip or br compressed formet then true
                if ((get).contains("Compress HTML")) {

                    String compress = htmlEncoding;
                    row = st.getRow(i);

                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(compress);

                }

                //3
                //if html contains UTF-8 then true
                if ((get).contains("Character set needs to be specified in HTML calls. Specifying a character set in the HTTP response headers of your HTML documents allows the browser to begin parsing HTML and executing scripts immediately.")) {

                    String characterSet = htmlcharacterSet;
                    row = st.getRow(i);
                    
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(characterSet);

                }

                //4
                //Check No of js call with jsCount(from input file).
                //Check the name of js with jsName(from input file)
                if ((get).contains("Minimize the number of HTTP requests for Javascript")) {

                    String httpReq = null;
                    if (js.size() == 0) {
                        row = st.getRow(i);
                        Cell c1 = row.createCell(xcol);
                        c1.setCellValue("N/A");
                        continue;
                    }
                    try {
                        httpReq = test.MinNo_HTTP_JsReq(js);
                    } catch (IOException ex) {
                        OutputWriter.appendToFile(ex);
                        ex.printStackTrace();
                    }
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(httpReq);

                }

                //5
                if ((get).contains("Minify Javascript")) {
                    String minify = null;
                    if (js.size() == 0) {
                        row = st.getRow(i);
                        Cell c1 = row.createCell(xcol);
                        c1.setCellValue("N/A");
                        continue;
                    }
                    minify = test.Minify(js4);

                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(minify);

                }

                //6
                if ((get).contains("Compress Javascript")) {
                    String compressJs = jsCompress;
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(compressJs);

                }

                //8
                if ((get).contains("Javascript should utilize “Max Age” in response headers and utilize versioned paths")) {
                    String maxAge = jscache;
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(maxAge);

                }

                //10
                if ((get).contains("Minimize the filesize")) {
                    String minSizeJs = null;
                    if (html1.isEmpty() && api1.isEmpty() && img1.isEmpty() && js1.isEmpty() && css1.isEmpty() && font1.isEmpty()) {
                        row = st.getRow(i);
                        Cell c1 = row.createCell(xcol);
                        c1.setCellValue("N/A");
                        continue;

                    }
                    try {
                        minSizeJs = test.size(html1, api1, js1, css1, font1);
                    } catch (IOException ex) {
                        OutputWriter.appendToFile(ex);
                        ex.printStackTrace();
                    }
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(minSizeJs);

                }
                //11
                if ((get).contains("Minimize the number of HTTP requests for CSS")) {
                    String age = null;
                    if (css.size() == 0) {
                        row = st.getRow(i);
                        Cell c1 = row.createCell(xcol);
                        c1.setCellValue("N/A");
                        continue;
                    }
                    try {
                        age = test.MinNo_HTTP_CssReq(css);
                    } catch (IOException ex) {
                        OutputWriter.appendToFile(ex);
                        ex.printStackTrace();
                    }
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(age);

                }

                //12
                if ((get).contains("Minify CSS")) {
                    String MinifyCss = null;
                    if (css4.size() == 0) {
                        row = st.getRow(i);
                        Cell c1 = row.createCell(xcol);
                        c1.setCellValue("N/A");
                        continue;
                    }

                    MinifyCss = test.Minify1(css4);
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(MinifyCss);

                }

                //13
                if ((get).contains("Compress CSS")) {
                    String compressCss = cssCompress;
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(compressCss);

                }

                //15
                if ((get).contains("CSS @import in an external stylesheet needs to be avoided")) {
                    String importAvai = null;
                    if (css4.size() == 0) {
                        row = st.getRow(i);
                        Cell c1 = row.createCell(xcol);
                        c1.setCellValue("N/A");
                        continue;
                    }
                    try {
                        importAvai = test.Avoid_Import(css4);
                    } catch (IOException ex) {
                        OutputWriter.appendToFile(ex);
                        ex.printStackTrace();
                    }
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(importAvai);

                }
                //16
                if ((get).contains("Maximizing parallelization")) {
                    String max_parall = "N/A";
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(max_parall);

                }
                //17
                if ((get).contains("DNS Prefetching")) {

                    String dns_Prefet = null;
                    if (html == "") {
                        row = st.getRow(i);
                        Cell c1 = row.createCell(xcol);
                        c1.setCellValue("N/A");
                        continue;
                    }
                    try {

                        dns_Prefet = test.dns(html4, html, api, font, css, js, img);
                    } catch (IOException ex) {
                        OutputWriter.appendToFile(ex);
                        ex.printStackTrace();
                    }
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(dns_Prefet);

                }

                //18 All Domain DNS Prefetching
                if ((get).contains("All Domain Prefetching")) {

                    String dns_Prefet = null;
                    if (html == "") {
                        row = st.getRow(i);
                        Cell c1 = row.createCell(xcol);
                        c1.setCellValue("N/A");
                        continue;
                    }

                    try {
                        dns_Prefet = test.alldns(html4, html, all);
                    } catch (IOException ex) {
                        OutputWriter.appendToFile(ex);
                        ex.printStackTrace();
                    } catch (URISyntaxException ex) {
                        OutputWriter.appendToFile(ex);
                        ex.printStackTrace();
                    }
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(dns_Prefet);

                }
                //20
                if ((get).contains("Optimize Image Size")) {
                    String op_quality = null;
                    if (img1.size() == 0) {
                        row = st.getRow(i);
                        Cell c1 = row.createCell(xcol);
                        c1.setCellValue("N/A");
                        continue;
                    }
                    try {
                        op_quality = test.size(img1);
                    } catch (IOException ex) {
                        OutputWriter.appendToFile(ex);
                        ex.printStackTrace();
                    }
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(op_quality);

                }
                //21
                if ((get).contains("Optimize Image Resolution")) {
                    String op_Resol = "N/A";
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(op_Resol);

                }
                //22
                if ((get).contains("Utilize CDN for Asserts")) {
//                    String cdnImage = null;
//                    if (font.isEmpty()&& css.isEmpty()&& js.isEmpty()&& img.isEmpty()) {
//                        row = st.getRow(i);
//                        Cell c1 = row.createCell(xcol);
//                        c1.setCellValue("N/A");
//                        continue;
//                    }
//                    try {
//                        cdnImage = test.cdn(img);
//                    } catch (URISyntaxException | FileNotFoundException | UnsupportedEncodingException ex) {
//                        OutputWriter.appendToFile(ex);
//                        ex.printStackTrace();
//                    }
//                    row = st.getRow(i);
//                    Cell c1 = row.createCell(xcol);
//                    c1.setCellValue(cdnImage);
                    String cdn = cdnAssert;
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(cdn);
                }
                //23
                if ((get).contains("Set Proper Content Expiration")) {
                    String content_Expir = imgAge;

                    row = st.getRow(i);

                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(content_Expir);

                }
//            //24
//            if ((get).contains("Utilization of Sprites")) {
//                String util_Sprites = "N/A";
//
//                row = st.getRow(i);
//
//                Cell c1 = row.createCell(xcol);
//                c1.setCellValue(util_Sprites);
//
//            }
//            //25
//            if ((get).contains("Minimize the amount of “empty space” in a sprited image")) {
//
//                String min_EmptySpace = test.sprite_opti(img);
//                row = st.getRow(i);
//                Cell c1 = row.createCell(xcol);
//                c1.setCellValue(min_EmptySpace);
//
//            }
                //30
                if ((get).contains("Transparency")) {
                    String transparency = "N/A";
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(transparency);

                }
                //31
                if ((get).contains("Avoid document.write()")) {
                    String doc = null;
                    if (css4.isEmpty()) {
                        row = st.getRow(i);
                        Cell c1 = row.createCell(xcol);
                        c1.setCellValue("N/A");
                        continue;
                    }
                    try {
                        doc = test.Avoid_DocumentWrite(css4);
                    } catch (IOException ex) {
                        OutputWriter.appendToFile(ex);
                        ex.printStackTrace();
                    }
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(doc);

                }

                //32
                if ((get).contains("Avoid Costly CSS Expressions")) {
                    String costly = null;
                    if (css4.isEmpty()) {
                        row = st.getRow(i);
                        Cell c1 = row.createCell(xcol);
                        c1.setCellValue("N/A");
                        continue;
                    }
                    try {
                        costly = test.Avoid_CostlyExpressions(css4);
                    } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                        OutputWriter.appendToFile(ex);
                        ex.printStackTrace();
                    }
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(costly);

                }
                //33
                if ((get).contains("Ensure color / gradient is used for background and images are avoided")) {
                    String col_Grad = "N/A";
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(col_Grad);

                }
                //34
                if ((get).contains("Ensure sprite images is used for multi-state buttons")) {
                    String multistate_but = "N/A";
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(multistate_but);

                }
                //35
                if ((get).contains("Use html text instead of images for text                                                                                                \n"
                        + "Utilize webfonts whenever possible to achieve specific styling")) {
                    String webfonts = "N/A";
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(webfonts);

                }
                //36
                if ((get).contains("Ensure server cache is enabled for fonts")) {
                    String fontCache = fontAge;
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(fontCache);

                }
                //37
                if ((get).contains("Ensure Etags are configured correctly")) {
                    String etags = etag;
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(etags);

                }
                //38
                if ((get).contains("Reduce the number of web service requests, particularly repetitive requests for static data")) {
                    String webservice_Req = null;
                    if (api.isEmpty()) {
                        row = st.getRow(i);
                        Cell c1 = row.createCell(xcol);
                        c1.setCellValue("N/A");
                        continue;
                    }
                    try {
                        webservice_Req = test.reduceNo(api);
                        // String webservice_Req = null;
                    } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                        OutputWriter.appendToFile(ex);
                        ex.printStackTrace();
                    }
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(webservice_Req);

                }
                //39
                if ((get).contains("Cache data locally to eliminate unneccesary")) {
                    String localCache = null;
                    localCache = apicache;
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(localCache);

                }
                //40
                if ((get).contains("Ensure Connection:keep-alive header is included in server calls")) {
                    String conn_Servercalls = ConnectionAlive;
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(conn_Servercalls);

                }
                //41
                if ((get).contains("Compress web service responses")) {
                    String Compress_webservice = apiCompress;
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(Compress_webservice);

                }
                //42
                if ((get).contains("Duplicate web service calls should be eliminated")) {
                    //  String Dup_webservice = test.duplicate(api);
                    if (api4.isEmpty()) {
                        row = st.getRow(i);
                        Cell c1 = row.createCell(xcol);
                        c1.setCellValue("N/A");
                        continue;
                    }
                    String Dup_webservice = test.duplicate(api4);
                    row = st.getRow(i);

                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(Dup_webservice);

                }
                //43
                if ((get).contains("Marketing/Pixel tracking calls should be triggered asynchronously after content is loaded")) {
                    String trackingcalls = "N/A";
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(trackingcalls);

                }
                //44
                if ((get).contains("Ensure calls required for page rendering are loaded first in parallel")) {
                    String pagerenderLoad = "N/A";
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(pagerenderLoad);

                }
                //46
                if ((get).contains("Avoid HTTP Redirects")) {
                    String httpRedir = null;
                    httpRedir = respond;
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(httpRedir);

                }
                //47
                if ((get).contains("Ensure Memcache is implemented for all custom pages (Home page, Category page, product list page, search page).")) {
                    String Memcache = "N/A";
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(Memcache);

                }
                //48
                if ((get).contains("Ensure Error / Warning log is enabled in server (Debug & Network logs should be disabled)")) {
                    String Warninglog = "N/A";
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(Warninglog);

                }
                //49
                if ((get).contains("Minimise the number of domains used to load Skava calls")) {
                    String minDomain = null;
                    try {
                        minDomain = test.noDomain(api, css, js);
                    } catch (FileNotFoundException ex) {
                        OutputWriter.appendToFile(ex);
                        ex.printStackTrace();
                    } catch (UnsupportedEncodingException ex) {
                        OutputWriter.appendToFile(ex);
                        ex.printStackTrace();
                    }
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(minDomain);

                }
                //50
                if ((get).contains("Unwanted asset files should not be loaded")) {
                    String UnwantedAsset = "N/A";
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(UnwantedAsset);

                }
                //51
                if ((get).contains("Incorporate Lazy Loading of Images and content where applicable")) {
                    String lazy = "N/A";
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(lazy);

                }

                //52
                if ((get).contains("Use pagination for Product list more than 12 products")) {
                    String pagination = "N/A";
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(pagination);

                }
                //53
                if ((get).contains("Header & Footer needs to be loaded first instead of waiting for other contents")) {
                    String load = "N/A";
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(load);

                }//54
                if ((get).contains("Page should not be masked until page loading is completed, assets should be displayed as they are loaded")) {
                    String mask = "N/A";
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(mask);

                }//55
                if ((get).contains("Ensure the contents in hidden Tab are loaded only after the tab is selected (For Ex: Recommended products which are displayed in a tab which is not selected should be loaded only after the tab is selected)")) {
                    String hidden = "N/A";
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(hidden);

                }
                //56
                if ((get).contains("Ensure All the calls from our front end to our micro service API must made through stream transform. we must not use the micro services API directly in any of our projects from the front end")) {
                    String microApi = null;
                    if (api.isEmpty()) {
                        row = st.getRow(i);
                        Cell c1 = row.createCell(xcol);
                        c1.setCellValue("N/A");
                        continue;
                    }
                    try {
                        microApi = test.skavaStream(api);
                    } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                        OutputWriter.appendToFile(ex);
                        ex.printStackTrace();
                    }

                    row = st.getRow(i);

                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(microApi);

                }
                //57
                if ((get).contains("Html cache")) {
                    //String microApi = test.Max_AgeHtml(html2);

                    String microApi = htmlcache;
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(microApi);

                }
                //58
                if ((get).contains("CSS cache")) {
                    //String microApi = test.Max_AgeFont(font2);
                    String microApi = csscache;
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(microApi);

                }
                //64
                if ((get).contains("Assert Duplicate")) {

                    //String microApi = test.Max_AgeFont(font2);
                    if (html.isEmpty() && font.isEmpty() && css.isEmpty() && js.isEmpty() && img.isEmpty()) {
                        row = st.getRow(i);
                        Cell c1 = row.createCell(xcol);
                        c1.setCellValue("N/A");
                        continue;
                    }
                    List<String> assertDuplicate = new LinkedList();

                    assertDuplicate.add(html);
                    assertDuplicate.addAll(font);
                    assertDuplicate.addAll(css);
                    assertDuplicate.addAll(js);
                    assertDuplicate.addAll(img);

                    String microApi = test.duplicate1(assertDuplicate);
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(microApi);

                }
                //65Html compress

                //59
                if ((get).contains("Transport_Security")) {
                    // String microApi = test.Transport_Security(html2);

                    String microApi = Transport_Security;
                    row = st.getRow(i);

                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(microApi);

                }
                //60
                if ((get).contains("X_Content_Type")) {
                    //String microApi = test.X_Content_Type(html2);

                    String microApi = X_Content_Type;
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(microApi);

                }
                //61
                if ((get).contains("X_FRAME_OPTIONS")) {
                    // String microApi = test.X_FRAME_OPTIONS(html2);

                    String microApi = X_FRAME_OPTIONS;
                    row = st.getRow(i);

                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(microApi);

                }
                //62
                if ((get).contains("X_Robots_Tag")) {
                    // String microApi = test.X_Robots_Tag(html2);

                    String microApi = X_Robots_Tag;
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(microApi);

                }
                //63
                if ((get).contains("X_XSS_Protection")) {
                    //  String microApi = test.X_XSS_Protection(html2);

                    String microApi = X_XSS_Protection;
                    row = st.getRow(i);
                    Cell c1 = row.createCell(xcol);
                    c1.setCellValue(microApi);

                }
            } catch (URISyntaxException | NamingException ex) {
                OutputWriter.appendToFile(ex);
                ex.printStackTrace();
            }
        }
    }

    public void run() throws IOException, URISyntaxException {
        try {
            Iterator<String> ii = harlList.iterator();

            try {
                xcol = 4;

            } catch (Exception ex) {
                OutputWriter.appendToFile(ex);
                ex.printStackTrace();
                xcol = 4;

            }
            Set<String> set = new HashSet<String>();
            while (ii.hasNext()) {
                try {
                    int num = 0;
                    filePath = ii.next();

                   
                    if ("".equals(filePath)) {
                        continue;
                    }
                    // System.out.println("@@@@@1@@@@@");
                    String line = filePath;
                    String pattern = "^(.*[\\\\\\/])?(.*?)(\\.[^.]*?|)$";

                    Pattern r = Pattern.compile(pattern);

                    Matcher m = r.matcher(line);

                    if (m.find()) {
                        //System.out.println("Found value: " + m.group(2));
                        fileTag = m.group(2);

                    } else {
                       
                    }

                    write.LoadingView(fileTag + " Running test", "In Process");
                    OutputWriter.appendHarProblem("\n");
                    OutputWriter.appendHarProblem("\nURL :" + filePath + "\tPageName :" + fileTag + "\n\n");
                    OutputWriter.appendHarProblem("\n");
                     String domain3=null;
                    img.clear();
                    img1.clear();
                    img2.clear();
                    font.clear();
                    font1.clear();
                    font2.clear();
                    js.clear();
                    js1.clear();
                    js2.clear();
                    css.clear();
                    css1.clear();
                    css2.clear();
                    api.clear();
                    api1.clear();
                    api2.clear();
                    api4.clear();
                    html4 = "";
                    String findingHtml = "";
                    css4.clear();
                    js4.clear();
                    html = "";
                    html1.clear();
//                html2.clear();
                    all.clear();
                    set.clear();
                    htmlEncoding = "Pass";
                    htmlcharacterSet = "Pass";

                    jsCompress = "Pass";
                    jscache = "Pass";
                    cssCompress = "Pass";
                    csscache = "Pass";
                    etag = "Pass";
                    cdnAssert = "Pass";
                    imgAge = "Pass";
                    fontAge = "Pass";
                    ConnectionAlive = "Pass";

                    apiCompress = "Pass";
                    apicache = "Pass";

                    htmlcache = "Pass";
                    fontcache = "Pass";
                    X_Content_Type = "Pass";
                    Transport_Security = "Pass";
                    X_FRAME_OPTIONS = "Pass";
                    X_Robots_Tag = "Pass";
                    X_XSS_Protection = "Pass";
                    IssuehtmlEncoding = "";
                    IssuehtmlcharacterSet = "";

                    IssuejsCompress = "";
                    Issuejscache = "";
                    Issuecsscache = "";
                    IssuecssCompress = "";
                    Issueetag = "";
                    IssuecdnAssert = "";
                    Issuerespond = "";
                    IssueimgAge = "";
                    IssuefontAge = "";
                    IssueConnectionAlive = "";
                    IssueapiCompress = "";
                    Issueapicache = "";

                    Issuehtmlcache = "";

                    IssueX_Content_Type = "";
                    IssueTransport_Security = "";
                    IssueX_FRAME_OPTIONS = "";
                    IssueX_Robots_Tag = "";
                    IssueX_XSS_Protection = "";
                    String bf = readAllBytesJava7(filePath);
                    byte[] bytes = bf.getBytes();
                    
                    InputStream inputStream = new ByteArrayInputStream(bytes);

                    JsonObject empObj = null;

                    try {
                        try (
                                JsonReader reader = Json.createReader(inputStream)) {

                            empObj = reader.readObject();
                        }
                    } catch (Exception ex) {
                        OutputWriter.appendToFile(ex);
                        ex.printStackTrace();
                    }

                    JsonObject addrObj = empObj.getJsonObject("log");
                    ////
                    String page = "";
                    try {
                        JsonArray arrObjpages = addrObj.getJsonArray("pages");
                        JsonObject addrObjpg = arrObjpages.getJsonObject(0);
                        page = addrObjpg.getString("title");
                    } catch (Exception ex) {
                        System.out.println("no title");
                    }
                    ///
                    JsonArray arrObj = addrObj.getJsonArray("entries");

                    loadTxt += 0.2;
                    loadBar += 0.2;

                    for (int i = 0; i < arrObj.size(); i++) {

                        JsonObject addrObj4 = null;
                        addrObj4 = arrObj.getJsonObject(i);
                        JsonObject addrObj8 = addrObj4.getJsonObject("request");
                        String type = addrObj4.getString("_resourceType");
                        String calls = addrObj8.getString("url");
                        System.out.println("^^^^^^^^^^^^^^^^^^^^");
                        System.out.println(calls);
                        System.out.println(type);
                        System.out.println("VVVVVVVVVVVVVVVVVVV");

                        JsonObject addrObj5 = addrObj4.getJsonObject("response");

                        int tranfer = addrObj5.getInt("_transferSize");
                        int rescode = addrObj5.getInt("status");
                        String protocol = addrObj5.getString("httpVersion");

                        JsonArray arrObj6 = addrObj5.getJsonArray("headers");
                        JsonObject rescontent = addrObj5.getJsonObject("content");
                        String responds = "";
                        try {
                            responds = rescontent.getString("text");
                        } catch (Exception ex) {
                            // OutputWriter.appendToFile(ex);
                            //ex.printStackTrace();
                        }
                        try {
                            URI uri1 = null;
                            if (calls.contains("?")) {
                                String call[] = calls.split("\\?");
                                uri1 = new URI(call[0]);
                            } else {
                                uri1 = new URI(calls);
                            }

                            String domainall = uri1.getHost();

                            if (set.add(domainall) == false) {
                                //System.out.println("dup");
                            } else {
                                all.put(calls, type);
                            }
                        } catch (Exception ex) {
//                        OutputWriter.appendToFile(ex);
//                        ex.printStackTrace();

                        }
                       
                        if (page == "") {
                                                          
                                //System.out.println("host" + Host);
                                
                                    JsonArray arrObjhtml = addrObj8.getJsonArray("headers");
                                   // System.out.println("1header" + arrObjhtml);
                                    for (int ihtml = 0; ihtml < arrObjhtml.size(); ihtml++) {
                                        JsonObject addrObjhtml = null;
                                        addrObjhtml = arrObjhtml.getJsonObject(ihtml);
                                        //System.out.println("add"+addrObjhtml);
                                        try {
//                                            System.out.println("name.." + addrObjhtml.getString("name"));
//                                            System.out.println("val.." + addrObjhtml.getString("value"));

                                            if (addrObjhtml.getString("name").equalsIgnoreCase("referer")) {
                                                System.out.println("found");
                                                findingHtml = addrObjhtml.getString("value");
                                            }

                                            //html=findingHtml;
                                            //System.out.println("html"+html);
                                           // System.out.println("%%%%%%%%%%%%%%%%%%%%%%");
                                        } catch (Exception e) {
                                        }
                                    }
                               // System.out.println("findingHtml"+findingHtml);
                                URL urllhtml = null;
                                urllhtml = new URL(findingHtml);
                                String domain2 = urllhtml.getHost();
                                domain3=domain2;
                                page = domain2.startsWith("www.") ? domain2.substring(4) : domain2;
                                //System.out.println("page "+page);
                            
                        }
                        URI uri = null;

                        uri = new URI(page);
                         String domain1=null;
                         domain1= uri.getHost();
                        if(domain1==null){
                        domain1=domain3;
                        } 
                        domain = domain1.startsWith("www.") ? domain1.substring(4) : domain1;
                        //if (type.toLowerCase().contains("document".toLowerCase()) && calls.contains(page)) {
                        String callsDomain="";
                        if(type.toLowerCase().contains("document".toLowerCase()) ){
                            try{
//                       
                        URI uricalls = new URI(calls);
                        
                        String domain1calls= uricalls.getHost();
                        
                       callsDomain= domain1calls.startsWith("www.") ? domain1calls.substring(4) : domain1calls;
//                        System.out.println("callsDomain"+callsDomain);
//                        System.out.println("??????????????????????????????");
                            } catch (Exception ex) {}}
//                        System.out.println("@@@@@@@&&&&&&");
//                        System.out.println(type.toLowerCase().contains("document".toLowerCase()));
//                        System.out.println("callsDomain"+callsDomain);
//                        System.out.println("domain"+domain);
//                        System.out.println(callsDomain.equalsIgnoreCase(domain));
//                        System.out.println("@@@@@@@&&&&&&");
                        
                        if (type.toLowerCase().contains("document".toLowerCase()) && callsDomain.equalsIgnoreCase(domain)) {
                            System.out.println("html add");
                            html = calls;
                            URL url_Object = new URL(html);
                            URLConnection urlConnection_Obj = url_Object.openConnection();
                            Map<String, List<String>> map = urlConnection_Obj.getHeaderFields();
                            html1.add(calls + ",CCTV," + tranfer);
                            //  html2.put(calls, map);
                            //                     CustomObject1 o1 = new CustomObject1(calls, map);
                            //                     html2.add(o1);

                            html4 = responds;

                            int avai = 0;
                            int ava = 0;
                            int avail = 0;
                            int availa = 0;
                            int availab = 0;
                            int availabl = 0;
                            int available = 0;
                            int available1 = 0;
                            int available2 = 0;
                            int available3 = 0;
                            // int a1 = 0;
                            int a2 = 0;
                            int a1vai = 0;
                            int a1va = 0;
                            int a1vail = 0;
                            int a1vaila = 0;
                            int a1vailab = 0;
                            int a1vailabl = 0;
                            int a1vailable = 0;
                            int a1vailable1 = 0;
                            int a1vailable2 = 0;
                            int a1vailable3 = 0;
                            int a11 = 0;
                            int a12 = 0;
                            for (int j = 0; j < arrObj6.size(); j++) {
                                JsonObject addrObj7 = null;
                                addrObj7 = arrObj6.getJsonObject(j);
                                String name = addrObj7.getString("name").toLowerCase();
                                String val;
                                val = addrObj7.getString("value").toLowerCase();
                                
                                if (name.toLowerCase().contains("etag".toLowerCase())) {
                                    System.out.println("etag"+val);
                                    available2++;
                                    if (val.equals("")) {

//                                    etag = "Fail";
//                                    Issueetag = Issueetag + "\nFail-" + name + "->" + val + "->" + calls;
                                    } else {
                                        a1vailable2++;

                                    }
                                }
                                if (rescode >= 200 && rescode < 300) {
                                    a2++;
                                }
                                if (protocol.equals("http/2.0".toLowerCase())) {
                                    available3++;
                                    a1vailable3++;
                                } else if (name.toLowerCase().contains("Connection".toLowerCase())) {
                                    available3++;
                                    if (val.toLowerCase().equals("keep-alive".toLowerCase())) {
                                        a1vailable3++;
                                        //  System.out.println("connection@@@");
                                    } else {

//                                    IssueConnectionAlive = IssueConnectionAlive + "\nFail-" + name + "->" + val + "->" + calls;
//                                    ConnectionAlive = "Fail";
                                    }
                                }
                                if (name.toLowerCase().contains("Strict-Transport-Security".toLowerCase())) {
                                    available1++;
                                    if (val.contains("max-age=")) {

                                        String cacheNum = "";
                                        String pattern1 = "max-age=([0-9]+)";

                                        Pattern r1 = Pattern.compile(pattern1);

                                        Matcher m1 = r1.matcher(val);

                                        if (m1.find()) {
                                            cacheNum = m1.group(1);

                                        } else {

                                        }
                                        if (Integer.parseInt(cacheNum) >= Integer.parseInt(transport_Security)) {
                                            a1vailable1++;
                                        } else {

//                                        IssueTransport_Security = IssueTransport_Security + "\n" + name + " " + val;
//
//                                        Transport_Security = "Fail";
                                        }
                                    } else {
//                                    Transport_Security = "Fail";
//                                    IssueTransport_Security = IssueTransport_Security + "\n " + name + " " + val;
                                    }
                                }
                                if (name.toLowerCase().contains("content-encoding".toLowerCase())) {
                                    ava++;
                                    if (val.toLowerCase().contains("gzip".toLowerCase()) || val.toLowerCase().contains("br".toLowerCase())) {
                                        a1va++;
                                    } else {
//                                    htmlEncoding = "Fail";
//                                    IssuehtmlEncoding = IssuehtmlEncoding + "\n " + name + " " + val;
                                    }
                                }
                                if (name.toLowerCase().contains("X-Content-Type".toLowerCase())) {
                                    availa++;
                                    if (val.toLowerCase().contains("nosniff".toLowerCase())) {
                                        a1vaila++;
                                    } else {

//                                    IssueX_Content_Type = IssueX_Content_Type + "\n " + name + " " + val;
//                                    X_Content_Type = "Fail";
                                    }
                                }
                                if (name.toLowerCase().contains("X-Frame-Options".toLowerCase())) {
                                    availab++;
                                    if (val.toLowerCase().contains("SAMEORIGIN".toLowerCase())) {
                                        a1vailab++;
                                    } else {

//                                    IssueX_FRAME_OPTIONS = IssueX_FRAME_OPTIONS + "\n " + name + " " + val;
//                                    X_FRAME_OPTIONS = "Fail";
                                    }
                                }
                                if (name.toLowerCase().contains("X-Robots-Tag".toLowerCase())) {
                                    availabl++;

                                    if (val.toLowerCase().contains("noindex, nofollow".toLowerCase())) {
                                        a1vailabl++;
                                    } else {

//                                    IssueX_Robots_Tag = IssueX_Robots_Tag + "\n " + name + " " + val;
//                                    X_Robots_Tag = "Fail";
                                    }
                                }
                                if (name.toLowerCase().contains("X-XSS-Protection".toLowerCase())) {
                                    available++;
                                    if (val.toLowerCase().contains("1; mode=block".toLowerCase())) {
                                        a1vailable++;
                                    } else {

//                                    IssueX_XSS_Protection = IssueX_XSS_Protection + "\n " + name + " " + val;
//                                    X_XSS_Protection = "Fail";
                                    }
                                }

                                if (name.toLowerCase().contains("Content-Type".toLowerCase()) && !(name.toLowerCase().contains("X".toLowerCase()))) {
                                    avai++;
                                    if (val.toLowerCase().contains("UTF-8".toLowerCase())) {
                                        a1vai++;
                                    } else {
//                                    IssuehtmlcharacterSet = IssuehtmlcharacterSet + "\n " + name + " " + val;
//                                    htmlcharacterSet = "Fail";

                                    }
                                }
                                ///

                                if (name.toLowerCase().contains("cache-control".toLowerCase())) {
                                    System.out.println("cache"+val);
                                    avail++;
                                    if (val.contains("max-age=") && !val.contains("no-cache") && val.contains("must-revalidate")) {
                                        Issuehtmlcache = Issuehtmlcache + "\n must revalidate" + name + " " + val + "call :" + calls;
                                        htmlcache = "Fail";
                                    } else if (val.contains("max-age=") && !val.contains("no-cache")) {
                                        String cacheNum = "";
                                        String pattern1 = "max-age=([0-9]+)";
                                        Pattern r1 = Pattern.compile(pattern1);
                                        Matcher m1 = r1.matcher(val);
                                        if (m1.find()) {
                                            cacheNum = m1.group(1);

                                        } else {
                                        }
                                        if (Integer.parseInt(cacheNum) >= Integer.parseInt(htmlCacheio)) {
                                            a1vail++;
                                        } else {

//                                        Issuehtmlcache = Issuehtmlcache + "\n " + name + " " + val;
//                                        htmlcache = "Fail";
                                        }
                                    } else {
//                                    Issuehtmlcache = Issuehtmlcache + "\n " + name + " " + val;
//                                    htmlcache = "Fail";
                                    }
                                }

                            }
//System.out.println("@@@@@7@@@@@");
                            // System.out.println("gzip: " + ava + "UTF-8: " + avai + " " + ava + "htmlcache: " + avail + "X-Content-Type: " + availa + "X-Frame-Options: " + availab + "X-Robots-Tag: " + availabl + "X-XSS-Protection: " + available + "Strict-Transport-Security: " + available1 + "etag: " + available2 + "Connection: " + available3 + "respond" + a2);
                            System.out.println("*************************************************");
                            System.out.println("etag"+available2+" "+a1vailable2);
                            if (available2 == 0 || a1vailable2 == 0) {

                                Issueetag = Issueetag + "\n" + calls;
                                etag = "Fail";

                            }
                            if (available3 == 0 || a1vailable3 == 0) {

                                IssueConnectionAlive = IssueConnectionAlive + "\n" + calls;
                                ConnectionAlive = "Fail";

                            }
                            if (available1 == 0 || a1vailable1 == 0) {
                                Transport_Security = "Fail";
                                IssueTransport_Security = IssueTransport_Security + "\n" + calls;

                            }
                            if (available == 0 || a1vailable == 0) {
                                X_XSS_Protection = "Fail";
                                IssueX_XSS_Protection = IssueX_XSS_Protection + "\n" + calls;

                            }
                            if (availabl == 0 || a1vailabl == 0) {
                                X_Robots_Tag = "Fail";
                                IssueX_Robots_Tag = IssueX_Robots_Tag + "\n" + calls;

                            }
                            if (availab == 0 || a1vailab == 0) {
                                X_FRAME_OPTIONS = "Fail";
                                IssueX_FRAME_OPTIONS = IssueX_FRAME_OPTIONS + "\n" + calls;

                            }
                            if (avai == 0 || a1vai == 0) {
                                htmlcharacterSet = "Fail";
                                IssuehtmlcharacterSet = IssuehtmlcharacterSet + "\n" + calls;

                            }

                            if (availa == 0 || a1vaila == 0) {
                                X_Content_Type = "Fail";
                                IssueX_Content_Type = IssueX_Content_Type + "\n" + calls;

                            }
                            System.out.println("etag"+avail+" "+a1vail);
                            if (avail == 0 || a1vail == 0) {
                                htmlcache = "Fail";
                                Issuehtmlcache = Issuehtmlcache + "\n" + calls;

                            }
                            if (ava == 0 || a1va == 0) {
                                IssuehtmlEncoding = IssuehtmlEncoding + "\n" + calls;
                                htmlEncoding = "Fail";
                            }
                        }
//System.out.println("@@@@@8@@@@@");
                        if (type.toLowerCase().contains("xhr".toLowerCase()) || type.toLowerCase().contains("fetch".toLowerCase())) {
//                        if (calls.contains("skavastream")) {
//                            try {
//                                String a = calls;
//                                String aa[] = a.split("\\?");
//                                calls = aa[0] + "?" + URLEncoder.encode(aa[1], "UTF-8");
//                                URI uri = null;
//                                uri = new URI(calls);
//                                String domain1 = uri.getHost();
//                                domain = domain1.startsWith("www.") ? domain1.substring(4) : domain1;
//                            } catch (Exception ex) {
//                                OutputWriter.appendToFile(ex);
//                            }
//
//                        }
                            int avai = 0;
                            int a1vai = 0;
                            int b = 0;
                            int c = 0;

                            int avail = 0;
                            int availa = 0;
                            int a1vail = 0;
                            int a1vaila = 0;
                            int availablecdn = 0;
                            int availablecdn1 = 0;
                            // int av = 0;
                            for (String dn : Domainio) {
                                // System.out.println("***********domain:" + dn);
                                URL urll = null;
                                urll = new URL(calls);
                                String Host = urll.getHost();
                                //System.out.println("host" + Host);
                                if (Host.toLowerCase().contains(dn.trim().toLowerCase()) || Host.toLowerCase().contains(domain.toLowerCase())) {

                                    c++;
                                    b++;
                                    for (int j = 0; j < arrObj6.size(); j++) {
                                        JsonObject addrObj7 = null;
                                        addrObj7 = arrObj6.getJsonObject(j);
                                        String name = addrObj7.getString("name").toLowerCase();
                                        String val;
                                        val = addrObj7.getString("value").toLowerCase();
//                                    if (name.toLowerCase().contains("etag".toLowerCase())) {
//                                        av = 1;
//                                        if (val.equals("")) {
//
//                                            av = 0;
//                                        } else {
//
//                                        }
//                                    }
                                        if (name.toLowerCase().contains("x-cache".toLowerCase()) || name.toLowerCase().contains("cf-cache-status".toLowerCase())) {
                                            availablecdn++;
                                            if (val.equals("")) {

                                            } else {
                                                availablecdn1++;

                                            }
                                        }
                                        if (protocol.equals("http/2.0".toLowerCase())) {
                                            a1vai = 1;
                                            avai = 1;
//                                            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
//                                            System.out.println("XHR"+protocol+" "+name+" "+val);
//                                            System.out.println("@@@@@@@@@@@@@@@@@@@@@@@");
                                        } else if (name.toLowerCase().contains("Connection".toLowerCase())) {
                                            avai = 1;
                                            if (val.toLowerCase().equals("keep-alive".toLowerCase())) {
                                                a1vai = 1;
                                            } else {

                                            }
                                        }

                                        if (name.toLowerCase().contains("content-encoding".toLowerCase())) {
                                            avail = 1;
                                            if (val.toLowerCase().contains("gzip".toLowerCase()) || val.toLowerCase().contains("br".toLowerCase())) {
                                                a1vail = 1;
                                            } else {

                                            }
                                        }

                                        if (name.toLowerCase().contains("cache-control".toLowerCase())) {
                                            availa = 1;
                                            if (calls.contains("xact")) {
                                                if (val.toLowerCase().contains("no-cache".toLowerCase())) {
                                                    a1vaila++;
                                                } else {

                                                }
                                            } else {
                                                if (val.contains("max-age=") && !val.contains("no-cache") && val.contains("must-revalidate")) {

                                                } else if (val.contains("max-age=") && !val.contains("no-cache")) {

                                                    String cacheNum = "";
                                                    String pattern1 = "max-age=([0-9]+)";

                                                    Pattern r1 = Pattern.compile(pattern1);

                                                    Matcher m1 = r1.matcher(val);

                                                    if (m1.find()) {

                                                        // System.out.println("Found value: " + m1.group(1));
                                                        cacheNum = m1.group(1);

                                                    } else {
                                                    }
                                                    if (Integer.parseInt(cacheNum) >= Integer.parseInt(apiCacheio)) {
                                                        a1vaila++;
                                                    } else {

                                                    }
                                                } else {

                                                }
                                            }

                                        }
                                    }
                                }
                            }
                            if (c != 0) {
                                // System.out.println("add");
                                api.add(calls);
                                if ("".equals(responds)) {
                                    api4.add(calls);
                                } else {
                                    api4.add(calls + "," + responds);
                                }
                                api1.add(calls + ",CCTV," + tranfer);
                                URL url_Object = null;
                                url_Object = new URL(calls);
                                URLConnection urlConnection_Obj = null;
                                urlConnection_Obj = url_Object.openConnection();
                                Map<String, List<String>> map = urlConnection_Obj.getHeaderFields();
                                // api2.put(calls, map);
                                CustomObject1 o1 = new CustomObject1(calls, map);
                                api2.add(o1);
                                if (rescode >= 200 && rescode < 300) {

                                } else {
                                    respond = "Fail";
                                    Issuerespond = Issuerespond + "\nFail-" + rescode + "->" + calls;

                                }
                            }
                            if (b != 0) {
                                //  System.out.println("connection: " + avai + "gzip: " + avail + "cache: " + availa);
//                            if (av == 0) {
//                                Issueetag = Issueetag + "\n" + calls;
//                                etag = "Fail";
//                            }
                                if (avai == 0 || a1vai == 0) {
                                    IssueConnectionAlive = IssueConnectionAlive + "\n" + calls;
                                    ConnectionAlive = "Fail";
                                }
                                if (availablecdn == 0 || availablecdn1 == 0) {

                                    IssuecdnAssert = IssuecdnAssert + "\n" + calls;
                                    etag = "Fail";

                                }
                                if (availa == 0 || a1vaila == 0) {
                                    Issueapicache = Issueapicache + "\n" + calls;
                                    apicache = "Fail";

                                }
                                if (avail == 0 || a1vail == 0) {
                                    IssueapiCompress = IssueapiCompress + "\n" + calls;
                                    apiCompress = "Fail";
                                }
//System.out.println("@@@@@10@@@@@");
                            }
                        }
                        if (type.toLowerCase().contains("image".toLowerCase())) {
                            int b = 0;
                            int c = 0;
                            int avai = 0;
                            int ava = 0;
                            int a1vai = 0;
                            int a1va = 0;
                            int availablecdn = 0;
                            int availablecdn1 = 0;
                            for (String dn : Domainio) {
                                URL urll = null;
                                urll = new URL(calls);
                                String Host = urll.getHost();
                                if (Host.toLowerCase().contains(dn.trim().toLowerCase()) || Host.toLowerCase().contains(domain.toLowerCase())) {
                                    b++;
                                    c++;
                                    for (int j = 0; j < arrObj6.size(); j++) {
                                        JsonObject addrObj7 = null;
                                        addrObj7 = arrObj6.getJsonObject(j);
                                        String name = addrObj7.getString("name").toLowerCase();
                                        String val;
                                        val = addrObj7.getString("value").toLowerCase();
                                        if (name.toLowerCase().contains("etag".toLowerCase())) {
                                            ava = 1;
                                            if (val.equals("")) {

                                            } else {
                                                a1va++;

                                            }
                                        }

                                        if (name.toLowerCase().contains("x-cache".toLowerCase()) || name.toLowerCase().contains("cf-cache-status".toLowerCase())) {
                                            availablecdn++;
                                            if (val.equals("")) {

                                            } else {
                                                availablecdn1++;

                                            }
                                        }
                                        if (name.toLowerCase().contains("cache-control".toLowerCase())) {
                                            avai = 1;
                                            if (val.toLowerCase().contains("no-cache".toLowerCase())) {
                                                //avai = 0;
                                            }
                                            if (val.contains("max-age=") && !val.contains("no-cache") && val.contains("must-revalidate")) {
                                                //avai = 0;
                                            } else if (val.contains("max-age=") && !val.contains("no-cache")) {
                                                String cacheNum = "";
                                                String pattern1 = "max-age=([0-9]+)";
                                                Pattern r1 = Pattern.compile(pattern1);
                                                Matcher m1 = r1.matcher(val);
                                                if (m1.find()) {
                                                    cacheNum = m1.group(1);
                                                } else {
                                                }
                                                if (Integer.parseInt(cacheNum) >= Integer.parseInt(ImgCacheio)) {
                                                    a1vai++;
                                                } else {
                                                    // avai = 0;
                                                }
                                            } else {
                                                // avai = 0;
                                            }
                                        }
                                    }
                                }
                            }
                            if (c != 0) {
                                // System.out.println("add");
                                img.add(calls);
                                img1.add(calls + ",CCTV," + tranfer);
                                URL url_Object = null;
                                url_Object = new URL(calls);
                                URLConnection urlConnection_Obj = null;
                                urlConnection_Obj = url_Object.openConnection();
                                Map<String, List<String>> map = urlConnection_Obj.getHeaderFields();
                                //img2.put(calls, map);
                                CustomObject1 o1 = new CustomObject1(calls, map);
                                img2.add(o1);
                                if (rescode >= 200 && rescode < 300) {
                                } else {
                                    respond = "Fail";
                                    Issuerespond = Issuerespond + "\nFail-" + rescode + "->" + calls;
                                }
                            }
                            if (b != 0) {
                                // System.out.println("etag: " + ava + "cache: " + avai);
                                if (ava == 0 || a1va == 0) {

                                    Issueetag = Issueetag + "\n" + calls;
                                    etag = "Fail";

                                }
                                if (avai == 0 || a1vai == 0) {

                                    IssueimgAge = IssueimgAge + "\n" + calls;
                                    imgAge = "Fail";

                                }
                                if (availablecdn == 0 || availablecdn1 == 0) {

                                    IssuecdnAssert = IssuecdnAssert + "\n" + calls;
                                    etag = "Fail";

                                }
                            }
                        }
                        if (type.toLowerCase().contains("script".toLowerCase())) {
                            int avai = 0;
                            int ava = 0;
                            int avail = 0;
                            int available3 = 0;
                            int a1vai = 0;
                            int a1va = 0;
                            int a1vail = 0;
                            int a1vailable3 = 0;
                            int c = 0;
                            int b = 0;
                            int availablecdn = 0;
                            int availablecdn1 = 0;
//System.out.println("@@@@@11@@@@@");
                            for (String dn : Domainio) {

                                URL urll = null;

                                urll = new URL(calls);

                                String Host = urll.getHost();

                                if (Host.toLowerCase().contains(dn.trim().toLowerCase()) || Host.toLowerCase().contains(domain.toLowerCase())) {
                                    b++;
                                    c++;
                                    for (int j = 0; j < arrObj6.size(); j++) {
                                        JsonObject addrObj7 = null;

                                        addrObj7 = arrObj6.getJsonObject(j);

                                        String name = addrObj7.getString("name").toLowerCase();
                                        String val;

                                        val = addrObj7.getString("value").toLowerCase();

                                        if (name.contains("content-encoding")) {
                                            ava = 1;
                                            if (val.contains("gzip") || val.toLowerCase().contains("br".toLowerCase())) {
                                                a1va++;
                                            } else {

                                            }
                                        }

                                        if (name.contains("etag")) {
                                            avai = 1;
                                            if (val.equals("")) {

                                            } else {
                                                a1vai++;
                                            }
                                        }
                                        if (name.toLowerCase().contains("x-cache".toLowerCase()) || name.toLowerCase().contains("cf-cache-status".toLowerCase())) {
                                            availablecdn++;
                                            if (val.equals("")) {

                                            } else {
                                                availablecdn1++;

                                            }
                                        }
                                        // if (calls.toLowerCase().contains("load")) {
                                        if (protocol.equals("http/2.0".toLowerCase())) {
                                            available3 = 1;
                                            a1vailable3++;
                                        } else if (name.toLowerCase().contains("Connection".toLowerCase())) {
                                            available3 = 1;
                                            if (val.toLowerCase().equals("keep-alive".toLowerCase())) {
                                                a1vailable3++;
                                            } else {

                                            }
                                        }

                                        // }
                                        if (name.contains("cache-control")) {
                                            avail = 1;

                                            if (val.contains("max-age=") && !val.contains("no-cache") && val.contains("must-revalidate")) {
                                                System.out.println("1");
                                            } else if (val.contains("max-age=") && !val.contains("no-cache")) {
                                                System.out.println("2");
                                                String cacheNum = "";
                                                String pattern1 = "max-age=([0-9]+)";
                                                Pattern r1 = Pattern.compile(pattern1);

                                                Matcher m1 = r1.matcher(val);

                                                if (m1.find()) {
                                                    cacheNum = m1.group(1);
                                                    System.out.println("cache" + cacheNum);
                                                } else {

                                                }
//                                                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
//                                                System.out.println(" cache=" + cacheNum + " >= " + jsCssFontCacheio);
//                                                System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

                                                if (Integer.parseInt(cacheNum) >= Integer.parseInt(jsCssFontCacheio)) {
                                                    a1vail++;
                                                } else {

                                                }
                                            } else {

                                            }
                                        }
                                    }
                                }
                            }
                            if (c != 0) {
                                // System.out.println("add");
                                js.add(calls);
                                js1.add(calls + ",CCTV," + tranfer);
                                js4.add(calls + ",CCTV," + responds);
                                URL url_Object = null;
                                url_Object = new URL(calls);
                                URLConnection urlConnection_Obj = null;
                                urlConnection_Obj = url_Object.openConnection();
                                Map<String, List<String>> map = urlConnection_Obj.getHeaderFields();
                                // js2.put(calls, map);
                                CustomObject1 o1 = new CustomObject1(calls, map);
                                js2.add(o1);
                                if (rescode >= 200 && rescode < 300) {
                                } else {
                                    respond = "Fail";
                                    Issuerespond = Issuerespond + "\nFail-" + rescode + "->" + calls;
                                }
                            }
                            if (b != 0) {
                                // System.out.println("compress: " + ava + "etag: " + avai + "cache: " + avail + "connection: " + available3);
                                if ((available3 == 0 || a1vailable3 == 0) && calls.toLowerCase().contains("load")) {
                                    IssueConnectionAlive = IssueConnectionAlive + "\n" + calls;
                                    ConnectionAlive = "Fail";
                                }
                                if (ava == 0 || a1va == 0) {
                                    jsCompress = "Fail";
                                    IssuejsCompress = IssuejsCompress + "\n" + calls;
                                }
                                if (avai == 0 || a1vai == 0) {
                                    Issueetag = Issueetag + "\n" + calls;
                                    etag = "Fail";
                                }
                                if (availablecdn == 0 || availablecdn1 == 0) {

                                    IssuecdnAssert = IssuecdnAssert + "\n" + calls;
                                    etag = "Fail";

                                }
                                if (avail == 0 || a1vail == 0) {
                                    Issuejscache = Issuejscache + "\n" + calls;
                                    jscache = "Fail";
                                }
                            }
                        }
                        if (type.toLowerCase().contains("stylesheet".toLowerCase())) {
                            int c = 0;
                            int b = 0;
                            int ava = 0;
                            int avai = 0;
                            int available3 = 0;
                            int avail = 0;
                            int a1va = 0;
                            int a1vai = 0;
                            int a1vailable3 = 0;
                            int a1vail = 0;
                            int availablecdn = 0;
                            int availablecdn1 = 0;
                            for (String dn : Domainio) {
                                URL urll = null;
                                urll = new URL(calls);
                                String Host = urll.getHost();
                                if (Host.toLowerCase().contains(dn.trim().toLowerCase()) || Host.toLowerCase().contains(domain.toLowerCase())) {
                                    c++;
                                    b++;
                                    for (int j = 0; j < arrObj6.size(); j++) {
                                        JsonObject addrObj7 = null;
                                        addrObj7 = arrObj6.getJsonObject(j);
                                        String name = addrObj7.getString("name").toLowerCase();
                                        String val;
                                        val = addrObj7.getString("value").toLowerCase();
                                        if (name.contains("cache-control")) {
                                            avail = 1;

                                            if (val.contains("max-age=") && !val.contains("no-cache") && val.contains("must-revalidate")) {

                                            } else if (val.contains("max-age=") && !val.contains("no-cache")) {

                                                String cacheNum = "";
                                                String pattern1 = "max-age=([0-9]+)";
                                                Pattern r1 = Pattern.compile(pattern1);
                                                Matcher m1 = r1.matcher(val);
                                                if (m1.find()) {
                                                    cacheNum = m1.group(1);
                                                } else {
                                                }
                                                if (Integer.parseInt(cacheNum) >= Integer.parseInt(jsCssFontCacheio)) {
                                                    a1vail++;
                                                } else {

                                                }
                                            } else {

                                            }
                                        }
                                        if (name.contains("content-encoding")) {
                                            ava = 1;
                                            if (val.contains("gzip") || val.toLowerCase().contains("br".toLowerCase())) {
                                                a1va++;
                                            } else {

                                            }
                                        }
                                        if (calls.toLowerCase().contains("load")) {
                                            if (protocol.equals("http/2.0".toLowerCase())) {
                                                available3 = 1;
                                                a1vailable3++;
                                            } else if (name.toLowerCase().contains("Connection".toLowerCase())) {
                                                available3 = 1;
                                                if (val.toLowerCase().equals("keep-alive".toLowerCase())) {
                                                    a1vailable3++;
                                                } else {

                                                }
                                            }
                                        }
                                        if (name.contains("etag")) {
                                            avai = 1;

                                            if (val.equals("")) {

                                            } else {
                                                a1vai++;

                                            }
                                        }
                                        if (name.toLowerCase().contains("x-cache".toLowerCase()) || name.toLowerCase().contains("cf-cache-status".toLowerCase())) {
                                            availablecdn++;
                                            if (val.equals("")) {

                                            } else {
                                                availablecdn1++;

                                            }
                                        }

                                    }
                                }
                            }
                            if (c != 0) {
                                // System.out.println("add");
                                css.add(calls);
                                css1.add(calls + ",CCTV," + tranfer);
                                css4.add(calls + ",CCTV," + responds);
                                URL url_Object = null;

                                url_Object = new URL(calls);

                                URLConnection urlConnection_Obj = null;

                                urlConnection_Obj = url_Object.openConnection();

                                Map<String, List<String>> map = urlConnection_Obj.getHeaderFields();
                                // css2.put(calls, map);
                                CustomObject1 o1 = new CustomObject1(calls, map);
                                css2.add(o1);
                                if (rescode >= 200 && rescode < 300) {

                                } else {
                                    respond = "Fail";
                                    Issuerespond = Issuerespond + "\nFail-" + rescode + "->" + calls;

                                }
                            }
                            if (b != 0) {
                                //  System.out.println("compress: " + ava + "etag: " + avai + "connection: " + available3 + "cache: " + avail);
                                if ((available3 == 0 || a1vailable3 == 0) && calls.toLowerCase().contains("load")) {
                                    IssueConnectionAlive = IssueConnectionAlive + "\n" + calls;
                                    ConnectionAlive = "Fail";
                                }
                                if (availablecdn == 0 || availablecdn1 == 0) {

                                    IssuecdnAssert = IssuecdnAssert + "\n" + calls;
                                    etag = "Fail";

                                }
                                if (avail == 0 || a1vail == 0) {
                                    Issuejscache = Issuejscache + "\n" + calls;
                                    jscache = "Fail";
                                }
                                if (ava == 0 || a1va == 0) {
                                    IssuecssCompress = IssuecssCompress + "\n" + calls;
                                    cssCompress = "Fail";
                                }
                                if (avai == 0 || a1vai == 0) {
                                    Issueetag = Issueetag + "\n" + calls;
                                    etag = "Fail";
                                }
                            }
                        }
                        if (type.toLowerCase().contains("font".toLowerCase())) {
                            int b = 0;
                            int c = 0;
                            int ava = 0;
                            int avai = 0;
                            int available3 = 0;
                            int a1va = 0;
                            int a1vai = 0;
                            int a1vailable3 = 0;
                            int availablecdn = 0;
                            int availablecdn1 = 0;
                            for (String dn : Domainio) {
                                URL urll = null;
                                urll = new URL(calls);
                                String Host = urll.getHost();
                                if (Host.toLowerCase().contains(dn.trim().toLowerCase()) || Host.toLowerCase().contains(domain.toLowerCase())) {
                                    b++;
                                    c++;
                                    for (int j = 0; j < arrObj6.size(); j++) {
                                        JsonObject addrObj7 = null;
                                        addrObj7 = arrObj6.getJsonObject(j);
                                        String name = addrObj7.getString("name").toLowerCase();
                                        String val;
                                        val = addrObj7.getString("value").toLowerCase();
                                        if (name.contains("cache-control")) {
                                            ava = 1;
                                            if (val.contains("max-age=") && !val.contains("no-cache") && val.contains("must-revalidate")) {

                                            } else if (val.contains("max-age=") && !val.contains("no-cache")) {
                                                String cacheNum = "";
                                                String pattern1 = "max-age=([0-9]+)";
                                                Pattern r1 = Pattern.compile(pattern1);
                                                Matcher m1 = r1.matcher(val);
                                                if (m1.find()) {
                                                    cacheNum = m1.group(1);

                                                } else {

                                                }
                                                if (Integer.parseInt(cacheNum) >= Integer.parseInt(jsCssFontCacheio)) {
                                                    a1va++;
                                                } else {

                                                }

                                            } else {

                                            }
                                        }
                                        if (protocol.equals("http/2.0".toLowerCase())) {
                                            available3 = 1;
                                            a1vailable3++;
                                        } else if (name.toLowerCase().contains("Connection".toLowerCase())) {
                                            available3 = 1;
                                            if (val.toLowerCase().equals("keep-alive".toLowerCase())) {
                                                a1vailable3++;
                                            } else {

                                            }
                                        }
                                        if (name.contains("etag")) {
                                            avai = 1;
                                            if (val.equals("")) {

                                            } else {
                                                a1vai++;
                                            }
                                        }
                                        if (name.toLowerCase().contains("x-cache".toLowerCase()) || name.toLowerCase().contains("cf-cache-status".toLowerCase())) {
                                            availablecdn++;
                                            if (val.equals("")) {

                                            } else {
                                                availablecdn1++;

                                            }
                                        }
                                    }
                                }
                            }
                            if (c != 0) {
                                //  System.out.println("add");
                                font.add(calls);
                                font1.add(calls + ",CCTV," + tranfer);
                                URL url_Object = null;
                                url_Object = new URL(calls);
                                URLConnection urlConnection_Obj = null;
                                urlConnection_Obj = url_Object.openConnection();
                                Map<String, List<String>> map = urlConnection_Obj.getHeaderFields();
                                // font2.put(calls, map);
                                CustomObject1 o1 = new CustomObject1(calls, map);
                                font2.add(o1);
                                if (rescode >= 200 && rescode < 300) {

                                } else {
                                    respond = "Fail";
                                    Issuerespond = Issuerespond + "\nFail-" + rescode + "->" + calls;
                                }
                            }
                            if (b != 0) {
                                //  System.out.println("cache: " + ava + "etag: " + avai + "connection: " + available3);
                                if (available3 == 0 || a1vailable3 == 0) {
                                    IssueConnectionAlive = IssueConnectionAlive + "\n" + calls;
                                    ConnectionAlive = "Fail";
                                }
                                if (avai == 0 || a1vai == 0) {
                                    Issueetag = Issueetag + "\n" + calls;
                                    etag = "Fail";
                                }
                                if (ava == 0 || a1va == 0) {
                                    IssuefontAge = IssuefontAge + "\n" + calls;
                                    fontAge = "Fail";
                                }
                                if (availablecdn == 0 || availablecdn1 == 0) {

                                    IssuecdnAssert = IssuecdnAssert + "\n" + calls;
                                    etag = "Fail";

                                }
                            }
                        }
                        
                        if (html == "") {
                            if (findingHtml.contains("")) {
                                System.out.println("1");
                                URL urllhtml = null;
                                urllhtml = new URL(calls);
                                String Hosthtml = urllhtml.getHost();
                                //System.out.println("host" + Host);
                                if (Hosthtml.toLowerCase().contains(domain.toLowerCase()) && !type.toLowerCase().contains("document".toLowerCase())) {

                                    JsonArray arrObjhtml = addrObj8.getJsonArray("headers");
                                    //System.out.println("1header" + arrObjhtml);
                                    for (int ihtml = 0; ihtml < arrObjhtml.size(); ihtml++) {
                                        JsonObject addrObjhtml = null;
                                        addrObjhtml = arrObjhtml.getJsonObject(ihtml);
                                        //System.out.println("add"+addrObjhtml);
                                        try {
//                                            System.out.println("name.." + addrObjhtml.getString("name"));
//                                            System.out.println("val.." + addrObjhtml.getString("value"));

                                            if (addrObjhtml.getString("name").contains("referer")) {
                                                findingHtml = addrObjhtml.getString("value");
                                            }

                                            //html=findingHtml;
                                            //System.out.println("html"+html);
                                            //System.out.println("%%%%%%%%%%%%%%%%%%%%%%");
                                        } catch (Exception e) {
                                        }
                                    }
                                }
                            }

                        }

                    }

                    ////////here
                    loadTxt += 0.2;
                    loadBar += 0.2;
                    System.out.println("************HAR****************");
                     System.out.println("!!!!" + filePath);
                    System.out.println("all" + all);
                    System.out.println("No. of calls"+all.size());
                    // all = all.stream().distinct().collect(Collectors.toList());
                    
                    System.out.println("img" + img);
                    // img = img.stream().distinct().collect(Collectors.toList());
                    System.out.println("img1" + img1);

                    System.out.println("font" + font);
                    // font = font.stream().distinct().collect(Collectors.toList());
                    System.out.println("font1" + font1);

                    System.out.println("js" + js);
                    // js = js.stream().distinct().collect(Collectors.toList());
                    System.out.println("js1" + js1);

                    System.out.println("css" + css);
                    // css = css.stream().distinct().collect(Collectors.toList());
                    System.out.println("css1" + css1);

                    System.out.println("api" + api);
                    // api = api.stream().distinct().collect(Collectors.toList());
                    System.out.println("api1" + api1);
                    if (html.equalsIgnoreCase("")) {
                        if (!findingHtml.equalsIgnoreCase("")) {
                            //System.out.println("#####################yes"+findingHtml);
                            html = findingHtml;
                            URL url = new URL(html);
                            URLConnection con = url.openConnection();
                            InputStream in = con.getInputStream();
                            String encoding = con.getContentEncoding();
                            encoding = encoding == null ? "UTF-8" : encoding;
                            String body = IOUtils.toString(in, encoding);
                            html4 = body;
                        }
                    }

                    System.out.println("html" + html);
                    System.out.println("page" + page);

                    System.out.println("*******************************");
//                    if ("".equals(html)) {
//
//                        html = page;
//                        //////
//                        URL RangeUrl = null;
//                        try {
//                            RangeUrl = new URL("https://www.googleapis.com/pagespeedonline/v5/runPagespeed?url=" + html + "&strategy=mobile");
//                        } catch (MalformedURLException ex) {
//                            OutputWriter.appendToFile(ex);
//                            ex.printStackTrace();
//                        }
//
//                        HttpURLConnection conn = null;
//                        try {
//                            conn = (HttpURLConnection) RangeUrl.openConnection();
//                        } catch (IOException ex) {
//                            OutputWriter.appendToFile(ex);
//                            ex.printStackTrace();
//                        }
//                        try {
//                            conn.setRequestMethod("GET");
//                        } catch (ProtocolException ex) {
//                            OutputWriter.appendToFile(ex);
//                            ex.printStackTrace();
//                        }
//                        conn.setRequestProperty("url", html);
//                        // conn.addRequestProperty("Key", "1BPJZEwpAPJxmeWoEv0h-b8tJkV_rALCcsp5J-EoQ7og");
//                        try {
//                            if (conn.getResponseCode() != 200) {
//                                throw new RuntimeException("Failed : HTTP error code : "
//                                        + conn.getResponseCode());
//                            }
//                        } catch (IOException ex) {
//                            OutputWriter.appendToFile(ex);
//                            ex.printStackTrace();
//                        }
//
//                        BufferedReader rangeReade = null;
//                        try {
//                            rangeReade = new BufferedReader(new InputStreamReader(
//                                    (conn.getInputStream())));
//                        } catch (IOException ex) {
//                            OutputWriter.appendToFile(ex);
//                            ex.printStackTrace();
//                        }
//                        StringBuffer rangeResponse = new StringBuffer();
//                        String rangeResponseOutput;
//
//                        try {
//                            while ((rangeResponseOutput = rangeReade.readLine()) != null) {
//
//                                rangeResponse.append(rangeResponseOutput);
//                            }
//                        } catch (IOException ex) {
//                            OutputWriter.appendToFile(ex);
//                            ex.printStackTrace();
//                        }
//System.out.println("@@@@@12@@@@@");
//                        conn.disconnect();
//                        // Create JsonReader from Json.
//                        StringBuffer bff = rangeResponse;
//                        byte[] bytes1 = bff.toString().getBytes();
//                        /*
//            * Get ByteArrayInputStream from byte array.
//                         */
//
////                        InputStream inputStream1 = new ByteArrayInputStream(bytes1);
////                        JsonObject empObj1;
//                        // Get the JsonObject structure from JsonReader.
////                        try (JsonReader reader = Json.createReader(inputStream1)) {
////                            // Get the JsonObject structure from JsonReader.
////                            empObj1 = reader.readObject();
////                        }
////                        JsonObject addrObj1 = empObj1.getJsonObject("lighthouseResult");
////                        //  System.out.println(addrObj);
////                        JsonObject addrObj11 = addrObj1.getJsonObject("audits");
////                        JsonObject addrObj2 = addrObj11.getJsonObject("network-requests");
////                        JsonObject addrObj3 = addrObj2.getJsonObject("details");
////                        JsonArray arrObj1 = addrObj3.getJsonArray("items");
////                        //  System.out.println(addrObj4);
////                        Integer rescode = 0;
////                        for (int i = 0; i <= arrObj1.size(); i++) {
////                            // System.out.println("^^^^^^^^^^^^^$$$$$^^^^^^^^^^^^^");
////
////                            JsonObject addrObj4 = null;
////                            try {
////                                addrObj4 = arrObj1.getJsonObject(i);
////                                String geturl = addrObj4.getString("url");
////                                String type = addrObj4.getString("resourceType");
////                                Integer gettranfer = addrObj4.getInt("transferSize");
////                                rescode = addrObj4.getInt("statusCode");
////                                if ((geturl.equals(html) || geturl.equals(html + "/")) && type.contains("Document")) {
////                                    html1.add(html + ",CCTV," + gettranfer);
////
////                                }
////                            } catch (Exception ex) {
////                                OutputWriter.appendToFile(ex);
////                                ex.printStackTrace();
////
////                            }
////                        }
//
//                        ///////
////                        URL site = new URL(html);
////                        BufferedReader in = new BufferedReader(
////                                new InputStreamReader(
////                                        site.openStream()));
//
////                        String inputLine;
////                        String a = "";
//
////                        while ((inputLine = in.readLine()) != null) {
////                            a = a + inputLine + "\n";
////                            // System.out.println(inputLine);
////                        }
////                        html4 = a;
////
////                        in.close();
//
//                        ////////
////                        int avai = 0;
////                        int ava = 0;
////                        int a1va = 0;
////                        int avail = 0;
////                        int availa = 0;
////                        int availab = 0;
////                        int availabl = 0;
////                        int available = 0;
////                        int available1 = 0;
////                        int available2 = 0;
////                        int available3 = 0;
////                        int a1 = 0;
////                        int a2 = 0;
////                        URL url_Object = new URL(html);
////                        URLConnection urlConnection_Obj = url_Object.openConnection();
////                        Map<String, List<String>> map = urlConnection_Obj.getHeaderFields();
////                        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
////
////                            String name = entry.getKey();
////                            String val = entry.getValue().toString();
////
////                            if (name.toLowerCase().contains("ETag".toLowerCase())) {
////                                available2++;
////                                if (val.equals("[]")) {
////
////                                    etag = "Fail";
////                                    Issueetag = Issueetag + "\nFail-" + name + "->" + val + "->" + html;
////
////                                } else {
////
////                                }
////                            }
////                            if (rescode >= 200 && rescode < 300) {
////                                a2++;
////                            } else {
////                                respond = "Fail";
////                                Issuerespond = Issuerespond + "\nFail-" + rescode + "->" + html;
////                            }
////
////                            if (name.toLowerCase().contains("Connection".toLowerCase())) {
////                                available3++;
////                                if (val.toLowerCase().contains("keep-alive".toLowerCase())) {
////
////                                } else {
////
////                                    IssueConnectionAlive = IssueConnectionAlive + "\nFail-" + name + "->" + val + "->" + html;
////                                    ConnectionAlive = "Fail";
////
////                                }
////                            }
////                            if (name.toLowerCase().contains("Strict-Transport-Security".toLowerCase())) {
////                                available1++;
////                                if (val.contains("max-age=")) {
////
////                                    String cacheNum = "";
////                                    String pattern1 = "max-age=([0-9]+)";
////
////                                    Pattern r1 = Pattern.compile(pattern1);
////
////                                    Matcher m1 = r1.matcher(val);
////
////                                    if (m1.find()) {
////                                        cacheNum = m1.group(1);
////
////                                    } else {
////
////                                    }
////                                    if (Integer.parseInt(cacheNum) >= Integer.parseInt(transport_Security)) {
////
////                                    } else {
////
////                                        IssueTransport_Security = IssueTransport_Security + "\n" + name + " " + val;
////
////                                        Transport_Security = "Fail";
////
////                                    }
////                                } else {
////                                    Transport_Security = "Fail";
////                                    IssueTransport_Security = IssueTransport_Security + "\n " + name + " " + val;
////                                }
////                            }
////                            if (name.toLowerCase().contains("Vary".toLowerCase()) || name.toLowerCase().contains("Connection".toLowerCase())) {
////                                ava++;
////                                if (val.toLowerCase().contains("Encoding".toLowerCase())) {
////                                    a1va++;
////                                } else {
////
////                                }
////                            }
////                            if (name.toLowerCase().contains("X-Content-Type".toLowerCase())) {
////                                availa++;
////                                if (val.toLowerCase().contains("nosniff".toLowerCase())) {
////
////                                } else {
////
////                                    IssueX_Content_Type = IssueX_Content_Type + "\n " + name + " " + val;
////                                    X_Content_Type = "Fail";
////                                }
////                            }
////                            if (name.toLowerCase().contains("X-FRAME-OPTIONS".toLowerCase())) {
////                                availab++;
////                                if (val.toLowerCase().contains("SAMEORIGIN".toLowerCase())) {
////
////                                } else {
////
////                                    IssueX_FRAME_OPTIONS = IssueX_FRAME_OPTIONS + "\n " + name + " " + val;
////                                    X_FRAME_OPTIONS = "Fail";
////                                }
////                            }
////                            if (name.toLowerCase().contains("X-Robots-Tag".toLowerCase())) {
////                                availabl++;
////
////                                if (val.toLowerCase().contains("noindex, nofollow".toLowerCase())) {
////
////                                } else {
////
////                                    IssueX_Robots_Tag = IssueX_Robots_Tag + "\n " + name + " " + val;
////                                    X_Robots_Tag = "Fail";
////                                }
////                            }
////                            if (name.toLowerCase().contains("X-XSS-Protection".toLowerCase())) {
////                                available++;
////                                if (val.toLowerCase().contains("1; mode=block".toLowerCase())) {
////
////                                } else {
////
////                                    IssueX_XSS_Protection = IssueX_XSS_Protection + "\n " + name + " " + val;
////                                    X_XSS_Protection = "Fail";
////                                }
////                            }
////
////                            if (name.toLowerCase().contains("Content-Type".toLowerCase()) && !(name.toLowerCase().contains("X".toLowerCase()))) {
////                                avai++;
////                                if (val.toLowerCase().contains("UTF-8".toLowerCase())) {
////
////                                } else {
////                                    IssuehtmlcharacterSet = IssuehtmlcharacterSet + "\n " + name + " " + val;
////                                    htmlcharacterSet = "Fail";
////
////                                }
////                            }
////                            ///
////
////                            if (name.toLowerCase().contains("Cache-Control".toLowerCase())) {
////                                avail++;
////                                if (val.contains("max-age=") && !val.contains("no-cache") && val.contains("must-revalidate")) {
////                                    Issuehtmlcache = Issuehtmlcache + "\n must revalidate" + name + " " + val + "call :" + html;
////                                    htmlcache = "Fail";
////                                } else if (val.contains("max-age=") && !val.contains("no-cache")) {
////                                    String cacheNum = "";
////                                    String pattern1 = "max-age=([0-9]+)";
////                                    Pattern r1 = Pattern.compile(pattern1);
////                                    Matcher m1 = r1.matcher(val);
////                                    if (m1.find()) {
////                                        cacheNum = m1.group(1);
////
////                                    } else {
////                                    }
////                                    if (Integer.parseInt(cacheNum) >= Integer.parseInt(htmlCacheio)) {
////                                    } else {
////
////                                        Issuehtmlcache = Issuehtmlcache + "\n " + name + " " + val;
////                                        htmlcache = "Fail";
////                                    }
////                                } else {
////                                    Issuehtmlcache = Issuehtmlcache + "\n " + name + " " + val;
////                                    htmlcache = "Fail";
////                                }
////                            }
////
////                        }
//
//                        //  System.out.println("gzip: " + a1 + "UTF-8: " + avai + " " + ava + "htmlcache: " + avail + "X-Content-Type: " + availa + "X-Frame-Options: " + availab + "X-Robots-Tag: " + availabl + "X-XSS-Protection: " + available + "Strict-Transport-Security: " + available1 + "etag: " + available2 + "Connection: " + available3 + "respond" + a2);
////                        if (a2 == 0) {
////
////                            respond = "Fail";
////                            Issuerespond = Issuerespond + "\nFail-" + rescode + "->" + html;
////
////                        }
////                        if (available2 == 0) {
////
////                            Issueetag = Issueetag + "\n" + html;
////                            etag = "Fail";
////
////                        }
////                        if (available3 == 0) {
////
////                            IssueConnectionAlive = IssueConnectionAlive + "\n" + html;
////                            ConnectionAlive = "Fail";
////
////                        }
////                        if (available1 == 0) {
////                            Transport_Security = "Fail";
////                            IssueTransport_Security = IssueTransport_Security + "\n" + html;
////
////                        }
////                        if (available == 0) {
////                            X_XSS_Protection = "Fail";
////                            IssueX_XSS_Protection = IssueX_XSS_Protection + "\n" + html;
////
////                        }
////                        if (availabl == 0) {
////                            X_Robots_Tag = "Fail";
////                            IssueX_Robots_Tag = IssueX_Robots_Tag + "\n" + html;
////
////                        }
////                        if (availab == 0) {
////                            X_FRAME_OPTIONS = "Fail";
////                            IssueX_FRAME_OPTIONS = IssueX_FRAME_OPTIONS + "\n" + html;
////
////                        }
////                        if (avai == 0) {
////                            htmlcharacterSet = "Fail";
////                            IssuehtmlcharacterSet = IssuehtmlcharacterSet + "\n" + html;
////
////                        }
////
////                        if (availa == 0) {
////                            X_Content_Type = "Fail";
////                            IssueX_Content_Type = IssueX_Content_Type + "\n" + html;
////
////                        }
////                        if (avail == 0) {
////                            htmlcache = "Fail";
////                            Issuehtmlcache = Issuehtmlcache + "\n" + html;
////
////                        }
////                        if (ava == 0 || a1va == 0) {
////                            IssuehtmlEncoding = IssuehtmlEncoding + "\n" + html;
////                            htmlEncoding = "Fail";
////                        }
//
////                    htmlEncoding = "N/A";
////                    htmlcache = "N/A";
////                    htmlcharacterSet = "N/A";
////                    htmlcompress = "N/A";
////                    X_Content_Type = "N/A";
////                    Transport_Security = "N/A";
////                    X_FRAME_OPTIONS = "N/A";
////                    X_Robots_Tag = "N/A";
////                    X_XSS_Protection = "N/A";
//                    }
                    if (img.isEmpty()) {
                        imgAge = "N/A";
                    }
                    if (font.isEmpty()) {
                        fontAge = "N/A";
                        fontcache = "N/A";
                    }
                    if (js.isEmpty()) {
                        jsCompress = "N/A";
                        jscache = "N/A";
                    }
                    if (css.isEmpty()) {
                        cssCompress = "N/A";
                        csscache = "N/A";
                    }
                    if (api.isEmpty()) {
                        apiCompress = "N/A";
                        apicache = "N/A";
                    }
                    if ("".equals(html) && api.isEmpty() && img.isEmpty() && js.isEmpty() && css.isEmpty() && font.isEmpty()) {

                        etag = "N/A";

                    }
                    if ("".equals(html) && api.isEmpty() && font.isEmpty()) {
                        ConnectionAlive = "Pass";
                    }
                    try {
                        tst();
                    } catch (Exception ex) {
                        OutputWriter.appendToFile(ex);
                        ex.printStackTrace();
                    }
                    xcol++;
                    loadTxt += 0.6;
                    loadBar += 0.6;
                    if (!"".equals(IssuehtmlEncoding)) {
                        OutputWriter.appendHarProblem("2,Issue in Html Encoding\n" + IssuehtmlEncoding + "\n\n");
                    }
                    if (!"".equals(IssuehtmlcharacterSet)) {
                        OutputWriter.appendHarProblem("3,Issue in Html CharacterSet\n" + IssuehtmlcharacterSet + "\n\n");
                    }

                    if (!"".equals(IssuejsCompress)) {
                        OutputWriter.appendHarProblem("6,Issue in Js Compress\n" + IssuejsCompress + "\n\n");
                    }
                    if (!"".equals(Issuejscache)) {
                        OutputWriter.appendHarProblem("8,Issue in Js Cache\n" + Issuejscache + "\n\n");
                    }
                    if (!"".equals(IssuecssCompress)) {
                        OutputWriter.appendHarProblem("13,Issue in Css Compress\n" + IssuecssCompress + "\n\n");
                    }

                    if (!"".equals(IssueimgAge)) {
                        OutputWriter.appendHarProblem("23,Issue in Img Cache\n" + IssueimgAge + "\n\n");
                    }
                    if (!"".equals(IssuefontAge)) {
                        OutputWriter.appendHarProblem("36,Issue in Font Cache\n" + IssuefontAge + "\n\n");
                    }
                    if (!"".equals(Issueetag)) {
                        OutputWriter.appendHarProblem("37,Issue in Etag\n" + Issueetag + "\n\n");
                    }
                    if (!"".equals(IssuecdnAssert)) {
                        OutputWriter.appendHarProblem("37,Issue in CDN Assert\n" + IssuecdnAssert + "\n\n");
                    }
                    if (!"".equals(Issueapicache)) {
                        OutputWriter.appendHarProblem("39,Issue in Api Cache\n" + Issueapicache + "\n\n");
                    }
                    if (!"".equals(IssueConnectionAlive)) {

                        OutputWriter.appendHarProblem("40,Issue in Connection Alive\n" + IssueConnectionAlive + "\n\n");
                    }
                    if (!"".equals(IssueapiCompress)) {
                        OutputWriter.appendHarProblem("41,Issue in Api Compress\n" + IssueapiCompress + "\n\n");
                    }
                    if (!"".equals(Issuerespond)) {
                        OutputWriter.appendHarProblem("46,Issue in respond code\n" + Issuerespond + "\n\n");
                    }

                    if (!"".equals(Issuehtmlcache)) {
                        OutputWriter.appendHarProblem("57,Issue in Html Cache\n" + Issuehtmlcache + "\n\n");
                    }
                    if (!"".equals(Issuecsscache)) {
                        OutputWriter.appendHarProblem("58,Issue in Css Cache\n" + Issuecsscache + "\n\n");
                    }
                    if (!"".equals(IssueTransport_Security)) {
                        OutputWriter.appendHarProblem("59,Issue in Transport_Security\n" + IssueTransport_Security + "\n");
                    }
                    if (!"".equals(IssueX_Content_Type)) {

                        OutputWriter.appendHarProblem("60,Issue in X_Content_Type\n" + IssueX_Content_Type + "\n");
                    }
                    if (!"".equals(IssueX_FRAME_OPTIONS)) {
                        OutputWriter.appendHarProblem("61,Issue in X_FRAME_OPTIONS\n" + IssueX_FRAME_OPTIONS + "\n");
                    }
                    if (!"".equals(IssueX_Robots_Tag)) {
                        OutputWriter.appendHarProblem("62,Issue in X_Robots_Tag\n" + IssueX_Robots_Tag + "\n");
                    }
                    if (!"".equals(IssueX_XSS_Protection)) {
                        OutputWriter.appendHarProblem("63,Issue in X_XSS_Protection\n" + IssueX_XSS_Protection + "\n");
                    }
                } catch (Exception ex) {
                    OutputWriter.appendHarProblem("\nHAR file is damage so, please download har file properly and try again  \n");
                    OutputWriter.appendToFile(ex);
                    ex.printStackTrace();
                }
            }
            writer.close();
        } catch (Exception ex) {

            OutputWriter.appendToFile(ex);
            ex.printStackTrace();
        }
    }
}
