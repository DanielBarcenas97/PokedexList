package com.example.pokemonlistapp.utils;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.example.pokemonlistapp.R;

public class CustomDialogUtil {

    public static void showDatePickerDialog(Context context){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);

        View dialogView = LayoutInflater.from(context).inflate(R.layout.layout_date_picker_dialog,null);
        DatePicker datePicker = dialogView.findViewById(R.id.datepicker);
        dialog.setView(dialogView);

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog1, int i) {
                dialog1.dismiss();
            }
        });

        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog1, int i) {
                dialog1.dismiss();
            }
        });

        AlertDialog alert = dialog.create();
        alert.show();
    }
}
