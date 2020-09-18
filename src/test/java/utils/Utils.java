package utils;

import core.TestsCore;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Utils {

    private static Logger logger = LogManager.getLogger(Utils.class);

    public static void downloadFileFromURL(String urlOfFile, String pathToSaveFile) {
        try (BufferedInputStream in = new BufferedInputStream(new URL(urlOfFile).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(pathToSaveFile)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            logger.error("Can't download file " + urlOfFile + " to " + pathToSaveFile + "\n" + e);
        }
    }

    public static String getPathToProjectRoot() {
        try {
            return new File(new File(".").getAbsolutePath())
                    .getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
