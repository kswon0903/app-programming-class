package raejin.myportflio;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CalendarView;
import android.widget.Toast;

public class ReserveActivity extends AppCompatActivity {

    Toolbar toolbar_reserve;
    CalendarView calendarView_reserve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        toolbar_reserve = (Toolbar)findViewById(R.id.toolbar_reserve);
        calendarView_reserve = (CalendarView)findViewById(R.id.calendarView_reserve);

        // 툴바 관련 설정
        toolbar_reserve.setTitle("");
        toolbar_reserve.setNavigationIcon(R.mipmap.ic_launcher_round);
        setSupportActionBar(toolbar_reserve);


        // 캘린더뷰 관련 설정
        calendarView_reserve.setOnDateChangeListener(
                new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                        Log.d("calender view ", i +"/"+ i1 +"/"+ i2);
                        Toast.makeText(getApplicationContext(),
                                i +"/"+ i1 +"/"+ i2,
                                Toast.LENGTH_SHORT).show();
                    }
                }

        );


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu aMenu) {
        getMenuInflater().inflate(R.menu.menu_reserve, aMenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItem_reserve_item1:
                    Log.d("menu item", "item1 pressed");
                Toast.makeText(getApplicationContext(),
                        "item1 pressed",
                        Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(
                        getApplicationContext(),
                        MainActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
