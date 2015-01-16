package nl.jasperwestra.notefinderapp_android;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import org.apache.http.HttpRequest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

/**
 * Created by jasper on 15/01/15.
 */
public class Client {

    private String httpLink;
    private Context context;

    public Client(String _httpLink, Context _context)
    {
        this.httpLink = _httpLink + "/post.php";
        this.context = _context;
    }

    public void sendSheet(File sheetMusic)
    {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(httpLink));
            request.setDescription("Download generated mp3");
            request.setTitle("Generated mp3");

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                request.allowScanningByMediaScanner();
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            }
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "generated.mp3");

            DownloadManager manager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            manager.enqueue(request);

            Log.d("HTTP", "Sending request to " + httpLink);
            PrintWriter writer = null;
            BufferedReader reader = null;
            try {
                URLConnection connection = new URL(httpLink).openConnection();
                connection.setDoOutput(true);
                writer = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
                writer.println("Content-Disposition: form-data; name=\"fileToUpload\"; filename=\"image.bmp\"");
                writer.println("Content-Type: text/plain; charset=UTF-8");
                writer.println();

                reader = new BufferedReader(new InputStreamReader(new FileInputStream(sheetMusic), "UTF-8"));
                for (String line; (line = reader.readLine()) != null; ) {
                    writer.println(line);
                }
                Log.d("HTTP", "Request to " + httpLink + " has been transferred.");
            } catch (Exception ex) {
                Log.d("HTTP", "Exception was raised in sendsheet: " + ex.getMessage());
            } finally {
                if (reader != null) try {
                    reader.close();
                } catch (IOException logOrIgnore) {
                    Log.d("HTTP", "Error closing reader.");
                }
            }

    }
}
