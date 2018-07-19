package rj.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button btn_left, btn_center, btn_right;
    ImageView imageView_image;
    EditText editText_number;
    TextView textView_message;
    CheckBox checkBox_red;
    RadioButton radioButton_blue, radioButton_yellow;
    Switch switch_green;
    RadioGroup radioGroup_colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_left = (Button)findViewById(R.id.btn_left);
        btn_center = (Button)findViewById(R.id.btn_center);
        btn_right = (Button)findViewById(R.id.btn_right);
        imageView_image = (ImageView)findViewById(R.id.imageView_image);
        textView_message = (TextView)findViewById(R.id.textView_message);
        editText_number = (EditText)findViewById(R.id.editText_number);
        checkBox_red = (CheckBox)findViewById(R.id.checkBox_red);
        radioButton_yellow = (RadioButton)findViewById(R.id.radioButton_yellow);
        radioButton_blue = (RadioButton)findViewById(R.id.radioButton_blue);
        switch_green = (Switch)findViewById(R.id.switch_green);
        radioGroup_colors = (RadioGroup)findViewById(R.id.radioGroup_colors);

        switch_green.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                /**
                 * b : 체크 여부
                 */
                if(b) {
                    Toast.makeText(getApplicationContext(),
                            "green 버튼이 ON 되었습니다.",
                            Toast.LENGTH_SHORT).show();

                    switch_green.setText("ON");
                } else {
                    Toast.makeText(getApplicationContext(),
                            "green 버튼이 OFF 되었습니다.",
                            Toast.LENGTH_SHORT).show();
                    switch_green.setText("OFF");
                }
            }
        });

        radioGroup_colors.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                /*
                i(checked id) : 체크된 아이디
                 */
                switch(i) {
                    case R.id.radioButton_blue:
                        Toast.makeText(getApplicationContext(),
                                "Blue 버튼이 선택되었습니다.",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButton_yellow:
                        Toast.makeText(getApplicationContext(),
                                "Yellow 버튼이 선택되었습니다.",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
        checkBox_red.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if(checkBox_red.isChecked()) {
                    Toast.makeText(getApplicationContext(),
                            "RED 버튼이 선택되었습니다.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("btn listener", "btn_left pressed");
                Toast.makeText(getApplicationContext(),
                        "btn_left pressed",
                        Toast.LENGTH_SHORT).show();

                checkBox_red.setChecked(true);
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


        editText_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 입력하기 전에
                /**
                 * charSequence : 수정전 내용
                 * i(start) : 입력된 글자의 위치(0 based)
                 * i1(count) : 수정되는 글자 수
                 * i2(after) : 새로 추가되는 글자 수
                 */
                Log.d("beforeTextChanged", "char : "+charSequence.toString()
                        +" start : "+i+" count : "+i1+" after : "+i2);

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // 입력되는 텍스트가 변할 때

                /**
                 * charSequence : 입력한 내용
                 * i(start) : 입력된 글자의 위치(0 based)
                 * i1(before) : 입력된 글자 대신 삭제된 기존 글자 수
                 * i2(count) : 새로 추가된 글자의 수
                 */
                Log.d("onTextChanged", "char : "+charSequence.toString()
                        +" start : "+i+" before : "+i1+" count : "+i2);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // 입력 후에

                /**
                 * editable : 입력한 내용
                 */
                Log.d("afterTextChanged", editable.toString());
            }
        });

    }
}
