package downloader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ImageSearcher {

    public void getImage(StringBuilder sbHtml) {

        Pattern pattern = Pattern.compile("(http|https|ftp)://\\S*?\\.(png|jpg|gif)");
        Matcher matcher = pattern.matcher(sbHtml.toString());

        while (matcher.find()) {
            String str = matcher.group();
            savePicture(str);
        }
    }

    public void savePicture(String picture) {

        FileOutputStream fos = null;
        InputStream is = null;
        try{
            URL url = new URL(picture);
            URLConnection urlConnection = url.openConnection();
            is = urlConnection.getInputStream();

            String str = picture.substring(8, picture.length()).replace('/', '.');

            if (str.contains("png")) {
                File folder = new File("src/png");
                if (!folder.exists()) {
                    folder.mkdir();
                }
                fos = new FileOutputStream(folder + "/" + str);
            } else if (str.contains("jpg")) {
                File folder = new File("src/jpg");
                if (!folder.exists()) {
                    folder.mkdir();
                }
                fos = new FileOutputStream(folder + "/" + str);
            } else if (str.contains("gif")) {
                File folder = new File("src/gif");
                if (!folder.exists()) {
                    folder.mkdir();
                }
                fos = new FileOutputStream(folder + "/" + str);
            }

            byte[] buff = new byte[1024];
            int read;
            while ((read = is.read(buff, 0, buff.length)) != -1) {
                fos.write(buff, 0, read);
            }
            System.out.println("Image was successfully downloaded");

        } catch (Exception e) {
            System.err.println(" Problem with writing process " + e.getMessage());
        } finally {
            try {
                fos.close();
                is.close();
            } catch (IOException e) {
                System.err.println(" Problem with release memory in writing process " + e.getMessage());
            }
        }
    }
}
