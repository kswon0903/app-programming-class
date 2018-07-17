package rj.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btn_left, btn_center, btn_right;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btn_left = (Button)findViewById(R.id.btn_left);
        btn_center = (Button)findViewById(R.id.btn_center);
        btn_right = (Button)findViewById(R.id.btn_right);

        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("btn_listener", "btn_left pressed");
            }
        });

        btn_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("btn_listener", "btn_center pressed");
            }
        });

        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("btn_listener", "btn_right pressed");
            }
        });

        setContentView(R.layout.activity_main);
    }
}
