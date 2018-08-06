package raejin.myplayer;

import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button button_prev, button_play_pause, button_stop, button_next;
    TextView textView_music_title;
    ListView listView_playlist;
    MediaPlayer mediaPlayer;
    ArrayList<String> music_path_list;
    ArrayAdapter<String> adapter_playlist;

    int play_index = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //컴포넌트의 객체를 생성함
        button_prev = (Button) findViewById(R.id.button_prev);
        button_play_pause = (Button) findViewById(R.id.button_play_pause);
        button_stop = (Button) findViewById(R.id.button_stop);
        button_next = (Button) findViewById(R.id.button_next);
        textView_music_title = (TextView) findViewById(R.id.textView_music_title);
        listView_playlist = (ListView) findViewById(R.id.listView_playlist);
        mediaPlayer = new MediaPlayer();
        // SD 카드에서 mp3파일을 검색하여 arrayList에 저장한다.
        music_path_list = findFileByExt("mp3");
        try {
            if (music_path_list == null) { // 음악 파일의 경로를 읽어올 때 에러가 난 경우
                Log.d("MediaPlayer", "music path list is null.");
                throw new Exception();

            } else if (music_path_list.size() == 0) { // mp3 파일이 없는 경우
                Log.d("MediaPlayer", "music path list is empty.");
                throw new Exception();
            } else { // 찾은 mp3 파일에서 첫번째 파일을 재생할 준비
                //play_index는 지금 재생할 mp3파일의 순서번호
                mediaPlayer.setDataSource(music_path_list.get(play_index));
                mediaPlayer.prepare();
                Log.d("MediaPlayer", "media player object setting complete.");
            }
        } catch (Exception e) {
            Log.d("MediaPlayer", e.getMessage());
        }
        button_prev.setOnClickListener(new BtnListener());
        button_play_pause.setOnClickListener(new BtnListener());
        button_stop.setOnClickListener(new BtnListener());
        button_next.setOnClickListener(new BtnListener());

        adapter_playlist = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_list_item_1,
                getMusicTitleFromPath(music_path_list));
        listView_playlist.setOnItemClickListener(new ListListener());
        listView_playlist.setAdapter(adapter_playlist);
    }

    class ListListener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            play_index = i;
            playMusicByIndex(i);
            setMusicTitle(i);
        }
    }

    class BtnListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button_prev:
                    play_index =
                            (play_index == 0 ? (music_path_list.size() - 1) : (play_index - 1));
                    // 순서번호로 음악을 재생하는 함수
                    playMusicByIndex(play_index);
                    break;
                case R.id.button_play_pause:
                    // 음악을 재생 일시중지할 때 활용하는 함수
                    playMusic();
                    break;
                case R.id.button_stop:
                    // 음악을 중단하는 함수
                    stopMusic();
                    break;
                case R.id.button_next:
                    play_index =
                            (play_index == (music_path_list.size() - 1) ? 0 : (play_index + 1));
                    playMusicByIndex(play_index);
                    break;
            }
            // TextView에 제목을 출력해주는 함수
            setMusicTitle(play_index);
        }
    }

    /**
     * 파일 이름만 저장한 리스트에서 순서번호로 제목을 텍스트뷰에 출력해주는 함수
     */
    private void setMusicTitle(int play_index) {
        String path = music_path_list.get(play_index);
        String[] split_path = path.split("/");
        textView_music_title.setText(split_path[(split_path.length - 1)]);
    }

    /**
     * mp3 파일 경로의 리스트를 활용하여 mp3 파일 이름만 저장한 리스트를 만든다.
     * @param path_list 파일 경로를 저장한 리스트
     * @return 파일 이름만 저장한 리스트
     */
    private ArrayList<String> getMusicTitleFromPath(ArrayList<String> path_list) {
        ArrayList<String> result = new ArrayList<String>();
        String[] split_path;
        String path;
        for (int i = 0; i < path_list.size(); i++) {
            path = path_list.get(i);
            split_path = path.split("/");

            result.add(split_path[(split_path.length - 1)]);
        }
        return result;

    }

    /**
     * 재생을 중지하는 함수
     */
    private void stopMusic() {
        try {
            // 음악이 재생해야 중지 할수 있으므로 재생중인지 비교한다.
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();  // 음악을 중지 시킨다.
                mediaPlayer.prepare(); // 다음에 음악을 재생할 준비를 한다.
            }
        } catch (Exception e) {
            Log.d("stopMusic", e.getMessage());
        }
    }

    /**
     * 음악을 재생 혹은 일시정지 하는 함수
     */
    private void playMusic() {
        try {
            // 재생이 되어 있는지 여부에 따라서 일시중지 혹은 재생 기능을 수행
            if (mediaPlayer.isPlaying()) { // 재생이 되어 있는 경우는 true, 아니면 false
                mediaPlayer.pause();
                button_play_pause.setText("일시중지");
                Log.d("playMusic", "pause button pushed");
            } else { // 재생이 되지 않은 경우
                mediaPlayer.start();
                button_play_pause.setText("재생");
                Log.d("playMusic", "play button pushed");
            }
        } catch (Exception e) {
            Log.d("playMusic", e.getMessage());
        }
    }

    /**
     * 파일 경로의 리스트에서 순서번호로 해당 파일을 재생하는 함수
     * @param list_index 리스트의 순서번호
     */
    private void playMusicByIndex(int list_index) {
        try {
            // 현재 다른 파일을 재생하고 있는지 확인
            if (mediaPlayer.isPlaying()) {
                // 재생중이라면 음악을 멈추고 준비상태로 바꾼다.
                mediaPlayer.stop();
                mediaPlayer.prepare();
            }
            // 새로운 음악파일을 재생한다.
            mediaPlayer.reset(); // 미디어 플레이어의 세팅을 초기화 해준다.
            mediaPlayer.setDataSource(music_path_list.get(list_index)); // 재생할 mp3 파일의 경로를 등록한다.
            mediaPlayer.prepare(); // 음악파일을 재생할 준비를 한다.
            mediaPlayer.start(); // 음악파일을 재생한다.

        } catch (Exception e) {
            Log.d("playMusicByIndex", e.getMessage());
        }
    }

    /**
     * SD카드에서 특정 확장자 이름을 가진 파일 경로를 리스트로 만들어 주는 함수
     * @param ext 확장자 이름
     * @return 파일의 경로를 가지고 있는 리스트
     */
    private ArrayList<String> findFileByExt(String ext) {
        ArrayList<String> temp_array = new ArrayList<String>();
        final String file_ext = ext;
        // 1. SD카드가 인식되어 있는지 확인한다.
        // 인식이 된경우에는 getExternalStorageState() 함수가 Environment.MEDIA_MOUNT 값을 리턴

        String state = Environment.getExternalStorageState();

        if (state.equals(Environment.MEDIA_MOUNTED)) {
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
            String[] file_list = sdRoot.list(filter);
            // 5. 검색 한 파일 리스트를 출력
            for (int i = 0; i < file_list.length; i++) {
                temp_array.add(sd_path + "/" + file_list[i]);
            }
            Log.d("findFileByExt", ext + " file is listed.");
            return temp_array;
        } else {
            Log.d("findFileByExt", "SD card is unmounted.");
            return null;
        }
    }

}