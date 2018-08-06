package raejin.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button button_sendData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_sendData = (Button)findViewById(R.id.button_sendData);

        SendListener sendListener = new SendListener();

        button_sendData.setOnClickListener(sendListener);
    }

    class SendListener implements View.OnClickListener {
        Intent intent = new Intent(MainActivity.this, SubActivity.class);

        @Override
        public void onClick(View view) {
            intent.putExtra("INT_KEY", 1234);
            intent.putExtra("STR_KEY", "안녕하세요");
            intent.putExtra("BOOL_KEY", true);
            intent.putExtra("DOUB_KEY", 3.141592);

            startActivity(intent);
        }
    }
}
