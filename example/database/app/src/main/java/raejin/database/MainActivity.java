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
                dbAsyncTask.execute("http://192.168.219.104:3000/");
            }
        });



    }

    public class DbAsyncTask extends AsyncTask<String, Integer, String> {
        URL url_obj;
        String url;
        HashMap<String, String> result_map;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            result_map = new HashMap<String, String>();
        }

        @Override
        protected String doInBackground(String... strings) {
            publishProgress(10);
            String result = null;

            try {
                url = strings[0];
                url_obj = new URL(url);

                HttpURLConnection conn
                        = (HttpURLConnection)url_obj.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                conn.setDoInput(true);

                byte[] output = strings[0].getBytes("UTF-8");
                OutputStream os = conn.getOutputStream();
                os.write(output);
                os.close();

                int resCode = conn.getResponseCode();
                Log.d("DbAsyncTask", "resCode : " +resCode);
                String line;
                StringBuffer response = new StringBuffer();

                switch(resCode) {

                    case 200:
                        InputStream is = conn.getInputStream();
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        while((line = br.readLine()) != null) {
                            response.append(line);
                            response.append('\r');
                        }
                        br.close();
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
            textView_result.setText(result);
            if(result != null) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String name = jsonObject.getString("name");
                    String school = jsonObject.getString("school");

                    result_map.put("name", name);
                    result_map.put("school", school);

                    Log.d("DbAsyncTask", "finished");
                } catch (JSONException je) {
                    Log.d("DbAsyncTask", "jsonObject error");
                    je.printStackTrace();
                }
            }

        }
    }
}
