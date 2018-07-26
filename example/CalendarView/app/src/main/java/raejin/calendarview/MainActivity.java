package raejin.calendarview;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    Button button_timePicker, button_datePicker;
    int year, month, day, hour, min;
    Button button_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_datePicker = (Button)findViewById(R.id.button_datePicker);
        button_timePicker = (Button)findViewById(R.id.button_timePicker);

        button_call = (Button)findViewById(R.id.button_call);


        GregorianCalendar calendar = new GregorianCalendar();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        min = calendar.get(Calendar.MINUTE);

        PickerListener pickerListener = new PickerListener();

        button_timePicker.setOnClickListener(pickerListener);
        button_datePicker.setOnClickListener(pickerListener);


        CallListener callListener = new CallListener();
        button_call.setOnClickListener(callListener);
    }

    class CallListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getApplicationContext(),
                    SubActivity.class);
            startActivity(intent);
        }
    }


    class PickerListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch(view.getId()) {
                case R.id.button_datePicker:
                    new DatePickerDialog(MainActivity.this,
                            dateSetListener, year, month, day).show();
                    break;
                case R.id.button_timePicker:
                    new TimePickerDialog(MainActivity.this,
                            timeSetListener, hour, min, false).show();
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
                }
            };
}
