package raejin.myportflio;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import raejin.util.Static;

public class MainActivity extends AppCompatActivity {
    WebView webView_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView_main = (WebView)findViewById(R.id.webView_main);

        // 웹뷰 관련 세팅
        WebSettings ws = webView_main.getSettings();
        ws.setJavaScriptEnabled(true);
        webView_main.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if(url.startsWith(Static.RESERVATION_URL)) {
                    // 예약 액티비티로 이동하는 코드 적용
                    Intent intent = new Intent(
                        getApplicationContext(), ReserveActivity.class);
                    startActivity(intent);
                } else {
                    view.loadUrl(url);
                }
                return true;
            }
        });
        webView_main.loadUrl(Static.MAIN_PAGE_URL);
    }
}
