package android.hseify69.ir.persiancalendarlibrary.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

import saman.zamani.persiandate.PersianDate;

/**
 * Created by HAMED on 11/26/2016.
 */

public class Date implements Parcelable {

    private int mounthDay;
    private int weekDay;
    private int month;
    private int year;
    private boolean isSelected;

    public Date() {
        Calendar c = Calendar.getInstance();
        weekDay = c.get(Calendar.DAY_OF_WEEK);
    }

    public Date(PersianDate pDate) {
        year = pDate.getShYear();
        month = pDate.getShMonth();
        mounthDay = pDate.getShDay();
        pDate.initJalaliDate(year, month, mounthDay);
        weekDay = pDate.dayOfWeek();
    }

    public Date(int y, int m, int d) {
        year = y;
        month = m;
        mounthDay = d;

        PersianDate pDate = new PersianDate();
        pDate.initJalaliDate(year, month, mounthDay);
        weekDay = pDate.dayOfWeek();
    }

    protected Date(Parcel in) {
        mounthDay = in.readInt();
        weekDay = in.readInt();
        month = in.readInt();
        year = in.readInt();
        isSelected = in.readByte() != 0x00;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mounthDay);
        dest.writeInt(weekDay);
        dest.writeInt(month);
        dest.writeInt(year);
        dest.writeByte((byte) (isSelected ? 0x01 : 0x00));
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Date> CREATOR = new Parcelable.Creator<Date>() {
        @Override
        public Date createFromParcel(Parcel in) {
            return new Date(in);
        }

        @Override
        public Date[] newArray(int size) {
            return new Date[size];
        }
    };

    public int getMounthDay() {
        return mounthDay;
    }

    public void setMounthDay(int mounthDay) {
        this.mounthDay = mounthDay;
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    public int getMounth() {
        return month;
    }

    public void setMounth(int mounth) {
        this.month = mounth;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    @Override
    public String toString() {
        return year + "/" + month + "/" + mounthDay;
    }
}
