package raejin.calendarview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SubActivity extends AppCompatActivity {
    TextView textView_sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        textView_sub = (TextView)findViewById(R.id.textView_sub);

        TextListener textListener = new TextListener();

        textView_sub.setOnClickListener(textListener);
    }

    class TextListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            finish();
        }
    }
}
