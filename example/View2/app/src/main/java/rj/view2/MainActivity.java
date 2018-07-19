package rj.view2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    }

    // english 버튼에게 기능을 부여하기 위한 EngListener를 만드는 클래스
    class EngListener implements View.OnClickListener {

        // 버튼을 클릭했을 때 실행되는 onClick() 함수
        @Override
        public void onClick(View view) {

        }
    }

}
