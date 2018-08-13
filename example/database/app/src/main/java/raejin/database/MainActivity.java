package raejin.database;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    TextView textView_result;
    Button button_main;

    DbAsyncTask dbAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView_result = (TextView)findViewById(R.id.textView_result);
        button_main = (Button)findViewById(R.id.button_main);


        button_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbAsyncTask = new DbAsyncTask();
                dbAsyncTask.execute("http://192.168.0.77:3000/");
            }
        });
    }
    // 서버와의 데이터 통신을 위한 클래스
    public class DbAsyncTask extends AsyncTask<String, Integer, String> {
        URL url_obj;
        String url;
        HashMap<String, String> result_map;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // 데이터를 받아서 저장할 객체를 만든다.
            result_map = new HashMap<String, String>();
        }

        @Override
        protected String doInBackground(String... strings) {
            // 받은 데이터를 임시로 저장하는 변수
            String result = null;

            try {
                //서버 주소를 저장한다.
                url = strings[0];
                // 서버에 접속할 수 있는 URL 객체를 만든다.
                url_obj = new URL(url);

                // 서버에 데이터를 요청하기 위해서 연결 객체(conn)를 만든다.
                HttpURLConnection conn
                        = (HttpURLConnection)url_obj.openConnection();

                conn.setRequestMethod("POST");  // POST 방식으로 통신
                conn.setDoOutput(true);         // 쓰기 허용
                conn.setDoInput(true);          // 읽기 허용

                // 보낼 데이터를 0과 1의 조합인 바이트로 바꾼다.
                byte[] output = "보내는 데이터닷!!!".getBytes("UTF-8");
                // 서버에 전송한다.
                OutputStream os = conn.getOutputStream();
                os.write(output);
                os.close();

                int resCode = conn.getResponseCode();
                Log.d("DbAsyncTask", "resCode : " +resCode);
                String line;
                StringBuffer response = new StringBuffer();

                switch(resCode) {
                    // 서버가 제대로 응답한 경우
                    case 200:
                        InputStream is = conn.getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        // 서버에서 전달한 데이터를 가져온다.
                        while((line = br.readLine()) != null) {
                            response.append(line);
                            response.append('\r');
                        }
                        br.close();
                        // 전달된 데이터를 임시 변수에 저장한다.
                        result = response.toString();
                        break;
                }
            } catch (MalformedURLException me) {
                Log.d("DbAsyncTask", "url error");
                me.printStackTrace();
            } catch (IOException ioe) {
                Log.d("DbAsyncTask", "openConnection error");
                ioe.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            // 서버로부터 전달된 데이터 result에서 데이터를 추려낸다.
            if(result != null) {
                try {
                    // 전달된 문자열 데이터를 json 객체로 만든다.
                    JSONObject jsonObject = new JSONObject(result);
                    // json 객체에서 데이터 이름이 name, school인 데이터를 가져온다.
                    String rcv_name = jsonObject.getString("name");
                    String rcv_school = jsonObject.getString("school");

                    // 가져온 데이터를 hashmap 객체에 넣는다.
                    result_map.put("name", rcv_name);
                    result_map.put("school", rcv_school);

                    textView_result.setText("이름 :" + result_map.get("name") + "\n"
                                            +"학교 : " + result_map.get("school"));

                    Log.d("DbAsyncTask", "finished");
                } catch (JSONException je) {
                    Log.d("DbAsyncTask", "jsonObject error");
                    je.printStackTrace();
                }
            }

        }
    }
}
