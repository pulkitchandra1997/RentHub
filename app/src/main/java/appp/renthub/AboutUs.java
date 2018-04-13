package appp.renthub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AboutUs extends AppCompatActivity {

    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        webview=findViewById(R.id.webview);
        WebSettings wb=webview.getSettings();
        webview.loadUrl("https://www.google.com");
        wb.setJavaScriptEnabled(true);
        wb.setBuiltInZoomControls(true);
        wb.setDisplayZoomControls(true);
        webview.setWebViewClient(new WebViewController());
    }

    private class WebViewController extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
