package downloader;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class IOEngine {

    /*public String inputWebSite() {
        System.out.print("Write your web-site, please - ");                           // After site press space and enter that wait a while please)

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            String str = reader.readLine();
            return str.contains("http") ? str : inputWebSite();
        } catch (IOException e) {
            System.err.println(" Exception with web-site input " + e);
        }
        return inputWebSite();
    }*/

    public String downLoadPicture(String str) throws IOException {

        URL url = new URL(str);
        URLConnection urlConnection = url.openConnection();
        try (
            InputStream is = urlConnection.getInputStream();
            Scanner scanner = new Scanner(is)) {

            StringBuilder sb = new StringBuilder();

            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append('\n');
            }
            return  sb.toString();
        } catch (IOException e) {
            System.err.println(" Problem with reading html page " + e.getMessage());
        }
        return null;
    }
}