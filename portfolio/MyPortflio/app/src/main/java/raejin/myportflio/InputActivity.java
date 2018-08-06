package raejin.myportflio;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class InputActivity extends AppCompatActivity {
    EditText editText_name, editText_date, editText_time, editText_member;
    int year, month, day, hour, min;
    int pick_year, pick_month, pick_day, pick_hour, pick_min;
    Toolbar toolbar_book;
    public static ArrayList<ReserveInfo> infoArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        infoArrayList = MainActivity.infoArrayList;

        editText_name = (EditText)findViewById(R.id.editText_name);
        editText_date = (EditText)findViewById(R.id.editText_date);
        editText_time = (EditText)findViewById(R.id.editText_time);
        editText_member = (EditText)findViewById(R.id.editText_member);
        toolbar_book = (Toolbar)findViewById(R.id.toolbar_book);

        // 툴바 관련 설정
        toolbar_book.setTitle("");
        toolbar_book.setNavigationIcon(R.drawable.baseline_arrow_back_black_36);
        setSupportActionBar(toolbar_book);


        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        min = calendar.get(Calendar.MINUTE);

        DateTimeListener dateTimeListener = new DateTimeListener();
        editText_time.setOnClickListener(dateTimeListener);
        editText_date.setOnClickListener(dateTimeListener);
    }

    class DateTimeListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.editText_date:
                    new DatePickerDialog(InputActivity.this,
                            dateSetListener, year, month, day).show();
                    break;
                case R.id.editText_time:
                    new TimePickerDialog(InputActivity.this,
                            timeSetListener, hour, min, true).show();
                    break;
            }
        }
    }

    DatePickerDialog.OnDateSetListener dateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                /**
                 *
                 * @param datePicker dialog 안에 있는 datePicker 객체
                 * @param i 선택한 년도
                 * @param i1 선택한 월 (1월 0, 12월 11)
                 * @param i2 선택한 일 (day of month)
                 */
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    Toast.makeText(getApplicationContext(), (i)+"/"+(i1+1)+"/"+(i2),
                            Toast.LENGTH_SHORT).show();
                    editText_date.setText(i+"년 "+(i1+1)+"월 "+i2+"일 ");
                    pick_year = i;
                    pick_month = (i1+1);
                    pick_day = i2;
                }
            };

    TimePickerDialog.OnTimeSetListener timeSetListener =
            new TimePickerDialog.OnTimeSetListener() {
                /**
                 *
                 * @param timePicker dialog 안에 있는 timepicker 객체
                 * @param i 선택한 시
                 * @param i1 선택한 분
                 */
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    Toast.makeText(getApplicationContext(), (i) + ":" + (i1),
                            Toast.LENGTH_SHORT).show();
                    editText_time.setText(i+"시 "+i1+"분");
                    pick_hour = i;
                    pick_min = i1;
                }
            };

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu aMenu) {
        getMenuInflater().inflate(R.menu.menu_input, aMenu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItem_input_item1:
                Calendar cal = Calendar.getInstance();
                cal.set(pick_year, pick_month, pick_day, pick_hour, pick_min);

                ReserveInfo temp = new ReserveInfo(
                        editText_name.getText().toString(),
                        Integer.valueOf(editText_member.getText().toString()),
                        cal
                );

                infoArrayList.add(temp);
                ReserveActivity.reserveAdapter.notifyDataSetChanged();
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
