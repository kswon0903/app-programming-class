package raejin.intent3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SubActivity extends AppCompatActivity {


    TextView textView_sub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        // 2 단계
        textView_sub = (TextView)findViewById(R.id.textView_sub);

        // 데이터를 받는 부분
        // 보내는 엑티비티에서 전달한 Intent를 intent 변수에 저장한다.
        Intent intent = getIntent();

        // 데이터를 열어보려면 get**Extra()
        // **는 데이터형 ex)int형 >> getIntExtra(), string형 >> getStringExtra()
        // 읽어오는 데이터형에 따라 함수 이름이 바뀐다!!!
        int int_value = intent.getIntExtra("INT_TYPE_NAME", -1);
        boolean bool_value = intent.getBooleanExtra("BOOL_TYPE_NAME", false);
        double doub_value = intent.getDoubleExtra("DOUBLE_TYPE_NAME", -1);
        String str = intent.getStringExtra("STR_TYPE_NAME");

        textView_sub.setText("int : "+ int_value+ " bool : "+bool_value+
                            " doub : "+doub_value+" str : "+str);
    }
}
