package com.mycompany.performancetest;

import com.mycompany.output.OutputWriter;
import com.mycompany.userInterface.ProcessView;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author pravin.a
 */
class progress extends Thread {

    @Override
    public void run() {
        //System.out.println("ssssss");
        try {
            ProcessView procc = new ProcessView();
            //  System.out.println("ProcessView");
            procc.ma();
        } catch (Exception ex) {

            OutputWriter.appendToFile(ex);
            ex.printStackTrace();
        }

    }

}

class UrlTrack extends Thread {

    @Override
    public void run() {

        ThreadRunningToTrack a = new ThreadRunningToTrack();
        try {
            //System.out.println("ThreadRunningToTrack");
            a.startTrack();
        } catch (IOException ex) {
            OutputWriter.appendToFile(ex);
            ex.printStackTrace();
        }
    }

}

public class ThreadRunningInSequence {

    public static void runn() {
        try {
            // System.out.println("start");
            Thread t1 = new progress();

            Thread t2 = new UrlTrack();

            t2.start();
            t1.start();
        } catch (Exception ex) {

            OutputWriter.appendToFile(ex);
            ex.printStackTrace();
        }

    }
}
