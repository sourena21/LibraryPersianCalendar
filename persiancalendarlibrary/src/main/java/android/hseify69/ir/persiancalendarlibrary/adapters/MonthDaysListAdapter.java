package android.hseify69.ir.persiancalendarlibrary.adapters;

import android.hseify69.ir.persiancalendarlibrary.R;
import android.hseify69.ir.persiancalendarlibrary.models.Date;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.logging.Handler;

import saman.zamani.persiandate.PersianDate;

/**
 * Created by HAMED on 11/27/2016.
 */
public class MonthDaysListAdapter extends RecyclerView.Adapter<MonthDaysListAdapter.DayViewHolder> {

    ArrayList<Date> daysList;
    OnDayClickListener dayClickListener;

    public MonthDaysListAdapter(ArrayList<Date> list) {
        daysList = list;
    }

    @NonNull
    @Override
    public DayViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = View.inflate(viewGroup.getContext(), R.layout.day_item_view, null);
        DayViewHolder dayViewHolder = new DayViewHolder(v);
        return dayViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DayViewHolder holder, int i) {
        final Date day = daysList.get(i);
        if (day != null) {
            holder.txtDayNumber.setText("" + day.getMounthDay());

            if (i % 7 == 6) {
                holder.txtDayNumber.setTextColor(holder.txtDayNumber.getContext().getResources()
                        .getColor(R.color.colorRed));
            }

            if (day.isSelected()) {
                holder.txtDayNumber.setBackgroundResource(R.drawable.background_circle_blue);
            } else {
                holder.txtDayNumber.setBackgroundResource(R.color.transparent);
            }

            holder.txtDayNumber.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dayClickListener != null && day != null) {
                        dayClickListener.onDayClick(day);
                    }
                }
            });
        } else {
            holder.txtDayNumber.setText("");
            holder.txtDayNumber.setBackgroundResource(R.color.transparent);
        }
    }

    @Override
    public int getItemCount() {
        if (daysList != null) {
            return daysList.size();
        }
        return 0;
    }

    class DayViewHolder extends RecyclerView.ViewHolder {

        TextView txtDayNumber;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);

            txtDayNumber = itemView.findViewById(R.id.DIV_txtDayNumber);
        }
    }

    public interface OnDayClickListener {
        void onDayClick(Date clickedDay);
    }

    public void setOnDayClickListener(OnDayClickListener listener) {
        dayClickListener = listener;
    }
}
