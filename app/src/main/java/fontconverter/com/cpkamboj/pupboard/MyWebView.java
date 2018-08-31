package fontconverter.com.cpkamboj.pupboard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class MyWebView extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.website);


        WebView webView =  findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.loadUrl("http://www.cpkamboj.com/2018/08/welcome-to-pupborad.html");

    }
}
