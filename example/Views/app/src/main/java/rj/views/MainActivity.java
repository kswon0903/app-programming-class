package rj.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    Button btn_left, btn_center, btn_right;
    ImageView imageView_image;
    EditText editText_id, editText_password, editText_number;
    TextView textView_message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_left = (Button)findViewById(R.id.btn_left);
        btn_center = (Button)findViewById(R.id.btn_center);
        btn_right = (Button)findViewById(R.id.btn_right);
        imageView_image = (ImageView)findViewById(R.id.imageView_image);
        editText_id = (EditText)findViewById(R.id.editText_id);
        editText_password = (EditText)findViewById(R.id.editText_password);
        editText_number = (EditText)findViewById(R.id.editText_number);
        textView_message = (TextView)findViewById(R.id.textView_message);

        btn_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("btn listener", "btn_left pressed");
                Toast.makeText(getApplicationContext(),
                        "btn_left pressed",
                        Toast.LENGTH_SHORT).show();

                String id = editText_id.getText().toString();
                textView_message.setText(id);
            }
        });

        btn_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("btn_listener", "btn_center pressed");
                Toast.makeText(getApplicationContext(),
                        "btn_center pressed",
                        Toast.LENGTH_SHORT).show();

                String password = editText_password.getText().toString();
                textView_message.setText(password);
            }
        });

        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("btn listener", "btn_right pressed");
                Toast.makeText(getApplicationContext(),
                        "btn_right pressed",
                        Toast.LENGTH_SHORT).show();

                String number = editText_number.getText().toString();
                textView_message.setText(number);
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

        editText_number.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Log.d("edittext listener", editText_id.getText().toString());
                Toast.makeText(getApplicationContext(),
                        editText_id.getText().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        editText_id.addTextChangedListener(new TextWatcher() {
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