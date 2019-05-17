package android.hseify69.ir.librarypersiancalendar;

import android.hseify69.ir.persiancalendarlibrary.models.Date;
import android.hseify69.ir.persiancalendarlibrary.views.SimplePersianCalendarView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SimplePersianCalendarView calendarView=findViewById(R.id.AM_calendarView);
        calendarView.setOnSelectDay(new SimplePersianCalendarView.OnSelectOneDay() {
            @Override
            public void onSelectd(Date date) {
                Log.d("ON_SELECT_DAY","selected: "+date.toString());
            }

            @Override
            public void onUnselectd(Date date) {
                Log.d("ON_SELECT_DAY","unselected: "+date.toString());
            }
        });
    }
}
