package com.example.pokemonlistapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pokemonlistapp.R;
import com.example.pokemonlistapp.callback.ActionCallback;
import com.example.pokemonlistapp.database.TaskItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskListViewHolder> {

    Context context;
    List<TaskItem> data;
    ActionCallback.TaskItemClick callback;

    public TaskListAdapter(Context context, List<TaskItem> data, ActionCallback.TaskItemClick callback) {
        this.context = context;
        this.data = data;
        this.callback = callback;
    }

    @NonNull
    @Override
    public TaskListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskListViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskListViewHolder holder, int position) {
        holder.taskName.setText(data.get(position).tittle);
        holder.time.setText(data.get(position).time);
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.clickItem(data.get(position), view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class TaskListViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.task_name)
        TextView taskName;

        @BindView(R.id.time)
        TextView time;

        @BindView(R.id.iv_more)
        ImageView more;


        public TaskListViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
