package com.example.pokemonlistapp.callback;

public interface ActionCallback {
    interface DatePickerCallBack{
        void selectedDate(String dateString);
    }

    interface TimePickerCallBack{
        void selectedTime(String dateString);
    }


}
