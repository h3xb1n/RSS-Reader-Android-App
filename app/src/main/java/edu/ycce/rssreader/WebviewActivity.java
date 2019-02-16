package edu.ycce.rssreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebviewActivity extends AppCompatActivity {

    public static final String EXTRA_URL = "url";
    private WebView webView;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        webView = (WebView)findViewById(R.id.webView);
        url = getIntent().getStringExtra(EXTRA_URL);

        webView.loadUrl(url);
    }
}
