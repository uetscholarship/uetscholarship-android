package net.bqc.uetscholarship;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

public class BrowserActivity extends AppCompatActivity implements MyWebChromeClient.ProgressListener {

    private WebView mWebView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        // setup web view
        mWebView = findViewById(R.id.wv_browser);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // add progress bar
        mProgressBar = findViewById(R.id.progressBar);
        mWebView.setWebChromeClient(new MyWebChromeClient(this));
        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                mProgressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressBar.setVisibility(View.GONE);
            }
        });


        // load url
        String url = getIntent().getStringExtra(MainActivity.EXTRA_URL);
        if (url != null) {
            mWebView.loadUrl(url);
        }
        else {
            Toast.makeText(this, "Can not load Url", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public void onUpdateProgress(int progressValue) {
        mProgressBar.setProgress(progressValue);
        if (progressValue == 100) {
            mProgressBar.setVisibility(View.INVISIBLE);
        }
    }
}
