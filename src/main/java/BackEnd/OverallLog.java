/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BackEnd;

import static com.mycompany.output.OutputWriter.folder;
import static com.mycompany.userInterface.Ui.logList;
import static com.mycompany.userInterface.Ui.project;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import BackEnd.DashBoardLog;
import BackEnd.ErrorLog;
import BackEnd.NetworkLog;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Lenovo
 */
public class OverallLog {

    public static int count = 1;
    public static int logProcessEnd = 0;
    public static List<File> fileList = new ArrayList<File>();
    public static String sitekey;
    public static String loc;

    public void backend() {

        Iterator<String> ii = logList.iterator();

        while (ii.hasNext()) {
            fileList.clear();
            String source = ii.next();
            String k = null;
            String line = source;
            String pattern = "^(.*[\\\\\\/])?(.*?)(\\.[^.]*?|)$";

            // Create a Pattern object
            Pattern r = Pattern.compile(pattern);

            // Now create matcher object.
            Matcher m = r.matcher(line);

            if (m.find()) {

                k = m.group(2);

            } else {
                System.out.println("NO MATCH");
            }
            sitekey = project;
            String a = source;

            loc = folder;

            loc = loc + k + "-" + count;
            count++;

//         System.out.println(aa);
//System.out.println(aa);
            File f = new File(loc);
            boolean flag = f.mkdirs();
            String destination = loc;
            String password = "l0g@r1thm$";

            try {
                ZipFile zipFile = new ZipFile(source);
                if (zipFile.isEncrypted()) {
                    zipFile.setPassword(password);
                }
                zipFile.extractAll(destination);
            } catch (ZipException e) {
                e.printStackTrace();
            }

            File directoryToZip = new File(loc);

            try {
                System.out.println("1 : " + directoryToZip.getCanonicalPath());
                System.out.println("#########");
            } catch (IOException ex) {
                Logger.getLogger(OverallLog.class.getName()).log(Level.SEVERE, null, ex);
            }
            // System.out.println(directoryToZip);
            // System.out.println(fileList);
            getAllFiles(directoryToZip, fileList);
            for (File ff : fileList) {
                if (ff.toString().contains("log.gz")) {
                    File inputFile = new File(ff + "");
                    String outputFile = getFileName(inputFile, destination);

                    File tarFile = new File(outputFile);

                    try {
                        tarFile = deCompressGZipFile(inputFile, tarFile);
                    } catch (IOException ex) {
                        Logger.getLogger(OverallLog.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }
            int dashboard = 0;
            int error = 0;
            int network = 0;
            fileList.clear();
            getAllFiles(directoryToZip, fileList);
            for (File ff : fileList) {
                if (!(ff.toString().contains("log.gz")) && ff.toString().contains("skavastream")) {

                    File file = new File(ff + "");

                    BufferedReader br = null;
                    try {
                        br = new BufferedReader(new FileReader(file));
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(OverallLog.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    String st;

                    try {
                        while ((st = br.readLine()) != null) {

                            if (st.contains("INFO") && st.contains("@makeRequest")) {
                                network++;

                            } else if (st.contains("DEBUG") && st.contains("semantic")) {
                                dashboard++;

                            } else if (st.contains("Error")) {
                                error++;

                            }

                        }

                        //
                    } catch (IOException ex) {
                        Logger.getLogger(OverallLog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (network > dashboard && network > error) {
                System.out.println(source);
                System.out.println("found network");
                NetworkLog a1 = new NetworkLog();
                a1.netwk();

            } else if (dashboard > error) {
                System.out.println(source);
                System.out.println("found dashboard");
                DashBoardLog a1 = new DashBoardLog();
                try {
                    a1.dash();
                } catch (InterruptedException ex) {
                    Logger.getLogger(OverallLog.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                System.out.println(source);
                System.out.println("found error");
                ErrorLog a1 = new ErrorLog();
                a1.err();
            }

        }

        logProcessEnd = 1;
    }

    private static File deCompressGZipFile(File gZippedFile, File tarFile) throws IOException {
        FileInputStream fis = new FileInputStream(gZippedFile);
        GZIPInputStream gZIPInputStream = new GZIPInputStream(fis);

        FileOutputStream fos = new FileOutputStream(tarFile);
        byte[] buffer = new byte[1024];
        int len;
        while ((len = gZIPInputStream.read(buffer)) > 0) {
            fos.write(buffer, 0, len);
        }

        fos.close();
        gZIPInputStream.close();
        return tarFile;

    }

    /**
     * This method is used to get the tar file name from the gz file by removing
     * the .gz part from the input file
     *
     * @param inputFile
     * @param outputFolder
     * @return
     */
    private static String getFileName(File inputFile, String outputFolder) {
        return outputFolder + File.separator
                + inputFile.getName().substring(0, inputFile.getName().lastIndexOf('.'));
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

}
