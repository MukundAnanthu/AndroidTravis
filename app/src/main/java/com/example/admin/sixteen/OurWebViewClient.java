package com.example.admin.sixteen;

import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by admin on 07-01-2016.
 */
public class OurWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);

        return true;
    }
}
