package com.example.pokemonlistapp.database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class TaskItem  implements Serializable {

    @PrimaryKey(autoGenerate = true)
    public int tid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "time")
    public String time;

    @ColumnInfo(name = "date")
    public String date;

    @ColumnInfo(name = "type")
    public String type;

    @ColumnInfo(name = "skill")
    public String skill;


}
