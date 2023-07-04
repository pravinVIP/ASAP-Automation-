/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.performancetest;

import com.mycompany.har.Har;
import static com.mycompany.har.Har.harProcessEnd;

import com.mycompany.input.InputReader;
import com.mycompany.output.OutputWriter;
import static com.mycompany.performancetest.ThreadRunningToTrack.write;

import static com.mycompany.userInterface.Ui.foundHar;
import static com.mycompany.userInterface.Ui.foundLog;
import BackEnd.OverallLog;
import static BackEnd.OverallLog.logProcessEnd;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pravin.a
 */
class LogTrack extends Thread {

    @Override
    public void run() {
        //System.out.println("ssssss");

        OverallLog log = new OverallLog();

        log.backend();

    }

}

class HarTrack extends Thread {

    @Override
    public void run() {

        Har har = new Har();

        har.UrlTracker();

    }

}

public class ThreadRunningToTrack {

    public static OutputWriter write = new OutputWriter();

    public void startTrack() throws IOException {
        // System.out.println("start");

        new InputReader().reader();
        try {
            write.create();
        } catch (Exception e) {
            write.LoadingView("sheet.xls wrong location", "Pls check the proper sheet.xls location and save on Excel location in input.cxv file");
            for (;;) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ThreadRunningToTrack.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        Thread t1 = null;
        Thread t2 = null;
        if (foundLog == 1) {
            OutputWriter.appendUrlProblem("Url File");
            t1 = new LogTrack();
            t1.start();
        }
        if (foundHar == 1) {
            OutputWriter.appendHarProblem("Har File");
            t2 = new HarTrack();
            t2.start();
        }
        if (foundLog == 0 && foundHar == 0) {
            write.LoadingView("Empty", "No Url or Har found");

        }

        for (;;) {
            if (foundLog == 0 && foundHar == 0) {
                write.LoadingView("Empty", "No Url or Har found");
                write.complete();
                break;
            }
            if (foundLog == 0 || foundHar == 0) {
                // if (urlProcessEnd == 1 || harProcessEnd == 1) {
                if (harProcessEnd == 1 || logProcessEnd == 1) {
                    write.complete();
                    break;
                }

            }

            //   if (!(urlProcessEnd == 1 && harProcessEnd == 1)) {
            if (!(harProcessEnd == 1 && logProcessEnd == 1)) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    OutputWriter.appendToFile(ex);
                    ex.printStackTrace();

                }

            } else {

                write.complete();

                break;
            }
        }
    }

}
