package android.hseify69.ir.persiancalendarlibrary.utils;

import android.hseify69.ir.persiancalendarlibrary.models.Date;

import java.util.ArrayList;
import java.util.Map;

public class CalendarCalculator {

    public static ArrayList<Date> getThisMounthDaysList(int year, int mounth, int mounthDay,
                                                        int weekDay, Map<String, Boolean> selectedDaysList) {
        ArrayList<Date> list = new ArrayList<>();
        int k = getGrayFirstOfList(mounthDay, weekDay);
        for (; k > 0; k--) {
            list.add(null);
        }
        int monthSize;
        if (mounth <= 6) {
            monthSize = 31;
        } else if (mounth <= 11) {
            monthSize = 30;
        } else if (mounth == 12 && isLeapYear(year)) {
            monthSize = 30;
        } else {
            monthSize = 29;
        }
        for (k = 1; k <= monthSize; k++) {
            Date date = new Date(year, mounth, k);
            if (selectedDaysList.get(date.toString()) != null && selectedDaysList.get(date.toString())) {
                date.setSelected(true);
            }
            list.add(date);
        }
        return list;
    }

    public static int getGrayFirstOfList(int mounthDay, int weekDay) {
        int firstDayNumber = (weekDay + 1) - (mounthDay % 7);
        if (firstDayNumber < 0) {
            firstDayNumber += 7;
        } else if (firstDayNumber >= 7) {
            firstDayNumber -= 7;
        }
        return firstDayNumber;
    }

    public static boolean isLeapYear(int year) {
        double a = 0.025;
        int b = 266;
        double leapDays0;
        double leapDays1;
        if (year > 0) {
            leapDays0 = ((year + 38) % 2820) * 0.24219 + a;
            leapDays1 = ((year + 39) % 2820) * 0.24219 + a;
        } else if (year
                < 0) {
            leapDays0 = ((year + 39) % 2820) * 0.24219 +
                    a;
            leapDays1 = ((year + 40) % 2820) * 0.24219 + a;
        } else {
            return false;
        }

        int frac0 = (int) ((leapDays0 - (int) (leapDays0)) * 1000);
        int frac1 = (int) ((leapDays1 - (int) (leapDays1)) * 1000);

        if (frac0 <= b && frac1 > b) {
            return true;
        } else {
            return false;
        }
    }

    public static String getMonthName(int month) {
        switch (month) {
            case 1:
                return "فروردین";
            case 2:
                return "اردیبهشت";
            case 3:
                return "خرداد";
            case 4:
                return "تیر";
            case 5:
                return "مرداد";
            case 6:
                return "شهریور";
            case 7:
                return "مهر";
            case 8:
                return "آبان";
            case 9:
                return "آذر";
            case 10:
                return "دی";
            case 11:
                return "بهمن";
            case 12:
                return "اسفند";
            default:
                return "فروردین";
        }
    }
}
