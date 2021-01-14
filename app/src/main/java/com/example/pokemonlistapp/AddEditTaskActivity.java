package com.example.pokemonlistapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.pokemonlistapp.R;
import com.example.pokemonlistapp.callback.ActionCallback;
import com.example.pokemonlistapp.database.AppDatabase;
import com.example.pokemonlistapp.database.TaskItem;
import com.example.pokemonlistapp.utils.CustomDialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddEditTaskActivity extends AppCompatActivity implements ActionCallback.DatePickerCallBack , ActionCallback.TimePickerCallBack {


    @BindView(R.id.toolbar_tittle)
    TextView toolbarTittle;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.et_date)
    EditText etDate;

    @BindView(R.id.et_title)
    EditText etTittle;

    @BindView(R.id.et_time)
    EditText etTime;

    private AppDatabase db;

    private TaskItem parmItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);
        ButterKnife.bind(this);
        db = AppDatabase.getDatabase(this);

        parmItem = (TaskItem) getIntent().getSerializableExtra("item");


        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbarTittle.setText(parmItem != null ? "Edit": "Add New"  + "Task");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        if(parmItem != null){
               etTittle.setText(parmItem.tittle);
               etTime.setText(parmItem.time);
               etDate.setText(parmItem.date);
        }


    }

    @OnClick(R.id.btn_save) void clickSave(){
        if(etTime.getText().toString().length() <=0 || etTittle.getText().toString().length()<=0 ||
        etDate.getText().toString().length() <=0){
            Toast.makeText(this, "Please fill required fields",Toast.LENGTH_LONG).show();
        }else{

            if(parmItem != null){
                 parmItem.tittle = etTittle.getText().toString();
                 parmItem.date = etDate.getText().toString();
                 parmItem.time = etTime.getText().toString();
                new AddTask(parmItem).execute();
            }else{
                TaskItem taskItem = new TaskItem();
                taskItem.tittle = etTittle.getText().toString();
                taskItem.date =   etDate.getText().toString();
                taskItem.time =   etTime.getText().toString();
                new AddTask(taskItem).execute();
            }

        }
    }

    @OnClick(R.id.et_date) void chooseDate(){
        CustomDialogUtil.showDatePickerDialog(this,this);
    }

    @OnClick(R.id.et_time) void clickTimePicker(){
        CustomDialogUtil.showTimePickerDialog(this,this);
    }


    @Override
    public void selectedDate(String dateString) {
        etDate.setText(dateString);
    }

    @Override
    public void selectedTime(String timeString) {
        etTime.setText(timeString);
    }


    class AddTask extends AsyncTask<Void, Void,Void>{

        TaskItem taskItem;

        public AddTask(TaskItem taskItem) {
            this.taskItem = taskItem;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if(parmItem != null){
                db.taskDAO().updateTask(taskItem);
            }else{
                db.taskDAO().insertTask(taskItem);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            etTime.setText("");
            etDate.setText("");
            etTittle.setText("");
        }
    }



}
