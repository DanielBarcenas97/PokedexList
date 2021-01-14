package com.example.pokemonlistapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonlistapp.adapter.TaskListAdapter;
import com.example.pokemonlistapp.callback.ActionCallback;
import com.example.pokemonlistapp.config.DataConfig;
import com.example.pokemonlistapp.database.AppDatabase;
import com.example.pokemonlistapp.database.TaskItem;
import com.example.pokemonlistapp.utils.CustomDialogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ActionCallback.DatePickerCallBack {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.toolbar_tittle)
    TextView toolbarTittle;

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    @BindView(R.id.tv_noresult)
    TextView noResult;

    @BindView(R.id.today_tittle)
    TextView todayTittle;

    @BindView(R.id.task_count)
    TextView taskCount;

    private TaskListAdapter adapter;
    private List<TaskItem> allTask;
    private AppDatabase db;
    private String chooseDate;
    private String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbarTittle.setText("Pokemon List APP");

        db = AppDatabase.getDatabase(this);
        chooseDate = DataConfig.getCurrentDate(this);

        allTask = new ArrayList<>();
        allTask.clear();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TaskListAdapter(this, allTask);
        recyclerView.setAdapter(adapter);

        Log.e(TAG,"Current Date:" + DataConfig.getCurrentDate(this));
        //new FetchTask(DataConfig.getCurrentDate(this)).execute();
    }

    @Override
    protected void onResume() {
        super.onResume();
        new FetchTask(chooseDate).execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        CustomDialogUtil.showDatePickerDialog(this, this);
        return true;
    }

    @OnClick(R.id.btn_add_task) void clickAddTask(){
        startActivity(new Intent(this,AddEditTaskActivity.class));
    }

    @Override
    public void selectedDate(String dateString) {
        if(dateString.equalsIgnoreCase(DataConfig.getCurrentDate(this))){
            todayTittle.setText("Today");
        }else{
            todayTittle.setText(DataConfig.formatDate(this,dateString));
        }
        chooseDate = dateString;
        new FetchTask(dateString).execute();
    }

    class FetchTask extends AsyncTask<Void,Void, Void>{

        String dateString;

        public FetchTask(String dateString) {
            this.dateString = dateString;
            allTask.clear();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            allTask.addAll(Arrays.asList(db.taskDAO().getTaskByDate(dateString)));

            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            taskCount.setText(allTask.size() + " tasks");

            if(allTask.size() > 0){

                noResult.setVisibility(View.GONE);
            }else{
                noResult.setVisibility(View.VISIBLE);
                allTask.clear();
            }
            adapter.notifyDataSetChanged();
        }
    }

}