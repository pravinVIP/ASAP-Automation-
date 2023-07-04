/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.input;

import com.mycompany.output.OutputWriter;
import java.io.IOException;

/**
 *
 * @author pravin.a
 */
public class InputReader {

    public static String jsCssFontCacheio;
    public static String htmlCacheio;
    public static String transport_Security;
    public static String apiCacheio;
    public static String ImgCacheio;
    public static String sizeio;
    public static String callCount;
    public static String excel;
    public static String jsCallio[];
    public static String CssCallio[];
    public static String Domainio[];
    public static String CDN[];

    public void reader() throws IOException {
        try {

            String inputCache[] = Reading.inputCache();
            jsCssFontCacheio = inputCache[0].trim();
            String inputImgCache[] = Reading.inputImgCache();
            ImgCacheio = inputImgCache[0].trim();
            String inputHtmlCache[] = Reading.inputHtmlCache();
            htmlCacheio = inputHtmlCache[0].trim();
            String transportSecurity[] = Reading.transpSecurity();
            transport_Security = transportSecurity[0].trim();
            String inputApiCache[] = Reading.inputApiCache();
            apiCacheio = inputApiCache[0].trim();
            String inputSize[] = Reading.inputsize();
            sizeio = inputSize[0].trim();

            String inputexcel[] = Reading.inputexcl();
            excel = inputexcel[0].trim();

            String inputCount[] = Reading.inputJsCount();
            callCount = inputCount[0].trim();

            jsCallio = Reading.inputJsCall();
            CssCallio = Reading.inputCssCall();
            Domainio = Reading.domainCall();
            CDN = Reading.cdnCall();
        } catch (Exception ex) {
            OutputWriter.appendToFile(ex);
            ex.printStackTrace();
        }
    }
}
