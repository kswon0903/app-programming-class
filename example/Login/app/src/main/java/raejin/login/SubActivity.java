package raejin.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SubActivity extends AppCompatActivity {

    TextView textView_sendData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();

        textView_sendData = (TextView)findViewById(R.id.textView_sendData);

        int int_value = intent.getIntExtra("INT_KEY", -1);
        String str_value = intent.getStringExtra("STR_KEY");
        Boolean bool_value = intent.getBooleanExtra("BOOL_KEY", false);
        double doub_value = intent.getDoubleExtra("DOUB_KEY", -1);

        String temp = "전달받은 값은? \nint : "+ int_value + " \nstring : " + str_value +
                " \nboolean : " + bool_value + " \ndouble : " + doub_value;

        textView_sendData.setText(temp);
    }
}
