/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.input;

import static com.mycompany.userInterface.Ui.csv_Location;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author pravin.a
 */
class Reading {

    public static String csv = csv_Location;

    public static String[] inputCache() throws FileNotFoundException, IOException {
        String line = "";
        String splitBy = ",";
        String aa[] = null;

//parsing a CSV file into BufferedReader class constructor  
        BufferedReader br = new BufferedReader(new FileReader(csv));
        while ((line = br.readLine()) != null) //returns a Boolean value  
        {
            String[] employee = line.split(splitBy);    // use comma as separator  
            for (String a : employee) {
                int index = 0;

                // System.out.println(a);
                // System.out.println("hai");
                if (a.contains("Js/Css/font Cache")) {
                    // System.out.println("haiiiiiiiii");
                    aa = Arrays.copyOfRange(employee, 3, employee.length);

                }
            }

        }

        return aa;
    }

    public static String[] inputImgCache() throws FileNotFoundException, IOException {
        String line = "";
        String splitBy = ",";
        String aa[] = null;

//parsing a CSV file into BufferedReader class constructor  
        BufferedReader br = new BufferedReader(new FileReader(csv));
        while ((line = br.readLine()) != null) //returns a Boolean value  
        {
            String[] employee = line.split(splitBy);    // use comma as separator  
            for (String a : employee) {
                int index = 0;

                // System.out.println(a);
                // System.out.println("hai");
                if (a.contains("Img Cache")) {
                    // System.out.println("haiiiiiiiii");
                    aa = Arrays.copyOfRange(employee, 3, employee.length);

                }
            }

        }

        return aa;
    }

    public static String[] inputHtmlCache() throws FileNotFoundException, IOException {
        String line = "";
        String splitBy = ",";
        String aa[] = null;

//parsing a CSV file into BufferedReader class constructor  
        BufferedReader br = new BufferedReader(new FileReader(csv));
        while ((line = br.readLine()) != null) //returns a Boolean value  
        {
            String[] employee = line.split(splitBy);    // use comma as separator  
            for (String a : employee) {
                int index = 0;

                // System.out.println(a);
                // System.out.println("hai");
                if (a.contains("Html Cache")) {
                    // System.out.println("haiiiiiiiii");
                    aa = Arrays.copyOfRange(employee, 3, employee.length);

                }
            }

        }

        return aa;
    }
//

    public static String[] transpSecurity() throws FileNotFoundException, IOException {
        String line = "";
        String splitBy = ",";
        String aa[] = null;

//parsing a CSV file into BufferedReader class constructor  
        BufferedReader br = new BufferedReader(new FileReader(csv));
        while ((line = br.readLine()) != null) //returns a Boolean value  
        {
            String[] employee = line.split(splitBy);    // use comma as separator  
            for (String a : employee) {
                int index = 0;

                // System.out.println(a);
                // System.out.println("hai");
                if (a.contains("Transport_Security")) {
                    // System.out.println("haiiiiiiiii");
                    aa = Arrays.copyOfRange(employee, 3, employee.length);

                }
            }

        }

        return aa;
    }

    public static String[] inputApiCache() throws FileNotFoundException, IOException {
        String line = "";
        String splitBy = ",";
        String aa[] = null;

//parsing a CSV file into BufferedReader class constructor  
        BufferedReader br = new BufferedReader(new FileReader(csv));
        while ((line = br.readLine()) != null) //returns a Boolean value  
        {
            String[] employee = line.split(splitBy);    // use comma as separator  
            for (String a : employee) {
                int index = 0;

                // System.out.println(a);
                // System.out.println("hai");
                if (a.contains("Api Cache")) {
                    // System.out.println("haiiiiiiiii");
                    aa = Arrays.copyOfRange(employee, 3, employee.length);

                }
            }

        }

        return aa;

    }

    public static String[] inputJsCount() throws FileNotFoundException, IOException {
        String line = "";
        String splitBy = ",";
        String aa[] = null;

//parsing a CSV file into BufferedReader class constructor  
        BufferedReader br = new BufferedReader(new FileReader(csv));
        while ((line = br.readLine()) != null) //returns a Boolean value  
        {
            String[] employee = line.split(splitBy);    // use comma as separator  
            for (String a : employee) {
                int index = 0;

                // System.out.println(a);
                // System.out.println("hai");
                if (a.contains("calls counts")) {
                    // System.out.println("haiiiiiiiii");
                    aa = Arrays.copyOfRange(employee, 3, employee.length);

                }
            }

        }

        return aa;
    }

    public static String[] inputsize() throws FileNotFoundException, IOException {
        String line = "";
        String splitBy = ",";
        String aa[] = null;

//parsing a CSV file into BufferedReader class constructor  
        BufferedReader br = new BufferedReader(new FileReader(csv));
        while ((line = br.readLine()) != null) //returns a Boolean value  
        {
            String[] employee = line.split(splitBy);    // use comma as separator  
            for (String a : employee) {
                int index = 0;

                // System.out.println(a);
                // System.out.println("hai");
                if (a.contains("File Size")) {
                    // System.out.println("haiiiiiiiii");
                    aa = Arrays.copyOfRange(employee, 3, employee.length);

                }
            }

        }

        return aa;
    }

    static String[] inputexcl() throws FileNotFoundException, IOException {
        String line = "";
        String splitBy = ",";
        String aa[] = null;

//parsing a CSV file into BufferedReader class constructor  
        BufferedReader br = new BufferedReader(new FileReader(csv));
        while ((line = br.readLine()) != null) //returns a Boolean value  
        {
            String[] employee = line.split(splitBy);    // use comma as separator  
            for (String a : employee) {
                int index = 0;

                // System.out.println(a);
                // System.out.println("hai");
                if (a.contains("Excel Location")) {
                    // System.out.println("haiiiiiiiii");
                    aa = Arrays.copyOfRange(employee, 3, employee.length);

                }
            }

        }

        return aa;
    }

    public static String[] inputJsCall() throws FileNotFoundException, IOException {
        String line = "";
        String splitBy = ",";
        String aa[] = null;

//parsing a CSV file into BufferedReader class constructor  
        BufferedReader br = new BufferedReader(new FileReader(csv));
        while ((line = br.readLine()) != null) //returns a Boolean value  
        {
            String[] employee = line.split(splitBy);    // use comma as separator  
            for (String a : employee) {
                int index = 0;

                if (a.contains("JS calls")) {
                    aa = Arrays.copyOfRange(employee, 3, employee.length);

                }
            }

        }

        return aa;
    }

    public static String[] domainCall() throws FileNotFoundException, IOException {
        String line = "";
        String splitBy = ",";
        String aa[] = null;

//parsing a CSV file into BufferedReader class constructor  
        BufferedReader br = new BufferedReader(new FileReader(csv));
        while ((line = br.readLine()) != null) //returns a Boolean value  
        {
            String[] employee = line.split(splitBy);    // use comma as separator  
            for (String a : employee) {
                int index = 0;

                if (a.contains("Domain")) {
                    aa = Arrays.copyOfRange(employee, 3, employee.length);

                }
            }

        }

        return aa;
    }

    public static String[] cdnCall() throws FileNotFoundException, IOException {
        String line = "";
        String splitBy = ",";
        String aa[] = null;

//parsing a CSV file into BufferedReader class constructor  
        BufferedReader br = new BufferedReader(new FileReader(csv));
        while ((line = br.readLine()) != null) //returns a Boolean value  
        {
            String[] employee = line.split(splitBy);    // use comma as separator  
            for (String a : employee) {
                int index = 0;

                if (a.contains("CDN")) {
                    aa = Arrays.copyOfRange(employee, 3, employee.length);

                }
            }

        }

        return aa;
    }

    public static String[] inputCssCall() throws FileNotFoundException, IOException {
        String line = "";
        String splitBy = ",";
        String aa[] = null;

//parsing a CSV file into BufferedReader class constructor  
        BufferedReader br = new BufferedReader(new FileReader(csv));
        while ((line = br.readLine()) != null) //returns a Boolean value  
        {
            String[] employee = line.split(splitBy);    // use comma as separator  
            for (String a : employee) {
                int index = 0;

                if (a.contains("CSS calls")) {
                    aa = Arrays.copyOfRange(employee, 3, employee.length);

                }
            }

        }

        return aa;
    }

}
