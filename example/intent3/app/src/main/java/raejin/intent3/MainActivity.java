package raejin.intent3;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;

public class MainActivity extends AppCompatActivity {

    Button button_main;

    Button button_sd;
    ImageView imageView_sd;
    String[] list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // sd카드 1단계 : SD카드가 인식되어 있는지 확인한다.
        // 인식이 된경우에는 getExteranlStorageState()함수가 Environment.MEDIA_MOUNTED 라는 값을 리턴한다.
        String state = Environment.getExternalStorageState();

        // sd카드가 잘 인식된 경우
        if(state.equals(Environment.MEDIA_MOUNTED)) {
            // sd카드 2단계 : SD카드의 폴더 주소를 조사한다.
            String sd_path = Environment.getExternalStorageDirectory().getAbsolutePath();

            // SD카드 3단계 : 찾고자 하는 파일의 확장자를 검색하는 필터 객체를 생성한다.
            FilenameFilter filter = new FilenameFilter() {
                @Override
                public boolean accept(File file, String s) {
                    return s.endsWith("jpg");
                }
            };

            // SD카드 4단계 : 만든 파일 필터 객체(filter)를 활용하여
            // SD 카드 경로(sd_path)안에 있는 파일들을 검색(결과는 list에 저장됨)한다.
            File sdRoot = new File(sd_path);
            list = sdRoot.list(filter);

            // SD카드 5단계 : 파일 검색 결과와 SD카드 경로를 조합하여 파일의 완전한 경로를 도출한다.
            // 예시 : sdcard/파일이름.jpg
            if(list.length != 0) {
                for(int i = 0; i < list.length; i++) {
                    list[i] = sd_path + "/" + list[i];
                }
            }
        } else {
            Toast.makeText(getApplicationContext(),"SD카드 인식이 안됨", Toast.LENGTH_SHORT).show();
        }


        // 버튼과 이미지뷰의 객체
        button_sd = (Button)findViewById(R.id.button_sd);
        imageView_sd = (ImageView)findViewById(R.id.imageView_sd);

        button_sd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(list != null) {
                    // 이미지뷰에 그림 넣기

                    // 그림넣기 단계 1 : list 배열에 있는 그림의 주소(list[0])를 읽어온다.
                    File image_file = new File(list[0]);
                    // 그림 리사이징을 위한 설정을 저장할 BitmapFactory.Options 객체를 만든다.
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    // BitmapFactory.Options 객체에서 inSampleSize 값을 바꾼다.
                    // 예시)
                    // inSampleSize의 값이 2이면 원래 이미지 크기의 1/2로 리사이징
                    // inSampleSize의 값이 8이면 원래 이미지 크기의 1/8로 리사이징
                    // 가능하면 짝수로 넣는 것을 추천
                    options.inSampleSize = 4;

                    // 파일 경로와 옵션을 적용해서 이미지 (비트맵파일을 만든다)
                    Bitmap bitmap = BitmapFactory.decodeFile(
                            image_file.getAbsolutePath(),options);

                    // 이미지의 크기에 맞게 리사이징하는 createScaledBitmap()
                    // 첫번째는 비트맵 파일, 두번째는 너비, 세번째는 높이,
                    // 네번째 리사이즈를 적용할 때 나타나는 이미지가 깨지는 것을 필터링 해줌
                    // >> 이미지 깨짐을 최소화
                    Bitmap bitmap_resize
                            = Bitmap.createScaledBitmap(bitmap, 400, 300, true);

                    // 웹뷰에 이미지(비트맵파일)을 보여준다.
                    imageView_sd.setImageBitmap(bitmap_resize);

                }
            }
        });


        // 2 단계
        button_main = (Button)findViewById(R.id.button_main);

        // 4 단계
        IntentListener listener = new IntentListener();

        // 5 단계
        button_main.setOnClickListener(listener);
    }

    // 3 단계
    class IntentListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            // 데이터를 보내는 부분
            // 인텐트 객체에 보내는 엑티비티, 받는 엑티비티를 적어준다.
            Intent intent = new Intent(MainActivity.this,
                    SubActivity.class);

            // 인텐트 객체에 있는 putExtra()함수를 활용해서 데이터를 첨부한다.
            // putExtra()함수에서 첫번째 매개변수는 데이터의 이름
            // 두번째 매개변수는 전달할 데이터
            intent.putExtra("INT_TYPE_NAME", 1234);
            intent.putExtra("STR_TYPE_NAME", "안녕하세요");
            intent.putExtra("BOOL_TYPE_NAME", true);
            intent.putExtra("DOUBLE_TYPE_NAME", 3.141592);

            // 다른 엑티비티에 이동한다.
            startActivity(intent);
        }
    }
}
