package nl.jasperwestra.notefinderapp_android;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.PowerManager;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by jasper on 21/01/15.
 */


public class ParseDownload extends AsyncTask<String, Integer, String> {

    private Context context;
    private ProgressDialog progressDialog;
    private File fileToSend;
    private PowerManager.WakeLock wakeLock;
    private String fileName = "result.mp3";

    public ParseDownload(Context _context, ProgressDialog _progressDialog, File _fileToSend) {
        this.context = _context;
        this.progressDialog = _progressDialog;
        this.fileToSend = _fileToSend;
    }


    private String getMP3File(HttpResponse response) throws IOException
    {
        String root = MainActivity.NOTE_ROOT_LOCATION;
        File directory = new File(root);
        directory.mkdirs();
        File mediaFile = new File(directory, fileName);
        if(mediaFile.exists())
        {
            mediaFile.delete();
        }
        String path = mediaFile.getAbsolutePath();

        InputStream is = response.getEntity().getContent();
        FileOutputStream fos = new FileOutputStream(new File(path));
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];
        int len = 0;
        while ((len = is.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
        return path;
    }

    @Override
    protected String doInBackground(String... url) {
        String pathToMp3 = null;
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(url[0]);
            InputStreamEntity reqEntity = new InputStreamEntity(new FileInputStream(fileToSend), -1);
            reqEntity.setContentType("binary/octet-stream");
            reqEntity.setChunked(true);
            httppost.setEntity(reqEntity);
            HttpResponse response = httpclient.execute(httppost);
            pathToMp3 = getMP3File(response);
        } catch (IOException ex) {
            Log.e("DOWNLOAD", "IOException thrown in ParseDownload Task. " + ex.getMessage());
        }
        return pathToMp3;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                getClass().getName());
        wakeLock.acquire();
        progressDialog.show();
    }

    @Override
    protected void onProgressUpdate(Integer... progress) {
        super.onProgressUpdate(progress);

        progressDialog.setIndeterminate(false);
        progressDialog.setMax(100);
        progressDialog.setProgress(progress[0]);
    }

    @Override
    protected void onPostExecute(String result) {
        wakeLock.release();
        progressDialog.dismiss();
        if(result != null)
        {
            Intent intent = new Intent();
            intent.setAction(android.content.Intent.ACTION_VIEW);
            File file = new File(MainActivity.NOTE_ROOT_LOCATION + '/' + fileName);
            System.out.println(file.getAbsolutePath());
            intent.setDataAndType(Uri.fromFile(file), "audio/*");
            context.startActivity(intent);
            System.out.println("Playing file ...");
        }
        else
        {
            System.out.println("NO RESULT BOOO!");
        }
    }
}
