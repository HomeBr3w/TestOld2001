package nl.jasperwestra.wgui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;


public class FullscreenActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_fullscreen);


        WebView webview = (WebView)findViewById(R.id.webView);
        webview.getSettings().setJavaScriptEnabled(true);
        webview.loadUrl("http://www.jasperwestra.nl/nf/");


        //findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);
    }
}
