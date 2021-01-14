package com.example.pokemonlistapp.callback;

import android.view.View;

import com.example.pokemonlistapp.database.TaskItem;

public interface ActionCallback {
    interface DatePickerCallBack{
        void selectedDate(String dateString);
    }

    interface TimePickerCallBack{
        void selectedTime(String dateString);
    }

    interface TaskItemClick{
        void clickItem(TaskItem taskItem, View view);
    }

}
