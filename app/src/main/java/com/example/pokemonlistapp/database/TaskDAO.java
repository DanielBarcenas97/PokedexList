package com.example.pokemonlistapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface TaskDAO {

    @Insert
    public void insertTask(TaskItem taskItem);

    @Query("SELECT * FROM taskItem WHERE date == :dateString")
    public TaskItem[] getTaskByDate(String dateString);
    

}
