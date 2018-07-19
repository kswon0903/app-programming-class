package raejin.views2nd;

import android.media.Rating;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.MediaController;
import android.widget.RatingBar;
import android.widget.Toast;
import android.widget.VideoView;

import java.net.URI;

public class MainActivity extends AppCompatActivity {

    RatingBar ratingBar_rating1, ratingBar_rating2, ratingBar_rating3;
    VideoView videoView_video;
    WebView webView_web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ratingBar_rating1 = (RatingBar)findViewById(R.id.ratingBar_rating1);
        ratingBar_rating2 = (RatingBar)findViewById(R.id.ratingBar_rating2);
        ratingBar_rating3 = (RatingBar)findViewById(R.id.ratingBar_rating3);

        videoView_video = (VideoView)findViewById(R.id.videoView_video);

        webView_web = (WebView)findViewById(R.id.webView_web);

        ratingBar_rating1.setOnRatingBarChangeListener(new RatingListener());
        ratingBar_rating2.setOnRatingBarChangeListener(new RatingListener());
        ratingBar_rating3.setOnRatingBarChangeListener(new RatingListener());

        Uri uri = Uri.parse("android.resource://"+getPackageName()+
                "/raw/atoms");
        videoView_video.setVideoURI(uri);
        videoView_video.start();

        MediaController mediaController = new MediaController(this);
        videoView_video.setMediaController(mediaController);

        WebSettings ws = webView_web.getSettings();
        ws.setJavaScriptEnabled(true);

        webView_web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView_web.loadUrl("http://www.naver.com");





    }

    class RatingListener implements RatingBar.OnRatingBarChangeListener {

        @Override
        public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
            /**
             * v : 별점
             * b : 사용자가 조작하여 별점을 내린 경우 true, 아니면 false
             */

            switch(((View)ratingBar).getId()) {
                case R.id.ratingBar_rating1:
                    Toast.makeText(getApplicationContext(),
                            "제 점수는요? " + String.valueOf(v),
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ratingBar_rating2:
                    Toast.makeText(getApplicationContext(),
                            "제 점수는요? " + String.valueOf(v),
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ratingBar_rating3:
                    Toast.makeText(getApplicationContext(),
                            "제 점수는요? " + String.valueOf(v),
                            Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
