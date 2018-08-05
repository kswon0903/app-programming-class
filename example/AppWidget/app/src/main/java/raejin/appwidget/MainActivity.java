package raejin.appwidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.appwidget.AppWidgetProviderInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppWidgetManager mAWM = AppWidgetManager.getInstance(this);
        List<AppWidgetProviderInfo> mList = mAWM.getInstalledProviders();

        String result = "count =" + mList.size() + "\n";
        for(AppWidgetProviderInfo info : mList) {
            result += info.toString() + "\n\n";
        }

        TextView resulttext = (TextView)findViewById(R.id.result);
        resulttext.setText(result);
    }
}
