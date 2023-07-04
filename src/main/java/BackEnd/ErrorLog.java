/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static BackEnd.OverallLog.fileList;
import static BackEnd.OverallLog.sitekey;
import com.mycompany.output.OutputWriter;

/**
 *
 * @author Lenovo
 */
public class ErrorLog {

    List<String> errorlog = new LinkedList();

    public void err() {

        for (File ff : fileList) {
            if (!(ff.toString().contains("log.gz")) && ff.toString().contains("skavastream")) {
                // System.out.println("@@@@@@@@ff->"+ff);
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
                        if (st.contains(sitekey)) {

                            errorlog.add(st);

                        }

                    }

                    //
                } catch (IOException ex) {
                    Logger.getLogger(ErrorLog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        OutputWriter.BackFile("errorlog", errorlog);
        System.out.println("errorlog\n" + errorlog);

    }
}
