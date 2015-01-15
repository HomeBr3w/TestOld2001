package nl.jasperwestra.notefinderapp_android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
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
import java.io.OutputStreamWriter;


public class MainActivity extends ActionBarActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private ImageView cameraView;
    private Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.cameraView = (ImageView)findViewById(R.id.cameraView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void takePicture(View view)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    public void sendPicture(View view)
    {
        if(image != null)
        {
            Client client = new Client("jasperwestra.nl");
            File file = new File("image.bmp");
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 0, output);
            byte[] bitmapdata = output.toByteArray();
            FileOutputStream fos;
            try {
                fos = new FileOutputStream(file);
                fos.write(bitmapdata);
                client.sendSheet(file);
                fos.close();
            }
            catch(Exception ex)
            {
                Log.d("PICTURE", "Could not send image. " + ex.getMessage());
            }
        }
        else
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("There is no image!")
                    .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                        }
                    });
            builder.create();
            builder.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK)
        {
            image = (Bitmap)data.getExtras().get("data");
            cameraView.setImageBitmap(image);
            Button sendButton = (Button)this.findViewById(R.id.sendPictureButton);
            sendButton.setActivated(true);
        }
    }
}
