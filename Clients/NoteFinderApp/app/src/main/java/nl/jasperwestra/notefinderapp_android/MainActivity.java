package nl.jasperwestra.notefinderapp_android;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;


public class MainActivity extends ActionBarActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private ImageView cameraView;
    private File image;
    private String picturePath;
    public static final String NOTE_ROOT_LOCATION = Environment.getExternalStorageDirectory().toString() + "/noteapp/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.cameraView = (ImageView)findViewById(R.id.cameraView);
    }

    /**
     * Starts an intent to take a picture with the camera
     * @param view
     */
    public void takePicture(View view)
    {
        Log.d("PICTURE","TakePicture();");
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            try {
                image = createImageFile();
            } catch (IOException ex) {
                this.showAlert("Unable to take snapshot!");
                Log.e("FILE", "Image could not be saved. " + ex.getMessage());
            }
            if (image != null) {
                try {
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(image));
                } catch (Exception e)
                {
                    this.showAlert("Error while using MediaStore!");
                }
                startActivityForResult(cameraIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
            } else {
                this.showAlert("Image saving failed.");
                Log.e("IMAGE","Image saving failed.");
            }
        }
    }

    /**
     * Called when the button Send Picture was pressed
     * @param view
     */
    public void sendPicture(View view)
    {
        if(image != null)
        {
            ProgressDialog progressDialog;

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Sending file and calculating result...");
            progressDialog.setIndeterminate(true);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setCancelable(true);

            final ParseDownload downloadTask = new ParseDownload(MainActivity.this, progressDialog ,image);
            downloadTask.execute("http://jasperwestra.nl/post.php");

            progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    downloadTask.cancel(true);
                }
            });
        }
        else
        {
            this.showAlert("No image was taken!");
        }
    }

    /**
     * Reads back the result and handles puts the image in the frame and adds it to the gallery.
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Button sendButton = (Button)this.findViewById(R.id.sendPictureButton);

            addPictureToGallery();
            setPictureInView();

            sendButton.setEnabled(true);
            Log.d("RESULT","Picture taken.");
        }
        else {
            Log.e("RESULT", "ERROR IN RESULT");
        }
    }

    /**
     * Adds the picture to the gallery.
     */
    private void addPictureToGallery()
    {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(picturePath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    /**
     * Sets the taken picure in the PictureView (cameraView)
     */
    private void setPictureInView() {
        int targetW = cameraView.getWidth();
        int targetH = cameraView.getHeight();

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(picturePath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;

        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(picturePath, bmOptions);
        cameraView.setImageBitmap(bitmap);
    }

    /**
     * Creates a file to put the image data in.
     * @return
     * @throws IOException
     */
    private File createImageFile() throws IOException
    {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "NOTEAPP_JPEG_" + timeStamp;
        /*
        String storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        File imageFile = new File(storageDir + "/" + imageFileName + ".jpg");
        picturePath = "file:" + imageFile.getAbsolutePath();
        */
        File directory = new File(NOTE_ROOT_LOCATION);
        directory.mkdirs();
        File imageFile = new File(directory, imageFileName + ".jpg");
        if(imageFile.exists())
        {
            imageFile.delete();
        }

        picturePath = imageFile.getAbsolutePath();
        Log.d("LOCATION", picturePath);
        return imageFile;
    }

    /**
     * Shows a dialog message to the user.
     * @param message
     */
    private void showAlert(String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        builder.create();
        builder.show();
    }
}
