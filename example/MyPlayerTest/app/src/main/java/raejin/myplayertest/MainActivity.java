package raejin.myplayertest;

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
import java.io.File;
import java.io.FilenameFilter;
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

        setComponents();
    }

    private void setComponents() {
//컴포넌트의 객체를 생성함
        button_prev = (Button) findViewById(R.id.button_prev);
        button_play_pause = (Button) findViewById(R.id.button_play_pause);
        button_stop = (Button) findViewById(R.id.button_stop);
        button_next = (Button) findViewById(R.id.button_next);
        textView_music_title = (TextView) findViewById(R.id.textView_music_title);
        listView_playlist = (ListView) findViewById(R.id.listView_playlist);
        mediaPlayer = new MediaPlayer();
// SD카드에서 Mp3 파일을 검색하여 arraylist에 저장
        music_path_list = findFileByExt("mp3");

        try {
            if (music_path_list == null) { // 음악 파일의 경로를 읽어올 때 에러가 난 경우
                Log.d("MediaPlayer", "music path list is null.");
                throw new Exception();

            } else if (music_path_list.size() == 0) { // mp3 파일이 없는 경우
                Log.d("MediaPlayer", "music path list is empty.");
                throw new Exception();
            } else { // 찾은 mp3 파일에서 첫번째 파일을 재생할 준비
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
                    playMusicByIndex(play_index);
                    break;
                case R.id.button_play_pause:
                    playMusic();
                    break;
                case R.id.button_stop:
                    stopMusic();
                    break;
                case R.id.button_next:
                    play_index =
                            (play_index == (music_path_list.size() - 1) ? 0 : (play_index + 1));
                    playMusicByIndex(play_index);
                    break;
            }
            setMusicTitle(play_index);
        }
    }

    private void setMusicTitle(int play_index) {
        String path = music_path_list.get(play_index);
        String[] split_path = path.split("/");
//textView_music_title.setText(split_path[1)]);
        textView_music_title.setText(split_path[(split_path.length - 1)]);
    }

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

    private void stopMusic() {
        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.prepare();
            }
        } catch (Exception e) {
            Log.d("stopMusic", e.getMessage());
        }
    }

    private void playMusic() {
        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                button_play_pause.
                        setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),
                                R.mipmap.baseline_pause_black_48dp));

                Log.d("playMusic", "pause button pushed");
            } else {
                mediaPlayer.start();
                button_play_pause.
                        setBackgroundDrawable(ContextCompat.getDrawable(getApplicationContext(),
                                R.mipmap.baseline_play_arrow_black_48dp));

                Log.d("playMusic", "play button pushed");
            }
        } catch (Exception e) {
            Log.d("playMusic", e.getMessage());
        }
    }

    private void playMusicByIndex(int list_index) {
        try {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.prepare();
            }
            mediaPlayer.reset();
            mediaPlayer.setDataSource(music_path_list.get(list_index));
            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch (Exception e) {
            Log.d("playMusicByIndex", e.getMessage());
        }
    }

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

    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
// Mediaplayer 객체를 소멸
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}