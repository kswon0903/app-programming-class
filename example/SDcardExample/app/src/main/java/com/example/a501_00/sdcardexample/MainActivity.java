package com.example.a501_00.sdcardexample;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button button_find_music, button_find_img;
    TextView textView_main;
    ArrayList<String> img_array, music_array;
    ImageView imageView_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_main = (TextView)findViewById(R.id.textView_main);
        button_find_img = (Button)findViewById(R.id.button_find_img);
        button_find_music = (Button)findViewById(R.id.button_find_music);
        imageView_main = (ImageView)findViewById(R.id.imageView_main);

        button_find_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_array = findFileByExt("jpg");
                textView_main.setText("");

                if(img_array != null) {
                    for(int i = 0; i < img_array.size(); i++) {
                        String temp = textView_main.getText().toString();
                        textView_main.setText(temp + img_array.get(i) +"\n");
                    }

                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize=2;

                    // 경로로 부터 파일을 읽어옴 (그냥 파일)
                    File img_file = new File(img_array.get(0));
                    // 파일을 이미지 파일로 변환 (이미지 파일)
                    Bitmap bitmap = BitmapFactory.decodeFile(img_file.getAbsolutePath(),options);

                    Bitmap bitmap_resize = Bitmap.createScaledBitmap(bitmap, 100, 50, true);

                    // 이미지 뷰에 이미지 파일 적용
                    imageView_main.setImageBitmap(bitmap_resize);
                }



            }
        });

        button_find_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                music_array = findFileByExt("mp3");
                textView_main.setText("");
                for(int i = 0; i < music_array.size(); i++) {
                    String temp = textView_main.getText().toString();
                    textView_main.setText(temp + music_array.get(i) +"\n");
                }
            }
        });
    }

    private ArrayList<String> findFileByExt(String ext) {
        ArrayList<String> temp_array = new ArrayList<String>();
        final String file_ext = ext;
        // 1. SD카드가 인식되어 있는지 확인한다.
        // 인식이 된경우에는 getExternalStorageState() 함수가 Environment.MEDIA_MOUNT 값을 리턴
        String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)) {
            //2 sd카드 폴더의 주소를 가져옴
            String sd_path = Environment.getExternalStorageDirectory()
                    .getAbsolutePath();

            //3. 찾고자 하는 파일의 확장자를 검색해주는 필더 객체를 생성
            FilenameFilter filter = new FilenameFilter() {
                @Override
                public boolean accept(File file, String s) {
                    return s.endsWith(file_ext);
                }
            };
            //4. 파일 필터 객체를 활용하여 파일을 검색
            File sdRoot = new File(sd_path);
            String[] list = sdRoot.list(filter);
            // 5. 검색 한 파일 리스트를 출력
            String temp = "";
            if(list.length != 0) {
                for(int i = 0; i < list.length; i++) {
                    temp += list[i] +"\n";
                    temp_array.add(sd_path+"/"+list[i]);
                }
                return temp_array;
            } else {
                return null;
            }

        } else {
            Toast.makeText(getApplicationContext(),
                    "SD 카드 인식이 안됨",
                    Toast.LENGTH_LONG).show();
            return null;
        }

    }
}
