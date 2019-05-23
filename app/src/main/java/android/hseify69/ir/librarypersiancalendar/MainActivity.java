package android.hseify69.ir.librarypersiancalendar;

import android.hseify69.ir.persiancalendarlibrary.models.Date;
import android.hseify69.ir.persiancalendarlibrary.views.SimplePersianCalendarView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SimplePersianCalendarView calendarView=findViewById(R.id.AM_calendarView);
        calendarView.setMultiSelectableDay(false);
        calendarView.setOnSelectDay(new SimplePersianCalendarView.OnSelectOneDay() {
            @Override
            public void onSelectd(Date date) {
                ArrayList<String> dates=calendarView.getSelectedDaysList();
                for (int i=0;i<dates.size();i++)
                {
                    Log.d("SELECTED_DAYS","selected: "+dates.get(i));
                }
            }

            @Override
            public void onUnselectd(Date date) {
                ArrayList<String> dates=calendarView.getSelectedDaysList();
                for (int i=0;i<dates.size();i++)
                {
                    Log.d("SELECTED_DAYS","selected: "+dates.get(i));
                }
            }
        });
        ArrayList<String> dates=new ArrayList<>();
        dates.add("1398/3/1");
        dates.add("1398/3/2");
        dates.add("1398/3/3");
        dates.add("1398/3/4");
        dates.add("1398/3/5");
        dates.add("1398/3/6");
        dates.add("1398/3/7");
        calendarView.setSelectedDaysList(dates);

    }
}
