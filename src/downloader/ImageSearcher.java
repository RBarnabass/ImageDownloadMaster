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

    public void getImage(String line) throws IOException {

        Pattern pattern = Pattern.compile("(http|https|ftp)://\\S*?\\.(png|jpg|gif)");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            String str = matcher.group();
            savePicture(str);
        }
    }

    public void savePicture(String picture) throws IOException {

        FileOutputStream fos = null;
        URL url = new URL(picture);
        URLConnection urlConnection = url.openConnection();
        try (InputStream is = urlConnection.getInputStream()) {

            String str = picture.substring(8, picture.length()).replace('/', '.');

            File folder = new File(getType(str));
            if (!folder.exists()) {
                folder.mkdir();
            }
            fos = new FileOutputStream(folder + "/" + str);

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
            } catch (IOException e) {
                System.err.println(" Problem with release memory in writing process " + e.getMessage());
            }
        }
    }

    private String getType(String line) {
        if (line.contains("png")) {
            return "src/png";
        } else if (line.contains("jpg")) {
            return "src/jpg";
        } else if (line.contains("gif")) {
            return "src/gif";
        } else if (line.contains("jpeg")) {
            return "src/jpeg";
        }
        return "";
    }
}
