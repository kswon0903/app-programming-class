package raejin.myportflio;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ReserveActivity extends AppCompatActivity {

    Toolbar toolbar_reserve;
    CalendarView calendarView_reserve;
    ListView listView_reserve;
    public static ArrayList<ReserveInfo> infoArrayList;
    public static ArrayList<ReserveInfo> todayArrayList;
    public static ReserveAdapter reserveAdapter;
    int pick_year, pick_month, pick_day;

    @Override
    protected void onResume() {
        super.onResume();
        updateTodaySchedule();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        toolbar_reserve = (Toolbar)findViewById(R.id.toolbar_reserve);
        calendarView_reserve = (CalendarView)findViewById(R.id.calendarView_reserve);
        listView_reserve = (ListView)findViewById(R.id.listView_reserve);

        infoArrayList = MainActivity.infoArrayList;
        todayArrayList = new ArrayList<ReserveInfo>();

        // 툴바 관련 설정
        toolbar_reserve.setTitle("");
        toolbar_reserve.setNavigationIcon(R.drawable.baseline_arrow_back_black_36);
        setSupportActionBar(toolbar_reserve);


        // 캘린더뷰 관련 설정
        Calendar today = Calendar.getInstance();
        pick_year = today.get(Calendar.YEAR);
        pick_month = today.get(Calendar.MONTH)+1;
        pick_day = today.get(Calendar.DATE);
        Log.d("calender view ", pick_year +"/"+ (pick_month) +"/"+ pick_day);

        updateTodaySchedule();

        calendarView_reserve.setOnDateChangeListener(
                new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {

                        pick_year = i;
                        pick_month = (i1+1);
                        pick_day = i2;

                        updateTodaySchedule();

                        reserveAdapter.notifyDataSetChanged();
                    }
                }
        );

        reserveAdapter = new ReserveAdapter(
                getApplicationContext(), todayArrayList, R.layout.item_reserve);

        listView_reserve.setAdapter(reserveAdapter);
    }


    public void updateTodaySchedule() {
        todayArrayList.clear();

        for(int i = 0; i < infoArrayList.size(); i++) {
            Log.d("month1", String.valueOf(infoArrayList.get(i).date.get(Calendar.YEAR)));
            Log.d("month2", String.valueOf(infoArrayList.get(i).date.get(Calendar.MONTH)));
            Log.d("month3", String.valueOf(infoArrayList.get(i).date.get(Calendar.DATE)));
            if(pick_year != infoArrayList.get(i).date.get(Calendar.YEAR)) continue;
            if(pick_month != infoArrayList.get(i).date.get(Calendar.MONTH)) continue;
            if(pick_day != infoArrayList.get(i).date.get(Calendar.DATE)) continue;
            todayArrayList.add(infoArrayList.get(i));
        }
    }
    class ReserveAdapter extends BaseAdapter {

        Context context;
        LayoutInflater layoutInflater;
        int item_layout_id;
        ArrayList<ReserveInfo> arrayList;
        int pos;


        public ReserveAdapter(Context context,
                              ArrayList<ReserveInfo> arrayList,
                              int item_layout_id) {
            this.context = context;
            this.arrayList = arrayList;
            this.item_layout_id = item_layout_id;
            this.layoutInflater = (LayoutInflater)context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return arrayList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if(view == null) {
                view = layoutInflater.inflate(
                        item_layout_id,
                        viewGroup,
                        false);
            }

            pos = i;

            ConstraintLayout mLayout = (ConstraintLayout)view.findViewById(R.id.mLayout);
            TextView textView_name = (TextView)view.findViewById(R.id.textView_name);
            TextView textView_date = (TextView)view.findViewById(R.id.textView_date);
            TextView textView_time = (TextView)view.findViewById(R.id.textView_time);

            Calendar temp_date = arrayList.get(pos).date;

            textView_name.setText(arrayList.get(pos).name + "외 " + (arrayList.get(pos).member - 1) + "명");
            textView_date.setText(temp_date.get(Calendar.YEAR) + "/" + temp_date.get(Calendar.MONTH) +
                    "/" + temp_date.get(Calendar.DATE));
            textView_time.setText(temp_date.get(Calendar.HOUR_OF_DAY) + " : " + temp_date.get(Calendar.MINUTE));

            return view;
        }
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
                Intent intent = new Intent(
                        getApplicationContext(),
                        InputActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
