package com.example.pokemonlistapp.utils;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.pokemonlistapp.AddEditTaskActivity;
import com.example.pokemonlistapp.R;
import com.example.pokemonlistapp.callback.ActionCallback;

public class CustomDialogUtil {

    public static String TAG = CustomDialogUtil.class.getSimpleName();
    public static String selectedDate = "", selectedTime = "";

    public static void showDatePickerDialog(Context context, ActionCallback.DatePickerCallBack callBack){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);

        View dialogView = LayoutInflater.from(context).inflate(R.layout.layout_date_picker_dialog,null);
        CalendarView calendarView = dialogView.findViewById(R.id.calendarView);
        dialog.setView(dialogView);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                Log.e(TAG, "year" + i );
                selectedDate = i2 + "-" + (i1+1) + "-" +i;
            }
        });

        dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog1, int i) {
                dialog1.dismiss();
                callBack.selectedDate(selectedDate);
            }
        });

        dialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog1, int i) {
                dialog1.dismiss();
            }
        });

        AlertDialog alert = dialog.create();
        alert.show();
    }

    public static void showTimePickerDialog(Context context, ActionCallback.TimePickerCallBack callBack){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        View timeDialog = LayoutInflater.from(context).inflate(R.layout.time_picker_dialog,null);
        TimePicker timePicker = timeDialog.findViewById(R.id.timepicker);
        dialog.setView(timeDialog);

        timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                selectedTime = i + ":" + i1;
            }
        });

        dialog.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                callBack.selectedTime(selectedTime);
            }
        });

        dialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog alert = dialog.create();
        alert.show();
    }
}
