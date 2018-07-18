package rj.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_left, btn_center, btn_right;
    ImageView imageView_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_left = (Button)findViewById(R.id.btn_left);
        btn_center = (Button)findViewById(R.id.btn_center);
        btn_right = (Button)findViewById(R.id.btn_right);
        imageView_image = (ImageView)findViewById(R.id.imageView_image);

        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("btn listener", "btn_left pressed");
                Toast.makeText(getApplicationContext(),
                        "btn_left pressed",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btn_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("btn_listener", "btn_center pressed");
                Toast.makeText(getApplicationContext(),
                        "btn_center pressed",
                        Toast.LENGTH_SHORT).show();
            }
        });

        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("btn listener", "btn_right pressed");
                Toast.makeText(getApplicationContext(),
                        "btn_right pressed",
                        Toast.LENGTH_SHORT).show();
            }
        });

        imageView_image.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {
                Log.d("image listener", "image pressed");
                Toast.makeText(getApplicationContext(),
                        "image pressed",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
