package org.example;
import org.apache.commons.io.FileUtils;
import org.apache.commons.validator.routines.UrlValidator;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please specify an URL to a file.");
            return;
        }

        String urlString = args[0];
        UrlValidator urlValidator = new UrlValidator();

        if (!urlValidator.isValid(urlString)) {
            System.out.println("This is not a valid URL.");
            return;
        }

        try {
            URL url = new URL(urlString);
            String fileName = new File(url.getFile()).getName();
            File destination = new File(fileName);

            System.out.println("Downloading file: " + fileName);
            FileUtils.copyURLToFile(url, destination);
            System.out.println("Download completed. Saved as: " + fileName);
        } catch (MalformedURLException e) {
            System.out.println("Invalid URL format: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error downloading file: " + e.getMessage());
        }
    }
}