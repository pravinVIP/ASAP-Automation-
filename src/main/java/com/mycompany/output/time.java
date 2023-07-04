/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.output;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

/**
 *
 * @author pravin.a
 */
public class time {

    public static String e() {
        SimpleDateFormat sd = new SimpleDateFormat(
                "yyyy.MM.dd G 'at' HH-mm-ss z");
        java.util.Date date = new java.util.Date();
        sd.setTimeZone(TimeZone.getTimeZone("IST"));
        return sd.format(date).toString();
    }
}
