package net.bqc.uetscholarship;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class BrowserActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        // setup web view
        webView = findViewById(R.id.wv_browser);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String url = getIntent().getStringExtra(MainActivity.EXTRA_URL);

        if (url != null) {
            webView.loadUrl(url);
        }
        else {
            Toast.makeText(this, "Can not load Url", Toast.LENGTH_LONG).show();
        }
    }

}
