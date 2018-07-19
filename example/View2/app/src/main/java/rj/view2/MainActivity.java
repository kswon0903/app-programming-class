package rj.view2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // 버튼 객체를 저장할 맴버 변수를 만든다.
    Button button_english, button_korean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ID로 레이아웃에 있는 버튼의 객체를 읽어와서 맴버 변수에 저장한다.
        button_english = (Button)findViewById(R.id.button_english);
        button_korean = (Button)findViewById(R.id.button_korean);

        // EngListener의 객체 만들기
        EngListener engListener = new EngListener();
        KorListener korListener = new KorListener();

        BtnListener btnListener = new BtnListener();

        button_korean.setOnClickListener(btnListener);
        button_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),
                        "korean 버튼이 클릭되었습니다.",
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    class BtnListener implements View.OnClickListener {
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.button_english:
                    Toast.makeText(getApplicationContext(),
                            "english 버튼이 클릭되었습니다.",
                            Toast.LENGTH_SHORT).show();
                    break;
                case R.id.button_korean:
                    Toast.makeText(getApplicationContext(),
                            "korean 버튼이 클릭되었습니다.",
                            Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    // english 버튼에게 기능을 부여하기 위한 EngListener를 만드는 클래스
    class EngListener implements View.OnClickListener {

        // 버튼을 클릭했을 때 실행되는 onClick() 함수
        @Override
        public void onClick(View view) {
            // 앱에서 메시지를 출력하는 Toast
            // makeText()함수를 활용하여 메시지를 출력한다.
            // 1번째 매개변수 : getApplicationContext()
            //               => 앱의 정보를 가져오는 함수
            // 2번째 매개변수 : 출력하고 싶은 글자(문자열)
            // 3번째 매개변수 : 글자를 표시하는 시간
            //    Toast.LENGTH_LONG 이나 Toast.LENGTH_SHORT 중 하나 선택
            Toast.makeText(getApplicationContext(),
                    "english 버튼이 클릭되었습니다.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    class KorListener implements View.OnClickListener {
        public void onClick(View view) {
            Toast.makeText(getApplicationContext(),
                    "korean 버튼이 클릭되었습니다.",
                    Toast.LENGTH_LONG).show();
        }
    }

}
