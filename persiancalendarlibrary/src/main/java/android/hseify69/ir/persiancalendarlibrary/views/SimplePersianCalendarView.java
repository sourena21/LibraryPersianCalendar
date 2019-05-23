package android.hseify69.ir.persiancalendarlibrary.views;

import android.content.Context;
import android.hseify69.ir.persiancalendarlibrary.R;
import android.hseify69.ir.persiancalendarlibrary.adapters.MonthDaysListAdapter;
import android.hseify69.ir.persiancalendarlibrary.models.Date;
import android.hseify69.ir.persiancalendarlibrary.utils.CalendarCalculator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import saman.zamani.persiandate.PersianDate;

import static android.hseify69.ir.persiancalendarlibrary.utils.CalendarCalculator.getThisMounthDaysList;
import static android.hseify69.ir.persiancalendarlibrary.utils.CalendarCalculator.isLeapYear;

public class SimplePersianCalendarView extends RelativeLayout {

    ImageButton btnNexMonth;
    ImageButton btnPreviesMounth;
    TextView txtMounthYear;
    RecyclerView rclDaysList;

    ArrayList<Date> daysList;
    MonthDaysListAdapter adapter;
    int year, month, day, weekDayNumber;
    boolean isMultiSelectableDay = false;
    OnSelectOneDay onSelectOneDay;
    Map<String, Boolean> selectedDaysList;

    public SimplePersianCalendarView(Context context) {
        super(context);
        init(context);
    }

    public SimplePersianCalendarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SimplePersianCalendarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(final Context context) {
        PersianDate pDate = new PersianDate();
        init(context, new Date(pDate));
    }

    public void init(final Context context, Date initDate) {
        selectedDaysList = new HashMap<>();
        init(context, initDate, selectedDaysList);
    }

    public void init(final Context context, Map<String, Boolean> daysListToSelect) {
        PersianDate pDate = new PersianDate();
        init(context, new Date(pDate), daysListToSelect);
    }

    public void init(final Context context, Date initDate, Map<String, Boolean> daysListToSelect) {
        View v = View.inflate(context, R.layout.simple_calendar_view, null);
        this.addView(v);

        btnNexMonth = v.findViewById(R.id.SCV_btnNextMonth);
        btnPreviesMounth = v.findViewById(R.id.SCV_btnPriviesMonth);
        txtMounthYear = v.findViewById(R.id.SCV_txtMonthYear);
        rclDaysList = v.findViewById(R.id.SCV_rclDaysList);

        year = initDate.getYear();
        month = initDate.getMounth();
        day = initDate.getMounthDay();
        weekDayNumber = initDate.getWeekDay();
        selectedDaysList = daysListToSelect;

        rclDaysList.setLayoutManager(new GridLayoutManager(context, 7));
        setCalendarMonthList();

        btnPreviesMounth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setPreviosMonth();
            }
        });
        btnNexMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setNextMonth();
            }
        });
    }

    private void setCalendarMonthList() {
        txtMounthYear.setText(getResources().getString(CalendarCalculator.getMonthName(month)) + "  " + year);
        daysList = getThisMounthDaysList(year, month, day, weekDayNumber, selectedDaysList);
        adapter = new MonthDaysListAdapter(daysList);
        rclDaysList.setAdapter(adapter);
        adapter.setOnDayClickListener(new MonthDaysListAdapter.OnDayClickListener() {
            @Override
            public void onDayClick(Date clickedDate) {
                if (selectedDaysList.get(clickedDate.toString()) != null
                        && selectedDaysList.get(clickedDate.toString())) {
                    selectedDaysList.remove(clickedDate.toString());
                    setSelectDate(clickedDate, false);
                    if (onSelectOneDay != null) {
                        onSelectOneDay.onUnselectd(clickedDate);
                    }
                } else {
                    if (!isMultiSelectableDay) {
                        selectedDaysList.clear();
                        clearDaysList();
                    }
                    selectedDaysList.put(clickedDate.toString(), true);
                    setSelectDate(clickedDate, true);
                    if (onSelectOneDay != null) {
                        onSelectOneDay.onSelectd(clickedDate);
                    }
                }
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void setNextMonth() {
        increaseMonth();
        day = 1;
        weekDayNumber = daysList.size() % 7;
        setCalendarMonthList();
    }

    private void increaseMonth() {
        if (month == 12) {
            month = 1;
            year++;
        } else {
            month++;
        }
        txtMounthYear.setText(getResources().getString(CalendarCalculator.getMonthName(month)) + "  " + year);
    }

    private void setPreviosMonth() {
        decreaseMonth();
        PersianDate persianDate = new PersianDate();
        persianDate.initJalaliDate(year, month, 1);
        day = 1;
        weekDayNumber = persianDate.dayOfWeek();
        setCalendarMonthList();
    }

    private void decreaseMonth() {
        if (month == 1) {
            month = 12;
            year--;
        } else {
            month--;
        }
        txtMounthYear.setText(getResources().getString(CalendarCalculator.getMonthName(month)) + "  " + year);
    }

    private void clearDaysList() {
        for (int index = 0; index < daysList.size(); index++) {
            if (daysList.get(index) != null) {
                daysList.get(index).setSelected(false);
            }
        }
    }

    private void setSelectDate(Date clickedDate, boolean isSelected) {
        for (int index = 0; index < daysList.size(); index++) {
            if (daysList.get(index) != null && daysList.get(index).toString().equals(clickedDate.toString())) {
                daysList.get(index).setSelected(isSelected);
                return;
            }
        }
    }

    public boolean isMultiSelectableDay() {
        return isMultiSelectableDay;
    }

    public void setMultiSelectableDay(boolean multiSelectableDay) {
        isMultiSelectableDay = multiSelectableDay;
    }

    public ArrayList<String> getSelectedDaysList() {
        ArrayList<String> dates = new ArrayList<String>(selectedDaysList.keySet());
        return dates;
    }

    public void setSelectedDaysList(ArrayList<String> selectedDays) {
        selectedDaysList.clear();
        this.addSelectedDaysList(selectedDays);
    }

    public void addSelectedDaysList(ArrayList<String> selectedDays) {
        for (int i = 0; i < selectedDays.size(); i++) {
            this.addSelectedDay(selectedDays.get(i));
        }
    }

    public void addSelectedDay(String selectedDay) {
        selectedDaysList.put(selectedDay, true);
    }

    public void addSelectedDay(Date selectedDay) {
        selectedDaysList.put(selectedDay.toString(), true);
    }

    public interface OnSelectOneDay {
        void onSelectd(Date date);

        void onUnselectd(Date date);
    }

    public void setOnSelectDay(OnSelectOneDay listener) {
        onSelectOneDay = listener;
    }
}
