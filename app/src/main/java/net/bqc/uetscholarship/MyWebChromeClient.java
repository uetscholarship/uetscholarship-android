package net.bqc.uetscholarship;

import android.webkit.WebChromeClient;
import android.webkit.WebView;

/**
 * Created by BQC-PC on 9/10/2017.
 */
public class MyWebChromeClient extends WebChromeClient {

    private ProgressListener mListener;

    public MyWebChromeClient(ProgressListener listener) {
        mListener = listener;
    }

    @Override
    public void onProgressChanged(WebView view, int newProgress) {
        mListener.onUpdateProgress(newProgress);
        super.onProgressChanged(view, newProgress);
    }

    public interface ProgressListener {
        void onUpdateProgress(int progressValue);
    }
}
