package com.example.pokemonlistapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.example.pokemonlistapp.R;
import com.example.pokemonlistapp.utils.CustomDialogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddEditTaskActivity extends AppCompatActivity {


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_task);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        toolbarTittle.setText("Add new Task");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    @OnClick(R.id.btn_save) void clickSave(){
    }

    @OnClick(R.id.et_date) void chooseDate(){
        CustomDialogUtil.showDatePickerDialog(this);
    }

}
